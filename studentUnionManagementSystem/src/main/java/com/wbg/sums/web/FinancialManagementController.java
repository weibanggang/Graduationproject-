package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.dto.Result;
import com.wbg.sums.entity.FinancialManagement;
import com.wbg.sums.service.FinancialManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financialManagement")
public class FinancialManagementController {
    @Autowired
    private FinancialManagementService financialManagementService;

    /**
     * 根据fId删除
     * 要求转入 fId
     * @param financialManagement
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(FinancialManagement financialManagement) {
        try {
            return financialManagementService.deleteByPrimaryKey(financialManagement.getfId()) > 0 ? new Result().successMessage("删除成功") : new Result("删除失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 添加对象FinancialManagement
     *
     * @param financialManagement
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody FinancialManagement financialManagement) {
        try {
            financialManagement.setmName("设置session");
            return financialManagementService.insert(financialManagement) > 0 ? new Result().successMessage("记录成功！") : new Result("记录失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }
    //获取操作人

    @GetMapping("/selectGroupBymName")
    public Result  selectGroupBymName() {
        try {
            List<FinancialManagement> financialManagement1 = financialManagementService.selectGroupBymName();
            if (financialManagement1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(financialManagement1);
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 根据aid查找对象  最多只能返回一个对象
     *
     * @param financialManagement
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(FinancialManagement financialManagement) {
        try {
            FinancialManagement financialManagement1 = financialManagementService.selectByPrimaryKey(financialManagement.getfId());
            if (financialManagement1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(financialManagement1);
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
            List<FinancialManagement> list = financialManagementService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,financialManagementService.count());
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改全部字段
     *
     * @param financialManagement
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey" )
    public Result updateByPrimaryKey(@RequestBody FinancialManagement financialManagement) {
        try {
            return financialManagementService.updateByPrimaryKey(financialManagement) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
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
    public Result selects(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize,String mName, String fType, String beforeDate, String afterDate) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<FinancialManagement> list = financialManagementService.selects(mName,fType,beforeDate,afterDate);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,financialManagementService.counts(mName,fType,beforeDate,afterDate));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
}
