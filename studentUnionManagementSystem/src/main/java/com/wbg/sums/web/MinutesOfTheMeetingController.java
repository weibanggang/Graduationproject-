package com.wbg.sums.web;

import com.wbg.sums.service.MinutesOfTheMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/MinutesOfTheMeeting")

public class MinutesOfTheMeetingController {
    @Autowired
    private MinutesOfTheMeetingService minutesOfTheMeetingService;
}
