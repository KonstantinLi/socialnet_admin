package ru.skillbox.adminpanel.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.adminpanel.dto.request.AddAdminRq;
import ru.skillbox.adminpanel.dto.response.CurrentUserInfoRs;
import ru.skillbox.adminpanel.service.AdminService;
import ru.skillbox.adminpanel.service.StatisticsService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin-console")
public class AdminController {

    private final AdminService adminService;
    private final StatisticsService statisticsService;

    @ModelAttribute("CurrentUserInfo")
    public CurrentUserInfoRs currentUserInfoRs(@CookieValue(name = "jwtToken") String token) {
        return statisticsService.getCurrentUser(token);
    }

    @ModelAttribute("AddAdminRq")
    public AddAdminRq addModerRq() {
        return new AddAdminRq();
    }

    @GetMapping("/admins")
    public String getModerators(Model model, @CookieValue(name = "jwtToken") String token) {
        return adminService.buildAdminPage(model, token);
    }

    @PostMapping("/admins/add")
    public String addModerators(AddAdminRq addAdminRq, Model model,
                                @CookieValue(name = "jwtToken") String token) {
        adminService.addAdmin(addAdminRq);
        return adminService.buildAdminPage(model, token);
    }

    @PostMapping("/admins/delete/{id}")
    public String deleteModerator(@PathVariable Long id, Model model,
                                  @CookieValue(name = "jwtToken") String token) {
        adminService.deleteAdmin(id);
        return adminService.buildAdminPage(model, token);
    }
}
