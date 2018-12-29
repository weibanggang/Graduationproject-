package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.dto.Result;
import com.wbg.sums.entity.ClassTable;
import com.wbg.sums.service.ClassTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classTable")
public class ClassTableController {
    @Autowired
    private ClassTableService classTableService;

    /**
     * 根据cId删除
     *
     * @param classTable
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(ClassTable classTable) {
        try {
        return classTableService.deleteByPrimaryKey(classTable.getcId()) > 0 ? new Result().successMessage("删除成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error ("出错,检查是否有依赖，再重试！");
        }
    }

    /**
     * 添加对象ClassTable
     *
     * @param classTable
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody ClassTable classTable) {
        try {
            return classTableService.insert(classTable) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据aid查找对象  最多只能返回一个对象
     *
     * @param cId
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(int cId) {
        try {
            ClassTable classTable1 = classTableService.selectByPrimaryKey(cId);
            if (classTable1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(classTable1,1);
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
            List<ClassTable> list = classTableService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,classTableService.count(""));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 返回不重复数组
     *
     * @return
     */
    @GetMapping("/selectGroupBy")
    public Result selectGroupBy() {
        try {
            List<ClassTable> list = classTableService.selectGroupBy();
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
     * 根据a_id修改全部字段
     *
     * @param classTable
     * @return
     */
    @PostMapping("/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody ClassTable classTable) {
        try {
            return classTableService.updateByPrimaryKey(classTable) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }


    /**
     * 根据j_id修改c_name
     *
     * @param classTable
     * @return
     */
    @GetMapping("/updateName")
    public Result updateName(ClassTable classTable) {
        try {
            return classTableService.updateName(classTable.getcId(),classTable.getcName()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据c_id修改状态
     *
     * @param classTable
     * @return
     */
    @GetMapping("/updateStatus")
    public Result updateStatus(ClassTable classTable) {
        try {
            return classTableService.updateStatus(classTable.getcId(),classTable.getStatus()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据班主任cHeadmasterName查询
     *
     * @paramcHeadmasterName
     * @return
     */
    @GetMapping("/selectByName")
    public Result selectByName(String cHeadmasterName) {
        try {
            List<ClassTable> list = classTableService.selectByName(cHeadmasterName);
            if (list == null) {
                return new Result().successMessage("无数据！");
            } else {
                return new Result().success(list,classTableService.countByName(cHeadmasterName));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据status  查询  返回全部
     *
     * @param status
     * @return
     */
    @GetMapping("/selectAllStatus")
    public Result selectAllStatus(String status, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            if ("all".equals(status)) {
                status = "";
            }
            PageHelper.startPage(pageNum,pageSize);
            List<ClassTable> list = classTableService.selectAllStatus(status);
            if (list == null) {
                return new Result().successMessage("无数据！");
            } else {
                return new Result().success(list,classTableService.count(status));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据status 查询  只返回两个字段
     *
     * @return
     */
    @GetMapping("/iSelectAllStatus")
    public Result iSelectAllStatus() {
        try {
            List<ClassTable> list = classTableService.iselectAllStatus("true");
            if (list == null) {
                return new Result().successMessage("无数据！");
            } else {
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }


}
