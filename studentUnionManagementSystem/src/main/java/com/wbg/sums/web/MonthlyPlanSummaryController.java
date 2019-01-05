package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.dto.Result;
import com.wbg.sums.entity.MonthlyPlanSummary;
import com.wbg.sums.service.MonthlyPlanSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monthlyPlanSummary")

public class MonthlyPlanSummaryController {
    @Autowired
    private MonthlyPlanSummaryService monthlyPlanSummaryService;

    /**
     * 根据mId删除
     * 要求转入 mId
     *
     * @param monthlyPlanSummary
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(MonthlyPlanSummary monthlyPlanSummary) {
        try {
            return monthlyPlanSummaryService.deleteByPrimaryKey(monthlyPlanSummary.getmId()) > 0 ? new Result().successMessage("删除成功") : new Result("删除失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 添加对象MonthlyPlanSummary
     *
     * @param monthlyPlanSummary
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody MonthlyPlanSummary monthlyPlanSummary) {
        try {
            return monthlyPlanSummaryService.insert(monthlyPlanSummary) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据aid查找对象  最多只能返回一个对象
     *
     * @param monthlyPlanSummary
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(MonthlyPlanSummary monthlyPlanSummary) {
        try {
            MonthlyPlanSummary monthlyPlanSummary1 = monthlyPlanSummaryService.selectByPrimaryKey(monthlyPlanSummary.getmId());
            if (monthlyPlanSummary1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(monthlyPlanSummary1);
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<MonthlyPlanSummary> list = monthlyPlanSummaryService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list, monthlyPlanSummaryService.count());
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改全部字段
     *
     * @param monthlyPlanSummary
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody MonthlyPlanSummary monthlyPlanSummary) {
        try {
            return monthlyPlanSummaryService.updateByPrimaryKey(monthlyPlanSummary) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据时间查询
     *
     * @return
     */
    @GetMapping("/selectDate")
    public Result selectDate(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, String beforeDate, String afterDate) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<MonthlyPlanSummary> list = monthlyPlanSummaryService.selectDate(beforeDate, afterDate);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list, monthlyPlanSummaryService.counts(beforeDate, afterDate, null, 0));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据操作员查询
     *
     * @return
     */
    @GetMapping("/selectmName")
    public Result selectmName(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize, String mName) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<MonthlyPlanSummary> list = monthlyPlanSummaryService.selectmName(mName);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list, monthlyPlanSummaryService.counts(null, null, mName, 0));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 根据部门查询
     *
     * @return
     */
    @GetMapping("/selectdId")
    public Result selectdId(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize,int dId) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<MonthlyPlanSummary> list = monthlyPlanSummaryService.selectdId(dId);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list, monthlyPlanSummaryService.counts(null, null, null, dId));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
}
