package adminpanel.controller;

import adminpanel.api.response.MeRs;
import adminpanel.service.GetMeService;
import adminpanel.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final StatisticService statisticService;

    private final GetMeService getMeService;

    @ModelAttribute("MeRs")
    public MeRs MeRs() {
        return getMeService.getMe();
    }

    @GetMapping("/statistics")
    public String getStatistic(Model model) {
       return statisticService.buildStatisticPage(model);
    }
}
