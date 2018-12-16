package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.dto.Result;
import com.wbg.sums.entity.Jurisdiction;
import com.wbg.sums.service.JurisdictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
     * @param jurisdiction
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(Jurisdiction jurisdiction) {
        try {
        return jurisdictionService.deleteByPrimaryKey(jurisdiction.getjId()) > 0 ? new Result().successMessage("删除成功") : new Result("修改失败");
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
     * @param jurisdiction
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(Jurisdiction jurisdiction) {
        try {
            Jurisdiction jurisdiction1 = jurisdictionService.selectByPrimaryKey(jurisdiction.getjId());
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
                return new Result().success(list,jurisdictionService.count(""));
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
    @GetMapping("/updateName")
    public Result updateName(Jurisdiction jurisdiction) {
        try {
            return jurisdictionService.updateName(jurisdiction.getjId(),jurisdiction.getjName()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据jId修改Status
     *
     * @param jurisdiction
     * @return
     */
    @GetMapping("/updateStatus")
    public Result updateStatus(Jurisdiction jurisdiction) {
        try {
            return jurisdictionService.updateStatus(jurisdiction.getjId(),jurisdiction.getStatus()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据Status查询   返回所有字段
     *
     * @param jurisdiction
     * @return
     */
    @GetMapping("/selectAllStatus")
    public Result selectAllStatus(Jurisdiction jurisdiction) {
        try {
          List<Jurisdiction> list=jurisdictionService.selectAllStatus(jurisdiction.getStatus());
          if(list==null){
              return new Result().successMessage("无数据");
          }else{
              return new Result().success(list);
          }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }



    /**
     * 根据Status查询  只返回两个字段
     *
     * @param jurisdiction
     * @return
     */
    @GetMapping("/iSelectAllStatus")
    public Result iSelectAllStatus() {
        try {
            List<Jurisdiction> list=jurisdictionService.iselectAllStatus("true");
            if(list==null){
                return new Result().successMessage("无数据");
            }else{
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据jName模糊查询
     *
     * @param jurisdiction
     * @return
     */
    @GetMapping("/selectAllVague")
    public Result selectAllVague(Jurisdiction jurisdiction) {
        try {
            List<Jurisdiction> list=jurisdictionService.selectAllVague(jurisdiction.getjName());
            if(list==null){
                return new Result().successMessage("无数据");
            }else{
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
}
