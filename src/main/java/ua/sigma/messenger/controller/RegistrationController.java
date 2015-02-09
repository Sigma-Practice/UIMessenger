package ua.sigma.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
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
        System.out.println(df.format(verificationToken.getExpiryDate()));
        System.out.println(df.format(cal.getTime()));

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
            String confirmationUrl = request.getContextPath() + "/regitrationConfirm.html?token=" + newToken.getToken();
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(user.getEmail());
            email.setSubject("Resend Registration Token");
            email.setText(RegistrationListener.MESSAGE + " \r\n" + "http://localhost:8080" + confirmationUrl);
            System.out.println(email.getText());
            mailSender.send(email);
            System.out.println(email.getText());
        } catch (Exception e) {
            return new ModelAndView("emailError");
        }
        return new ModelAndView("login");
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
