package com.wbg.sums.web;

import com.wbg.sums.service.ClassTableService;
import com.wbg.sums.service.DepartmentalExcellenceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departmentalExcellenceRecord")
public class DepartmentalExcellenceRecordController {
    @Autowired
    private DepartmentalExcellenceRecordService departmentalExcellenceRecordService;

}
