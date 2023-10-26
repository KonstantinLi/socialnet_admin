package adminpanel.controller;

import adminpanel.api.request.AddModerRq;
import adminpanel.api.response.MeRs;
import adminpanel.service.GetMeService;
import adminpanel.service.ModeratorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ModeratorsController {

    private final ModeratorsService moderatorsService;

    private final GetMeService getMeService;

    @ModelAttribute("MeRs")
    public MeRs MeRs() {
        return getMeService.getMe();
    }

    @ModelAttribute("AddModerRq")
    public AddModerRq addModerRq() {
        return new AddModerRq();
    }

    @GetMapping("/moderators")
    public String getModerators(Model model) {
        return moderatorsService.buildModeratorsPage(model);
    }

    @PostMapping("/moderators/add")
    public String addModerators(AddModerRq addModerRq) {
        moderatorsService.addModer(addModerRq);
        return "redirect:moderators";
    }

    @PostMapping("/moderators/delete/{id}")
    public String deleteModerator(@PathVariable Long id) {
        moderatorsService.deleteModer(id);
        return "redirect:moderators";
    }
}
