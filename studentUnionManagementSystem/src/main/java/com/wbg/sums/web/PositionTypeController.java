package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.util.Result;
import com.wbg.sums.entity.PositionType;
import com.wbg.sums.service.PositionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/positionType")
public class PositionTypeController {
    @Autowired
    private PositionTypeService positionTypeService;

    /**
     * 根据pId删除
     *
     * @param positionType
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(PositionType positionType) {
        try {
            return positionTypeService.deleteByPrimaryKey(positionType.getpId()) > 0 ? new Result().successMessage("删除成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 添加对象PositionType
     *
     * @param positionType
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody PositionType positionType) {
        try {
            return positionTypeService.insert(positionType) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据pId查找对象  最多只能返回一个对象
     *
     * @param pId
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(int pId) {
        try {
            PositionType positionType1 = positionTypeService.selectByPrimaryKey(pId);
            if (positionType1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(positionType1);
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
            PageHelper pageHelper=new PageHelper();
            pageHelper.startPage(pageNum,pageSize);
            List<PositionType> list = positionTypeService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,positionTypeService.count(""));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改全部字段
     *
     * @param positionType
     * @return
     */
    @PostMapping("/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody PositionType positionType) {
        try {
            return positionTypeService.updateByPrimaryKey(positionType) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }


    /**
     * 根据p_id修改状态
     *
     * @param positionType
     * @return
     */
    @GetMapping("/updateStatus")
    public Result updateStatus(PositionType positionType) {
        try {
            return positionTypeService.updateStatus(positionType.getpId(), positionType.getStatus()) > 0 ? new Result().successMessage("修改成功") : new Result().error("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改排序Sort
     *
     * @param positionType
     * @return
     */
    @PostMapping("/updateSort")
    public Result updateSort(@RequestBody PositionType positionType) {
        try {
            return positionTypeService.updateSort(positionType.getpId(), positionType.getpSort()) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 根据status查询全部
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
            PageHelper pageHelper=new PageHelper();
            pageHelper.startPage(pageNum,pageSize);
            List<PositionType> list = positionTypeService.selectAllStatus(status);
            if (list == null) {
                return new Result().successMessage("无数据！");
            } else {
                return new Result().success(list,positionTypeService.count(status));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据status查询  只要两个字段
     *
     * @return
     */
    @GetMapping("/iSelectAllStatus")
    public Result iSelectAllStatus() {
        try {
            List<PositionType> list = positionTypeService.iselectAllStatus("true");
            if (list == null) {
                return new Result().successMessage("无数据！");
            } else {
                return new Result().success(list);
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据pName模糊查询
     *
     * @param positionType
     * @return
     */
    @GetMapping("/selectAllVague")
    public Result selectAllVague(PositionType positionType) {
        try {
            List<PositionType> list = positionTypeService.selectAllVague(positionType.getpName());
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
