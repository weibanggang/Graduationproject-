package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.dto.Result;
import com.wbg.sums.entity.Notic;
import com.wbg.sums.service.NoticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/notic")

public class NoticController {
    @Autowired
    private NoticService noticService;
    /**
     * 根据aId删除
     * 要求转入 aId
     * @param notic
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(Notic notic) {
        try {
            return noticService.deleteByPrimaryKey(notic.getnId()) > 0 ? new Result().successMessage("删除成功") : new Result("删除失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 添加对象Notic
     *
     * @param notic
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Notic notic ) {
        try {
            /*request.getSession().getAttribute("user");*/
            notic.setmName("小邦哥");
            return noticService.insert(notic) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据aid查找对象  最多只能返回一个对象
     *
     * @param notic
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(Notic notic) {
        try {
            Notic notic1 = noticService.selectByPrimaryKey(notic.getnId());
            if (notic1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(notic1);
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
            List<Notic> list = noticService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,noticService.count(""));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改全部字段
     *
     * @param notic
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey" )
    public Result updateByPrimaryKey(@RequestBody Notic notic) {
        try {
            return noticService.updateByPrimaryKey(notic) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }
    /**
     * 根据nid修改状态Status
     *
     * @param notic
     * @return
     */
    @GetMapping("/updateStatus")
    public Result updateStatus(Notic notic) {
        try {
            return noticService.updateStatus(notic.getnId(), notic.getStatus()) > 0 ? new Result().successMessage("修改成功！") : new Result("修改失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }


}
