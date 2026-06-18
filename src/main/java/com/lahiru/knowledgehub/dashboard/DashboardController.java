package com.lahiru.knowledgehub.dashboard;

import com.lahiru.knowledgehub.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboard(
            @AuthenticationPrincipal User user
    ) {
        return dashboardService.getDashboard(user);
    }
}