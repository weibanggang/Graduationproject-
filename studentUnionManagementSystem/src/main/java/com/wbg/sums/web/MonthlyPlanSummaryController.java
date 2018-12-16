package com.wbg.sums.web;

import com.wbg.sums.service.MonthlyPlanSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/monthlyPlanSummary")

public class MonthlyPlanSummaryController {
    @Autowired
    private MonthlyPlanSummaryService monthlyPlanSummaryService;
}
