package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.util.Result;
import com.wbg.sums.entity.DepartmentalExcellenceRecord;
import com.wbg.sums.service.DepartmentalExcellenceRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departmentalExcellenceRecord")
public class DepartmentalExcellenceRecordController {
    @Autowired
    private DepartmentalExcellenceRecordService departmentalExcellenceRecordService;

    /**
     * 根据dId删除
     * 要求转入 dId
     * @param departmentalExcellenceRecord
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    @RequiresRoles("admin")
    public Result deleteByPrimaryKey(DepartmentalExcellenceRecord departmentalExcellenceRecord) {
        try {
            return departmentalExcellenceRecordService.deleteByPrimaryKey(departmentalExcellenceRecord.getdId()) > 0 ? new Result().successMessage("删除成功") : new Result("删除失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 添加对象DepartmentalExcellenceRecord
     *
     * @param departmentalExcellenceRecord
     * @return
     */
    @PostMapping("/insert")
    @RequiresPermissions({"insert"})
    public Result insert(@RequestBody DepartmentalExcellenceRecord departmentalExcellenceRecord) {
        try {
            return departmentalExcellenceRecordService.insert(departmentalExcellenceRecord) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据aid查找对象  最多只能返回一个对象
     *
     * @param departmentalExcellenceRecord
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(DepartmentalExcellenceRecord departmentalExcellenceRecord) {
        try {
            DepartmentalExcellenceRecord departmentalExcellenceRecord1 = departmentalExcellenceRecordService.selectByPrimaryKey(departmentalExcellenceRecord.getdId());
            if (departmentalExcellenceRecord1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(departmentalExcellenceRecord1);
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
    public Result selectAll(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<DepartmentalExcellenceRecord> list = departmentalExcellenceRecordService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,departmentalExcellenceRecordService.count());
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改全部字段
     *
     * @param departmentalExcellenceRecord
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey" )
    @RequiresPermissions({"update"})
    public Result updateByPrimaryKey(@RequestBody DepartmentalExcellenceRecord departmentalExcellenceRecord) {
        try {
            return departmentalExcellenceRecordService.updateByPrimaryKey(departmentalExcellenceRecord) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 查询所有数据
     *
     * @return
     */
    @GetMapping("/selects")
    public Result selects(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize, String dName, String beforeDate, String afterDate) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<DepartmentalExcellenceRecord> list = departmentalExcellenceRecordService.selects(dName, beforeDate, afterDate);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,departmentalExcellenceRecordService.counts(dName, beforeDate, afterDate));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

}
