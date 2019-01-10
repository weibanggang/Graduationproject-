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
@RequestMapping("/memberInformation")
public class MemberInformation {
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public Result upload(@RequestPart("file") MultipartFile multipartFile, HttpServletRequest request) {

        if (!multipartFile.getContentType().contains("image/")) {
            return new Result("只能是图片");
        }
        if (multipartFile.getSize() > 1024 * 1024 * 5) {
            return new Result("图片只能是5M以下！");
        }
        //取得相对路径
        String basePath = request.getServletContext().getRealPath(File.separator + "memberInformation" + File.separator);
        String rekativePath;
        try {
            rekativePath = new DBUtil().makeImagePath(multipartFile.getOriginalFilename());
            File file = new File((basePath + rekativePath));
            file.getParentFile().mkdirs();
            multipartFile.transferTo(file);
        } catch (IOException e) {
            return new Result("上传失败");
        }
        return new Result(1224, "文件上传成功！", "/memberInformation"+rekativePath, 1);
    }
}
