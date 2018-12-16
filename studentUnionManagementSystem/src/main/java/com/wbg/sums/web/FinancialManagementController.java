package com.wbg.sums.web;

import com.wbg.sums.service.FinancialManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/FinancialManagement")

public class FinancialManagementController {
    @Autowired
    private FinancialManagementService financialManagementService;
}
