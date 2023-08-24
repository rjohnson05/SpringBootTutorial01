package edu.carroll.cs389.web.form.controller;

import edu.carroll.cs389.web.form.LoginForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private static final String validUser = "cs389user";
    private static final String validPass = "supersecret";
    @GetMapping("/login")
    public String loginGet(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@Valid @ModelAttribute LoginForm loginForm, BindingResult result, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            return "login";
        }
        if (!validUser.equalsIgnoreCase(loginForm.getUsername()) &&
        validPass.equals(loginForm.getPassword())) {
            result.addError(new ObjectError("globalError", "Username and password do not match known users"));
            return "login";
        }
        attrs.addAttribute("username", loginForm.getUsername());
        return "redirect:/loginSuccess";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(String username, Model model) {
        model.addAttribute("username", username);
        return "loginSuccess";
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "loginFailure";
    }
}
