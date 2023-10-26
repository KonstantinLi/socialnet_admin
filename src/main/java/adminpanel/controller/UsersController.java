package adminpanel.controller;

import adminpanel.api.request.FindPersonRq;
import adminpanel.api.response.MeRs;
import adminpanel.service.GetMeService;
import adminpanel.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    private final GetMeService getMeService;

    @ModelAttribute("MeRs")
    public MeRs MeRs() {
        return getMeService.getMe();
    }

    @ModelAttribute("FindPersonRq")
    public FindPersonRq findPersonRq() {
        return new FindPersonRq();
    }

    @GetMapping("/users")
    public String getUsers(Model model, FindPersonRq findPersonRq) {
        return usersService.buildUsersPage(model, findPersonRq);
    }

    @PostMapping("/user/block_unblock/{id}")
    public String blockUnblockUser(@PathVariable Long id, Model model) {
        usersService.blockUnblockUser(id);
        return usersService.buildUsersPage(model, new FindPersonRq());

    }
}
