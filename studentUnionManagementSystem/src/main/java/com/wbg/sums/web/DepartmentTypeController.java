package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.dto.Result;
import com.wbg.sums.entity.DepartmentType;
import com.wbg.sums.service.DepartmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/departmentType")
public class DepartmentTypeController {
    @Autowired
    private DepartmentTypeService departmentTypeService;
    /**
     * 根据dId删除
     *
     * @param departmentType
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(DepartmentType departmentType) {
        try {
        return departmentTypeService.deleteByPrimaryKey(departmentType.getdId()) > 0 ? new Result().successMessage("删除成功") : new Result("修改失败");
        }
        catch (Exception ex) {
            return new Result().error ("出错,检查是否有依赖，再重试！");
        }
    }

    /**
     * 添加对象DepartmentType
     *
     * @param departmentType
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody DepartmentType departmentType) {
        try {
            return departmentTypeService.insert(departmentType) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error ("出错,请重试！");
        }

    }

    /**
     * 根据dId查找对象  最多只能返回一个对象
     *
     * @param departmentType
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(DepartmentType departmentType) {
        try {
            DepartmentType departmentType1 = departmentTypeService.selectByPrimaryKey(departmentType.getdId());
            if (departmentType1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(departmentType1);
            }
        } catch (Exception ex) {
            return new Result().error ("出错,请重试！");
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
            PageHelper pageHelper=new PageHelper();
            pageHelper.startPage(pageNum,pageSize);
            List<DepartmentType> list = departmentTypeService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,departmentTypeService.count(""));
            }
        } catch (Exception ex) {
            return new Result().error ("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改全部字段
     *
     * @param departmentType
     * @return
     */
    @PostMapping("/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody DepartmentType departmentType) {
        try {
            return departmentTypeService.updateByPrimaryKey(departmentType) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 根据d_id修改顺序
     *
     * @param departmentType
     * @return
     */
    @GetMapping("/updateSort")
    public Result updateSort(DepartmentType departmentType) {
        try {
            return departmentTypeService.updateByPrimaryKey(departmentType) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result("出错,请重试！");
        }
    }
    /**
     * 根据a_id修改状态
     *
     * @param departmentType
     * @return
     */
    @GetMapping("/updateStatus")
    public Result updateStatus(DepartmentType departmentType) {
        try {
            return departmentTypeService.updateStatus(departmentType.getdId(),departmentType.getStatus()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result("出错,请重试！");
        }
    }
    /**
     * 根据dName查询是否存在该名称
     *
     * @param departmentType
     * @return
     */
    @GetMapping("/selectName")
    public Result selectName(DepartmentType departmentType) {
        try {
            return departmentTypeService.selectName(departmentType.getdName()) > 0 ? new Result("名称已存在") : new Result().success("可以添加");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 根据Status查询全部
     *
     * @param departmentType
     * @return
     */
    @GetMapping("/selectAllStatus")
    public Result selectAllStatus(DepartmentType departmentType,@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            PageHelper pageHelper=new PageHelper();
            pageHelper.startPage(pageNum,pageSize);
            List<DepartmentType> list=departmentTypeService.selectAllStatus(departmentType.getStatus());
            if(list==null){
                return new Result().successMessage("无数据");
            }else{
                return new Result().success(list,departmentTypeService.count(""));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 根据dName模糊查询
     *
     * @param departmentType
     * @return
     */
    @GetMapping("/selectAllVague")
    public Result selectAllVague(DepartmentType departmentType) {
        try {
            List<DepartmentType> list=departmentTypeService.selectAllVague(departmentType.getdName());
            if(list==null){
                return new Result().successMessage("无数据");
            }else{
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result("出错,请重试！");
        }
    }
    /**
     * 根据Status查询状态，只返回两个字段
     *
     * @return
     */
    @GetMapping("/iSelectAllStatus")
    public Result iSelectAllStatus() {
        try {
            List<DepartmentType> list=departmentTypeService.iselectAllStatus("true");
            if(list==null){
                return new Result().successMessage("无数据");
            }else{
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result() .error("出错,请重试！");
        }
    }

}
