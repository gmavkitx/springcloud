package com.kingboy.mybatis.generator;

/**
 * 包的配置.
 * <p></p>
 *
 * @author kingboy - KingBoyWorld@163.com
 * @since 2018/8/7 01:37
 */
public class PackageConfiguration {

    /**
     * 项目基础包
     */
    public static String PACKAGE = "com.kingboy.provider";

    /**
     * Controller包名
     */
    public static String CONTROLLER_PACKAGE = "web";

    /**
     * Service包名
     */
    public static String SERVICE_PACKAGE = "service";

    /**
     * ServiceImpl实现类包名
     */
    public static String SERVICEIMPL_PACKAGE = "service.impl";

    /**
     * Mapper包名
     */
    public static String MAPPER_PACKAGE = "mapper";

    /**
     * MapperXML包名
     */
    public static String MAPPERXML_PACKAGE = "xml";

    /**
     * DTO包名
     */
    public static String DTO_PACKAGE = "domain.dto";

    /**
     * PO包名
     */
    public static String PO_PACKAGE = "domain.po";

    /**
     * VO包名
     */
    public static String VO_PACKAGE = "domain.vo";

    /**
     * 是否根据模块进行分包，eg: com.company.service.User.java ==> com.company.service.user.User.java
     * 默认模块名称为表名去除下划线，转小写
     */
    public static Boolean DIVIDE_MODULE = true;


}
