package com.wbg.sums.web.fileupdown;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBUtil {

    public static String makeImagePath(String fileName) {
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

    public static String[] simpleFile(String file) {
        int sum = file.lastIndexOf(".");
        return new String[]{
                file.substring(0, sum),
                file.substring(sum + 1)
        };
    }
}
