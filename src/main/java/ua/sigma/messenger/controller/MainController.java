package ua.sigma.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.sigma.messenger.dao.ChatDao;
import ua.sigma.messenger.dao.MessageDao;
import ua.sigma.messenger.dao.TopicDao;
import ua.sigma.messenger.model.Chat;
import ua.sigma.messenger.model.Message;
import ua.sigma.messenger.model.Topic;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    TopicDao topicDao;

    @Autowired
    ChatDao chatDao;

    @Autowired
    MessageDao messageDao;

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView defaultPage(@RequestParam(value = "chat", required = false) String chatId) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            model.setViewName("login");
        } else {
            putTopicsChatsMessages(model, chatId);
            model.setViewName("mainpage");
        }
        return model;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) {
            model.setViewName("login");
        } else {
            model.setViewName("welcome");
        }
        return model;
    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("admin");
        return model;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }
        model.setViewName("403");
        return model;

    }

    private void putTopicsChatsMessages(ModelAndView model, String chatId) {
        List<Topic> topics = topicDao.findAll();
        model.addObject("topics", topics);
        List<Chat> chats = chatDao.findAll();
        model.addObject("chats", chats);
        if (chatId != null && !chatId.equals("")) {
            List<Message> messages = messageDao.findByChatId(Integer.parseInt(chatId));
            model.addObject("messages", messages);
        }
    }
}
