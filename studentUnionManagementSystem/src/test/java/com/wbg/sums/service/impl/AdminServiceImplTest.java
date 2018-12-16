package com.wbg.sums.service.impl;

import com.wbg.sums.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
public class AdminServiceImplTest {
    @Autowired
    AdminServiceImpl adminService;
    @Test
    public void deleteByPrimaryKey() {
        System.out.println("as");
        System.out.println(makeImagePath("A:/file/notic", "asdsa.txt"));
    }
    public static String makeImagePath (String basePath, String fileName){
        Date date = new Date();
        String[] filename = simpleFile(fileName);
        return String.format("%s%s%s%supload_%s_%s.%s",
                basePath,
                File.separator,
                new SimpleDateFormat("yyyyMMdd").format(date),
                File.separator,
                filename[0],
                new SimpleDateFormat("hhmmss").format(date),
                filename[1]
        );
    }
    public static String[] simpleFile (String file){
        int sum = file.lastIndexOf(".");
        return new String[]{
                file.substring(0, sum),
                file.substring(sum + 1)
        };
    }
    @Test
    public void insert() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    // mockit

    @Test
    public void selectAll() {

        for (Admin admin : adminService.selectAll()) {
            System.out.println(admin);

        }
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void updatePassword() {
    }

    @Test
    public void selectUserName() {
    }

    @Test
    public void updateStatus() {
    }

    @Test
    public void selectAllVague() {
    }

    @Test
    public void selectAllStatus() {
    }
}