package com.wbg.sums.web;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbg.sums.dto.Result;
import com.wbg.sums.entity.Admin;
import com.wbg.sums.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 根据aId删除
     * 要求转入 aId
     * @param admin
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(Admin admin) {
        try {
        return adminService.deleteByPrimaryKey(admin.getaId()) > 0 ? new Result().successMessage("删除成功") : new Result("删除失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 添加对象Admin
     *
     * @param admin
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Admin admin) {
        try {
            return adminService.insert(admin) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据aid查找对象  最多只能返回一个对象
     *
     * @param admin
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(Admin admin) {
        try {
            Admin admin1 = adminService.selectByPrimaryKey(admin.getaId());
            if (admin1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(admin1);
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
            List<Admin> list = adminService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,adminService.count(""));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改全部字段
     *
     * @param admin
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey" )
    public Result updateByPrimaryKey(@RequestBody Admin admin) {
        try {
            return adminService.updateByPrimaryKey(admin) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }


    }

    /**
     * 根据aUsername 修改密码aPassword
     *
     * @param admin
     * @return
     */
    @GetMapping("/updatePassword")
    public Result updatePassword(Admin admin) {
        try {
            return adminService.updatePassword(admin.getaUsername(), admin.getaPassword()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据aUsername判断是否出现过该用户
     *
     * @param admin
     * @return
     */
    @GetMapping("/selectUserName")
    public Result selectUserName(Admin admin) {
        try {
            return adminService.selectUserName(admin.getaUsername()) > 0 ? new Result("用户名已存在") : new Result().successMessage("用户名可使用");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据aUsername修改状态Status
     *
     * @param admin
     * @return
     */
    @GetMapping("/updateStatus")
    public Result updateStatus(Admin admin) {
        try {
            return adminService.updateStatus(admin.getaUsername(), admin.getStatus()) > 0 ? new Result().successMessage("修改成功！") : new Result("修改失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据Username模糊查询
     *
     * @param admin
     * @return
     */
    @GetMapping("/selectAllVague")
    public Result selectAllVague(Admin admin) {
        try {
            List<Admin> list = adminService.selectAllVague(admin.getaUsername());
            if (list == null) {

            } else {
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
        return new Result();
    }

    /**
     * 根据Status查询全部
     *
     * @param admin
     * @return
     */
    @GetMapping("/selectAllStatus")
    public Result selectAllStatus(Admin admin,@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize) {
        try {
            PageHelper pageHelper=new PageHelper();
            pageHelper.startPage(pageNum,pageSize);
            List<Admin> list =   adminService.selectAllStatus(admin.getStatus());
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,adminService.count(""));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }


}
