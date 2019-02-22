package com.wbg.sums.web;

import com.github.pagehelper.PageHelper;
import com.wbg.sums.util.Result;
import com.wbg.sums.entity.PersonalMeritRecord;
import com.wbg.sums.service.PersonalMeritRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personalMeritRecord")

public class PersonalMeritRecordController {
    @Autowired
    private PersonalMeritRecordService personalMeritRecordService;

    /**
     * 根据aId删除
     * 要求转入 pId
     * @param personalMeritRecord
     * @return
     */
    @GetMapping("/deleteByPrimaryKey")
    public Result deleteByPrimaryKey(PersonalMeritRecord personalMeritRecord) {
        try {
            return personalMeritRecordService.deleteByPrimaryKey(personalMeritRecord.getpId()) > 0 ? new Result().successMessage("删除成功") : new Result("删除失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 添加对象PersonalMeritRecord
     *
     * @param personalMeritRecord
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody PersonalMeritRecord personalMeritRecord) {
        try {
            personalMeritRecord.setmName("设置session");
            return personalMeritRecordService.insert(personalMeritRecord) > 0 ? new Result().successMessage("添加成功！") : new Result("添加失败！");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }

    }

    /**
     * 根据pid查找对象  最多只能返回一个对象
     *
     * @param personalMeritRecord
     * @return
     */
    @GetMapping("/selectByPrimaryKey")
    public Result selectByPrimaryKey(PersonalMeritRecord personalMeritRecord) {
        try {
            PersonalMeritRecord personalMeritRecord1 = personalMeritRecordService.selectByPrimaryKey(personalMeritRecord.getpId());
            if (personalMeritRecord1 == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(personalMeritRecord1);
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
            List<PersonalMeritRecord> list = personalMeritRecordService.selectAll();
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,personalMeritRecordService.count(""));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }

    /**
     * 根据a_id修改全部字段
     *
     * @param personalMeritRecord
     * @return
     */
    @PostMapping(value = "/updateByPrimaryKey" )
    public Result updateByPrimaryKey(@RequestBody PersonalMeritRecord personalMeritRecord) {
        try {
            return personalMeritRecordService.updateByPrimaryKey(personalMeritRecord) > 0 ? new Result().successMessage("修改成功") : new Result("修改失败");
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
    /**
     * 查询所有数据
     *
     * @return
     */
    @GetMapping("/selectName")
    public Result selectName(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize, String lMName) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<PersonalMeritRecord> list = personalMeritRecordService.selectName(lMName);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,personalMeritRecordService.count(lMName));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }


    /**
     * 多个条件查询数据
     *
     * @return
     */
    @GetMapping("/selects")
    public Result selects(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize,String dName,String beforeDate, String afterDate) {
        try {
            PageHelper.startPage(pageNum,pageSize);
            List<PersonalMeritRecord> list = personalMeritRecordService.selects(dName,  beforeDate, afterDate);
            if (list == null) {
                return new Result().successMessage("无数据");
            } else {
                return new Result().success(list,personalMeritRecordService.counts(dName,beforeDate, afterDate));
            }
        } catch (Exception ex) {
            return new Result().error("出错,请重试！");
        }
    }
}
