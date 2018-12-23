package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.dto.Result;
import com.wbg.sums.entity.MinutesOfTheMeeting;
import com.wbg.sums.service.MinutesOfTheMeetingService;
import com.wbg.sums.service.MinutesOfTheMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/minutesOfTheMeeting")

public class MinutesOfTheMeetingController {
    @Autowired
    private MinutesOfTheMeetingService minutesOfTheMeetingService;
    /**
     * 根据aId删除
     * 要求转入 mId
     * @param minutesOfTheMeeting
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(MinutesOfTheMeeting minutesOfTheMeeting) {
        try {
            return minutesOfTheMeetingService.deleteByPrimaryKey(minutesOfTheMeeting.getmId()) > 0 ? new Result().successMessage("删除成功") : new Result("删除失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 添加对象MinutesOfTheMeeting
     *
     * @param minutesOfTheMeeting
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody MinutesOfTheMeeting minutesOfTheMeeting) {
        try {
            minutesOfTheMeeting.setmName("设置session");
            return minutesOfTheMeetingService.insert(minutesOfTheMeeting) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据pid查找对象  最多只能返回一个对象
     *
     * @param minutesOfTheMeeting
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(MinutesOfTheMeeting minutesOfTheMeeting) {
        try {
            MinutesOfTheMeeting minutesOfTheMeeting1 = minutesOfTheMeetingService.selectByPrimaryKey(minutesOfTheMeeting.getmId());
            if (minutesOfTheMeeting1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(minutesOfTheMeeting1);
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
            List<MinutesOfTheMeeting> list = minutesOfTheMeetingService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,minutesOfTheMeetingService.count());
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改全部字段
     *
     * @param minutesOfTheMeeting
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey" )
    public Result updateByPrimaryKey(@RequestBody MinutesOfTheMeeting minutesOfTheMeeting) {
        try {
            return minutesOfTheMeetingService.updateByPrimaryKey(minutesOfTheMeeting) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }


    }
}
