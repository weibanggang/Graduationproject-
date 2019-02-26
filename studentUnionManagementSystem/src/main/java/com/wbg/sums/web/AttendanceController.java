package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.util.Result;
import com.wbg.sums.entity.Attendance;
import com.wbg.sums.service.AttendanceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    /**
     * 根据aId删除
     * 要求转入 aId
     * @param attendance
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    @RequiresRoles("admin")
    public Result deleteByPrimaryKey(Attendance attendance) {
        try {
            return attendanceService.deleteByPrimaryKey(attendance.getaId()) > 0 ? new Result().successMessage("删除成功") : new Result("删除失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 添加对象Attendance
     *
     * @param attendance
     * @return
     */
    @PostMapping("/insert")
    @RequiresPermissions({"insert"})
    public Result insert(@RequestBody Attendance attendance) {
        try {
            return attendanceService.insert(attendance) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据pid查找对象  最多只能返回一个对象
     *
     * @param attendance
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(Attendance attendance) {
        try {
            Attendance attendance1 = attendanceService.selectByPrimaryKey(attendance.getaId());
            if (attendance1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(attendance1);
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
            PageHelper.startPage(pageNum,pageSize);
            List<Attendance> list = attendanceService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,attendanceService.count());
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改全部字段
     *
     * @param attendance
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey" )
    @RequiresPermissions({"update"})
    public Result updateByPrimaryKey(@RequestBody Attendance attendance) {
        try {
            return attendanceService.updateByPrimaryKey(attendance) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
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
    public Result selects(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize,String aMName, String dName, String mName, String beforeDate, String afterDate) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Attendance> list = attendanceService.selects(aMName,dName,mName,beforeDate,afterDate);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,attendanceService.counts(aMName,dName,mName,beforeDate,afterDate));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

}
