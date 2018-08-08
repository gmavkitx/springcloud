package com.kingboy.mybatis.generator;

import java.util.Arrays;
import java.util.List;

/**
 * 配置定义.
 * <p></p>
 *
 * @author kingboy - KingBoyWorld@163.com
 * @since 2018/8/7 00:38
 */
public class GeneralConfiguration {

    /**
     * 数据库连接配置
     */
    public static String USER_NAME = "root";
    public static String PASSWORD = "123456";
    public static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/provider?characterEncoding=utf8";

    /**
     * JsonFormat注解中的日期格式
     */
    public static String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * 要逆向生成的表,支持多个表的生成
     */
    public static List<String> TABLES = Arrays.asList("user");

    /**
     * 作者信息
     */
    public static String AUTHOR = "KingBoy - KingBoyWorld@163.com";

    /**
     * 输出位置
     */
    public static String OUTPUT_DIR = System.getProperty("user.home") + "/Desktop";

}
