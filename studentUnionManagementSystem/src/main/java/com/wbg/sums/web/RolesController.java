package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.entity.Roles;
import com.wbg.sums.service.RolesService;
import com.wbg.sums.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    private RolesService rolesService;
    /**
     * 根据rId删除
     * 要求转入 rId
     * @param roles
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(Roles roles) {
        try {
            return rolesService.deleteByPrimaryKey(roles.getrId()) > 0 ? new Result().successMessage("删除成功") : new Result("删除失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 添加对象Roles
     *
     * @param roles
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Roles roles) {
        try {
            return rolesService.insert(roles) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据pid查找对象  最多只能返回一个对象
     *
     * @param roles
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(Roles roles) {
        try {
            Roles Roles1 = rolesService.selectByPrimaryKey(roles.getrId());
            if (Roles1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(Roles1);
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
            List<Roles> list = rolesService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,rolesService.count());
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
    @GetMapping("/iSelectAll")
    public Result iSelectAll() {
        try {
            List<Roles> list = rolesService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,rolesService.count());
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 根据a_id修改全部字段
     *
     * @param roles
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey" )
    public Result updateByPrimaryKey(@RequestBody Roles roles) {
        try {
            return rolesService.updateByPrimaryKey(roles) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

}
