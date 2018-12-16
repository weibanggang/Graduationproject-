package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.dao.ExchangeTableMapper;
import com.wbg.sums.dto.Result;
import com.wbg.sums.entity.ExchangeTable;
import com.wbg.sums.service.ExchangeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exchangeTable")

public class ExchangeTableController {
    @Autowired
    private ExchangeTableService exchangeTableService;

    /**
     * 根据eId删除
     *
     * @param exchangeTable
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(ExchangeTable exchangeTable) {
        try {
            return exchangeTableService.deleteByPrimaryKey(exchangeTable.geteId()) > 0 ? new Result().successMessage("删除成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error ("出错,检查是否有依赖，再重试！");
        }    }

    /**
     * 添加对象exchangeTableService
     *
     * @param exchangeTable
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody ExchangeTable exchangeTable) {
        try {
            return exchangeTableService.insert(exchangeTable) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据eId查找对象  最多只能返回一个对象
     *
     * @param exchangeTable
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(ExchangeTable exchangeTable) {
        try {
            ExchangeTable list = exchangeTableService.selectByPrimaryKey(exchangeTable.geteId());
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
     * 查询所有数据
     *
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<ExchangeTable> list = exchangeTableService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,exchangeTableService.count(""));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据e_id修改全部字段
     *
     * @param exchangeTable
     * @return
     */
    @PostMapping("/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody ExchangeTable exchangeTable) {
        try {
            return exchangeTableService.updateByPrimaryKey(exchangeTable) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据e_id修改状态Status
     *
     * @param exchangeTable
     * @return
     */
    @GetMapping("/updateStatus")
    public Result updateStatus(ExchangeTable exchangeTable) {
        try {
            return exchangeTableService.updateStatus(exchangeTable.geteId(),exchangeTable.getStatus()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据e_id修改顺序eSort
     *
     * @param exchangeTable
     * @return
     */
    @GetMapping("/updateSort")
    public Result updateSort(ExchangeTable exchangeTable) {
        try {
            return exchangeTableService.updateSort(exchangeTable.geteId(),exchangeTable.geteSort()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据e_id修改全部字段
     *
     * @param exchangeTable
     * @return
     */
    @GetMapping("/selectAllStatus")
    public Result selectAllStatus(ExchangeTable exchangeTable) {
        try {
           List<ExchangeTable> list= exchangeTableService.selectAllStatus(exchangeTable.getStatus());
       if(list==null){
           return new Result().successMessage("无数据");
       }else {
           return new Result().success(list);
       }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     *   根据状态查询 只查询两个字段
     *
     * @return
     */
    @GetMapping("/iSelectAllStatus")
    public Result iSelectAllStatus() {
        try {
            List<ExchangeTable> list= exchangeTableService.iselectAllStatus("true");
            if(list==null){
                return new Result().successMessage("无数据");
            }else {
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据eName模糊查询
     *
     * @param exchangeTable
     * @return
     */
    @GetMapping("/selectAllVague")
    public Result selectAllVague(ExchangeTable exchangeTable) {
        try {
            List<ExchangeTable> list= exchangeTableService.selectAllVague(exchangeTable.geteName());
            if(list==null){
                return new Result().successMessage("无数据");
            }else {
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }


}
