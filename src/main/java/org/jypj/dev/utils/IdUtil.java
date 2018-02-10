package org.jypj.dev.utils;

import org.springframework.format.datetime.joda.LocalDateTimeParser;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * ID工具类
 *
 * @author yu_chen
 * @create 2018-01-31 14:32
 **/
public class IdUtil {

    public static void main(String[] args) {
        System.out.println(getTimeStamp());
    }
    /**
     * 得到UUID
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").trim();
    }


    /**
     * 根据时间戳生成文件
     *
     * @return
     */
    public static String getTimeStamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return simpleDateFormat.format(new Date());
    }
}
