package com.wbg.sums.web;

import com.wbg.sums.service.PersonalMeritRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personalMeritRecord")

public class PersonalMeritRecordController {
    @Autowired
    private PersonalMeritRecordService personalMeritRecordService;
}
