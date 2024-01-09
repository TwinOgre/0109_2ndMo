package com.ndMo9.user;

import com.ndMo9.article.ArticleForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signUp")
    public String signUp(UserForm userForm) {

        return "user_signUpForm";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_signUpForm";
        }
        if (!userForm.getPassword1().equals(userForm.getPassword2())) {
            return "user_signUpForm";
        }

        this.userService.signUp(userForm.getUserId(), userForm.getNickname(), userForm.getPassword1());

        return "redirect:/article/list";
    }

    @GetMapping("/login")
    public String login() {

        return "user_loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_loginForm";
        }
        this.userService.login(userForm.getUserId(), userForm.getPassword1());

        return "redirect:/article/list";
    }

}
