package adminpanel.controller;

import adminpanel.api.request.LoginRq;
import adminpanel.errors.PasswordException;
import adminpanel.errors.WrongEmailException;
import adminpanel.service.GetMeService;
import adminpanel.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class loginController {

    private final LoginService loginService;

    private final GetMeService getMeService;

    @ModelAttribute("LoginRq")
    public LoginRq loginRq() {
        return new LoginRq();
    }

    @PostMapping("/login")
    public String login(LoginRq loginRq,
                      HttpServletResponse response) throws PasswordException, WrongEmailException {
        loginService.loginUser(loginRq, response);
        return "redirect:statistics";
    }

    @GetMapping("/exit")
    public String logout(HttpServletResponse response, Model model) {
        model.addAttribute("MeRs", getMeService.getMe());
        loginService.logout(response);
        return "redirect:";
    }

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }
}
