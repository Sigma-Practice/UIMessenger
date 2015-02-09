package ua.sigma.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.sigma.messenger.event.OnRegistrationCompleteEvent;
import ua.sigma.messenger.model.User;
import ua.sigma.messenger.service.IUserService;
import ua.sigma.messenger.service.UserDto;
import ua.sigma.messenger.validation.EmailExistsException;
import ua.sigma.messenger.validation.LoginExistsException;

import javax.validation.Valid;

/**
 * Created by Maks on 02.02.2015.
 */
@Controller
public class RegistrationController {
    @Autowired
    private IUserService service;

    @Autowired
    private MessageSource messages;

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
                me.printStackTrace();
                return new ModelAndView("emailError", "user", accountDto);
            }
        }
        return new ModelAndView("successRegister", "user", accountDto);
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
