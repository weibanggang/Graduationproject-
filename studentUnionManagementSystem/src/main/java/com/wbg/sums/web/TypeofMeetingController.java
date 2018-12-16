package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.dto.Result;
import com.wbg.sums.entity.TypeofMeeting;
import com.wbg.sums.service.TypeofMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typeofMeeting")
public class TypeofMeetingController {
    @Autowired
    private TypeofMeetingService typeofMeetingService;

    /**
     * 根据tid删除
     *
     * @param typeofMeeting
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(TypeofMeeting typeofMeeting) {
        try {
        return typeofMeetingService.deleteByPrimaryKey(typeofMeeting.gettId()) > 0 ? new Result().successMessage("删除成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error ("出错,检查是否有依赖，再重试！");
        }
    }

    /**
     * 添加对象TypeofMeeting
     *
     * @param typeofMeeting
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody TypeofMeeting typeofMeeting) {
        try {
            return typeofMeetingService.insert(typeofMeeting) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据aid查找对象  最多只能返回一个对象
     *
     * @param typeofMeeting
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(TypeofMeeting typeofMeeting) {
        try {
            TypeofMeeting typeofMeeting1 = typeofMeetingService.selectByPrimaryKey(typeofMeeting.gettId());
            if (typeofMeeting1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(typeofMeeting1);
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
            List<TypeofMeeting> list = typeofMeetingService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,typeofMeetingService.count(""));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改全部字段
     *
     * @param typeofMeeting
     * @return
     */
    @PostMapping("/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody TypeofMeeting typeofMeeting) {
        try {
            return typeofMeetingService.updateByPrimaryKey(typeofMeeting) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }


    /**
     * 根据tId修改状态status
     *
     * @param typeofMeeting
     * @return
     */
    @GetMapping("/updateStatus")
    public Result updateStatus(TypeofMeeting typeofMeeting) {
        try {
            return typeofMeetingService.updateStatus(typeofMeeting.gettId(), typeofMeeting.getStatus()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据stataus 查询全部字段
     *
     * @param typeofMeeting
     * @return
     */
    @GetMapping("/selectAllStatus")
    public Result selectAllStatus(TypeofMeeting typeofMeeting) {
        try {
            List<TypeofMeeting> list = typeofMeetingService.selectAllStatus(typeofMeeting.getStatus());
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
     * 根据status 查询  只返回两个字段
     *
     * @return
     */
    @GetMapping("/iselectAllStatus")
    public Result iselectAllStatus() {
        try {
            List<TypeofMeeting> list = typeofMeetingService.iselectAllStatus("");
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
     * 根据t_id修改顺序
     *
     * @param typeofMeeting
     * @return
     */
    @GetMapping("/updateSort")
    public Result updateSort(TypeofMeeting typeofMeeting) {
        try {
            return typeofMeetingService.updateSort(typeofMeeting.gettId(), typeofMeeting.gettSort()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据tName模糊查询
     *
     * @param typeofMeeting
     * @return
     */
    @GetMapping("/selectAllVague")
    public Result selectAllVague(TypeofMeeting typeofMeeting) {
        try {
            List<TypeofMeeting> list = typeofMeetingService.selectAllVague(typeofMeeting.gettName());
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
