package com.wbg.sums.web.fileupdown;

import com.wbg.sums.dto.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/financialManagement")
public class FinancialManagement {
    @RequestMapping(value = "/upload", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public Result upload(@RequestPart("file") MultipartFile multipartFile, HttpServletRequest request){
        //取得相对路径
        String basePath = request.getServletContext().getRealPath(File.separator+"financialManagement"+File.separator);
        String rekativePath;
        try {
            rekativePath = new DBUtil().makeImagePath(multipartFile.getOriginalFilename());
            File file = new File((basePath+rekativePath));
            file.getParentFile().mkdirs();
            multipartFile.transferTo(file);
        } catch (IOException e) {
            return new Result("上传失败");
        }
        return new Result(1224,"文件上传成功！",rekativePath,1);
    }
}
