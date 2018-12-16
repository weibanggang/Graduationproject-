package com.wbg.sums.web;
import com.github.pagehelper.PageHelper;
import com.wbg.sums.dto.MemberInfomationDto;
import com.wbg.sums.dto.Result;
import com.wbg.sums.entity.MemberInformation;
import com.wbg.sums.service.MemberInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memberInformation")
public class MemberInformationController {
    @Autowired
    private MemberInformationService memberInformationService;
    /**
     *  返回多表数据
     */
    @GetMapping
    public Result selectTableAll(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize){
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<MemberInfomationDto> list = memberInformationService.selectTableAll("");
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,memberInformationService.count(""));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 根据aId删除
     * 要求转入 aId
     * @param memberInfomation
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(MemberInformation memberInfomation) {
        try {
            return memberInformationService.deleteByPrimaryKey(memberInfomation.getmId()) > 0 ? new Result().successMessage("删除成功") : new Result("删除失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 添加对象MemberInformation
     *
     * @param memberInfomation
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody MemberInformation memberInfomation) {
        try {
            return memberInformationService.insert(memberInfomation) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据aid查找对象  最多只能返回一个对象
     *
     * @param memberInfomation
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(MemberInformation memberInfomation) {
        try {
            MemberInformation memberInfomation1 = memberInformationService.selectByPrimaryKey(memberInfomation.getmId());
            if (memberInfomation1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(memberInfomation1);
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
            List<MemberInformation> list = memberInformationService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,memberInformationService.count(""));
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
    @GetMapping("/iSelectName")
    public Result iSelectName(@RequestParam(defaultValue = "1")int pId) {
        try {
            List<MemberInformation> list = memberInformationService.iSelectName(pId);
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
     * @param memberInfomation
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey" )
    public Result updateByPrimaryKey(@RequestBody MemberInformation memberInfomation) {
        try {
            return memberInformationService.updateByPrimaryKey(memberInfomation) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }


    }
    /**
     * 根据工作编号查询名字
     * @return
     */
    @GetMapping("/iUserName")
    public Result iUserName(String mUser) {
        try {
            List<MemberInformation> list = memberInformationService.iUserName(mUser);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
}
