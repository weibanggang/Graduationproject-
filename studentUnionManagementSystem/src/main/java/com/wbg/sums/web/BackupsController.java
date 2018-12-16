package com.wbg.sums.web;

import com.wbg.sums.service.BackupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/backups")
public class BackupsController {
    @Autowired
    private BackupsService backupsService;

}
