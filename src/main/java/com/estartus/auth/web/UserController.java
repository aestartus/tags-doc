package com.estartus.auth.web;

import com.estartus.auth.model.Document;
import com.estartus.auth.model.User;
import com.estartus.auth.service.SecurityService;
import com.estartus.auth.service.UserService;
import com.estartus.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getEmail(), userForm.getPasswordConfirm());

        return "redirect:/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Tu email y/o password son incorrectos.");

        if (logout != null)
            model.addAttribute("message", "Te has desconectado correctamente.");

        return "login";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView welcome(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("documents", getDocuments());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    private List getDocuments(){

        Document document = new Document("aestartus@gmail.com");
        document.setId(1L);
        document.setCreationDate(new Date());
        document.setModificationDate(new Date());
        document.setValueOnCreation(UUID.randomUUID().toString());
        document.setNameOfFile("archivo_"+UUID.randomUUID().toString());

        Document document1 = new Document("aestartus@gmail.com");
        document1.setId(2L);
        document1.setCreationDate(new Date());
        document1.setModificationDate(new Date());
        document1.setValueOnCreation(UUID.randomUUID().toString());
        document1.setNameOfFile("archivo_"+UUID.randomUUID().toString());

        Document document2 = new Document("aestartus@gmail.com");
        document2.setId(3L);
        document2.setCreationDate(new Date());
        document2.setModificationDate(new Date());
        document2.setValueOnCreation(UUID.randomUUID().toString());
        document2.setNameOfFile("archivo_"+UUID.randomUUID().toString());

        Document document3 = new Document("aestartus@gmail.com");
        document3.setId(4L);
        document3.setCreationDate(new Date());
        document3.setModificationDate(new Date());
        document3.setValueOnCreation(UUID.randomUUID().toString());
        document3.setNameOfFile("archivo_"+UUID.randomUUID().toString());

        return Arrays.asList(document,document1,document2,document3);
    }
}
