package ua.sigma.messenger.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.sigma.messenger.event.OnRegistrationCompleteEvent;
import ua.sigma.messenger.listener.RegistrationListener;
import ua.sigma.messenger.model.PasswordResetToken;
import ua.sigma.messenger.model.User;
import ua.sigma.messenger.model.VerificationToken;
import ua.sigma.messenger.service.IUserService;
import ua.sigma.messenger.service.UserDto;
import ua.sigma.messenger.validation.EmailExistsException;
import ua.sigma.messenger.validation.LoginExistsException;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * Created by Maks on 02.02.2015.
 */
@Controller
public class RegistrationController {
    @Autowired
    private IUserService service;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private UserDetailsService userDetailsService;

    Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    public RegistrationController() {

    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView showRegForm() {
        ModelAndView model = new ModelAndView();
        UserDto userDto = new UserDto();
        model.addObject("user", userDto);
        model.setViewName("registration");
        return model;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto accountDto,
                                            BindingResult result, WebRequest request, Errors errors) {
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        }
        User registered = createUserAccount(accountDto);
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        } else {
            try {
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
            } catch (Exception me) {
                return new ModelAndView("emailError", "user", accountDto);
            }
        }
        return new ModelAndView("successRegister", "user", accountDto);
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public ModelAndView confirmRegistration(@RequestParam("token") String token) {
        ModelAndView model = new ModelAndView();
        VerificationToken verificationToken = service.getVerificationToken(token);
        if (verificationToken == null) {
            model.setViewName("badUser");
            return model;
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        logger.debug(df.format(verificationToken.getExpiryDate()));
        logger.debug(df.format(cal.getTime()));

        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addObject("expired", true);
            model.addObject("token", token);
            model.setViewName("badUser");
            return model;
        }

        user.setActivated(true);
        service.saveRegisteredUser(user);
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/resendRegistrationToken", method = RequestMethod.GET)
    public ModelAndView resendRegistrationToken(WebRequest request, @RequestParam("token") String token) {
        VerificationToken newToken = service.updateVerificationToken(token);
        User user = service.getUser(newToken.getToken());
        try {
            String confirmationUrl = request.getContextPath() + "/registrationConfirm?token=" + newToken.getToken();
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(user.getEmail());
            email.setSubject("Resend Registration Token");
            email.setText(RegistrationListener.MESSAGE + " \r\n" + "http://localhost:8080" + confirmationUrl);
            logger.debug(email.getText());
            mailSender.send(email);
            logger.debug(email.getText());
        } catch (Exception e) {
            return new ModelAndView("emailError");
        }
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public ModelAndView forgetPassword() {
        return new ModelAndView("forgotPassword");
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ModelAndView resetPassword(WebRequest request, @RequestParam("email") String userEmail) {
        ModelAndView model = new ModelAndView();
        User user = service.findUserByEmail(userEmail);
        if (user == null) {
            model.setViewName("login");
            return model;
        }

        String token = UUID.randomUUID().toString();
        service.createPasswordResetTokenForUser(user, token);
        try {
            String url = request.getContextPath() + "/changePassword?id=" + user.getId() + "&token=" + token;
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(user.getEmail());
            email.setSubject("Reset Password");
            email.setText("Reset Password" + " \r\n" + "http://localhost:8080" + url);
            logger.debug(email.getText());
            mailSender.send(email);
            logger.debug(email.getText());
        } catch (Exception e) {
            model.setViewName("emailError");
            return model;
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public ModelAndView changePassword(@RequestParam("id") int id, @RequestParam("token") String token) {
        ModelAndView model = new ModelAndView();
        PasswordResetToken passToken = service.getPasswordResetToken(token);
        User user = passToken.getUser();
        if (passToken == null || user.getId() != id) {
            logger.debug("Invalid account confirmation token.");
            logger.debug(Integer.toString(id));
            logger.debug(passToken.toString());
            model.setViewName("login");
            return model;
        }

        Calendar cal = Calendar.getInstance();
        if ((passToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            logger.debug("Registration token has expired");
            model.setViewName("login");
            return model;
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, userDetailsService.loadUserByUsername(user.getLogin()).getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        model.setViewName("updatePassword");
        return model;
    }

    @RequestMapping(value = "/savePassword", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView savePassword(@RequestParam("password") String password) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.changeUserPassword(user, password);
        ModelAndView model = new ModelAndView("login");
        return model;
    }

    private User createUserAccount(UserDto accountDto) {
        User registered = null;
        try {
            registered = service.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        } catch (LoginExistsException e) {
            return null;
        }
        return registered;
    }
}
