package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.dao.GeUserPermissionsAssociateMapper;
import com.wbg.sums.dao.PermissionsMapper;
import com.wbg.sums.dto.RolesAndPermissions;
import com.wbg.sums.entity.GeUserPermissionsAssociate;
import com.wbg.sums.entity.Permissions;
import com.wbg.sums.entity.Roles;
import com.wbg.sums.service.RolesService;
import com.wbg.sums.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesAndPermissionsController {
    @Autowired
    private RolesService rolesService;

    @Autowired
    private PermissionsMapper permissionsMapper;

    @Autowired
    private GeUserPermissionsAssociateMapper geUserPermissionsAssociateMapper;
    /**
     * 根据rId删除
     * 要求转入 rId
     * @param roles
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    @RequiresRoles("admin")
    public Result deleteByPrimaryKey(Roles roles) {
        try {
            return rolesService.deleteByPrimaryKey(roles.getrId()) > 0 ? new Result().successMessage("删除成功") : new Result("删除失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 添加权限关联
     *
     * @param geUserPermissionsAssociate
     * @return
     */
    @PostMapping("/gupam")
    @RequiresRoles({"admin"})
    public Result gupam(@RequestBody GeUserPermissionsAssociate geUserPermissionsAssociate) {
        try {
            return geUserPermissionsAssociateMapper.insert(geUserPermissionsAssociate) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 删除权限关联
     *
     * @param id
     * @return
     */
    @GetMapping("/gupamDel")
    @RequiresRoles({"admin"})
    public Result gupamDel(int id) {
        try {
            return geUserPermissionsAssociateMapper.deleteByPrimaryKey(id) > 0 ? new Result().successMessage("删除成功！") : new Result("删除失败！");
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
    @RequiresRoles({"admin"})
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
     * @param rid
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(int rid) {
        try {
            Roles Roles1 = rolesService.selectByPrimaryKey(rid);
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
     * 查询所有权限
     *
     * @return
     */
    @GetMapping("/selectPermissions")
    public Result permissions() {
        try {
            List<Permissions> list = permissionsMapper.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 查询权限与角色
     *
     */
    @GetMapping("/PermissionsSelect")
    public Result PermissionsSelect(String rName,@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<RolesAndPermissions> list = rolesService.rolesAndpermissions(rName);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,rolesService.counts(rName));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 查询所有角色
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
    @RequiresPermissions({"update"})
    public Result updateByPrimaryKey(@RequestBody Roles roles) {
        try {
            return rolesService.updateByPrimaryKey(roles) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

}
