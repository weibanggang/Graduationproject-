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
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/leaveRecord")
public class LeaveRecord {
    @RequestMapping(value = "/upload", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public Result upload(@RequestPart("file") MultipartFile multipartFile, HttpServletRequest request){
        //取得相对路径
        String basePath = request.getServletContext().getRealPath(File.separator+"leaveRecord"+File.separator);
        String rekativePath;
        try {
            rekativePath = makeImagePath(multipartFile.getOriginalFilename());
            File file = new File((basePath+rekativePath));
            file.getParentFile().mkdirs();
            multipartFile.transferTo(file);
        } catch (IOException e) {
            return new Result("上传失败");
        }
        return new Result(1224,"文件上传成功！",rekativePath,1);
    }
    public String makeImagePath (String fileName){
        Date date = new Date();
        String[] filename = simpleFile(fileName);
        return String.format("%s%s%s%s%s.%s",
                File.separator,
                new SimpleDateFormat("yyyyMMdd").format(date),
                File.separator,
                filename[0],
                new SimpleDateFormat("hhmmss").format(date),
                filename[1]
        );
    }
    public String[] simpleFile (String file){
        int sum = file.lastIndexOf(".");
        return new String[]{
                file.substring(0, sum),
                file.substring(sum + 1)
        };
    }
}
