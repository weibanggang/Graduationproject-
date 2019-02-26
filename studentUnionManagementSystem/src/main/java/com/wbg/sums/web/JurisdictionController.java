package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.util.Result;
import com.wbg.sums.entity.Jurisdiction;
import com.wbg.sums.service.JurisdictionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jurisdiction")
public class JurisdictionController {
    @Autowired
    private JurisdictionService jurisdictionService;

    /**
     * jId
     *
     * @param jId
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    @RequiresRoles("admin")
    public Result deleteByPrimaryKey(int jId) {
        try {
        return jurisdictionService.deleteByPrimaryKey(jId) > 0 ? new Result().successMessage("删除成功") : new Result("修改失败");
     }
        catch (Exception ex) {
        return new Result().error ("出错,检查是否有依赖，再重试！");
    }
    }

    /**
     * 添加对象Jurisdiction
     *
     * @param jurisdiction
     * @return
     */
    @PostMapping("/insert")
    @RequiresPermissions({"insert"})
    public Result insert(@RequestBody Jurisdiction jurisdiction) {
        try {
            return jurisdictionService.insert(jurisdiction) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据aid查找对象  最多只能返回一个对象
     *
     * @param jId
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(int  jId) {
        try {
            Jurisdiction jurisdiction1 = jurisdictionService.selectByPrimaryKey(jId);
            if (jurisdiction1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(jurisdiction1);
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
            List<Jurisdiction> list = jurisdictionService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,jurisdictionService.count("",""));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改全部字段
     *
     * @param jurisdiction
     * @return
     */
    @PostMapping("/updateByPrimaryKey")
    @RequiresPermissions({"update"})
    public Result updateByPrimaryKey(@RequestBody Jurisdiction jurisdiction) {
        try {
            return jurisdictionService.updateByPrimaryKey(jurisdiction) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据jId修改jName
     *
     * @param jurisdiction
     * @return
     */
    @PostMapping("/updateName")
    @RequiresPermissions({"update"})
    public Result updateName(Jurisdiction jurisdiction) {
        try {
            return jurisdictionService.updateName(jurisdiction.getjId(),jurisdiction.getjName()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }






    /**
     * 根据jName模糊查询
     *
     * @param jName
     * @return
     */
    @GetMapping("/selectName")
    public Result selectName(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize,String jName) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<Jurisdiction> list=jurisdictionService.selectName(jName);
            if(list==null){
                return new Result().successMessage("无数据");
            }else{
                return new Result().success(list,jurisdictionService.count(jName,""));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
}
