package org.softuni.finalpoject.web.controllers;


import org.softuni.finalpoject.web.annotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    @GetMapping("/")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Index")
    public ModelAndView index(){
        return super.view("index");
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Home")
    public ModelAndView home(){
        return super.view("home");
    }


    @GetMapping("/contact")
    @PreAuthorize("isAnonymous()")
    @PageTitle("Contact us")
    public ModelAndView contact(){
        return super.view("contact");
    }

    @GetMapping("/contact-logged")
    @PreAuthorize("isAuthenticated()")
    @PageTitle("Contact us")
    public ModelAndView contactLogged(){
        return super.view("contact");
    }
}
