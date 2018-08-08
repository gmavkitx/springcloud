package com.kingboy.mybatis;

import com.kingboy.mybatis.generator.GeneralConfiguration;
import com.kingboy.mybatis.generator.Generator;

/**
 * 生成器主方法.
 * <p></p>
 *
 * @author kingboy - KingBoyWorld@163.com
 * @since 2018/8/7 00:38
 */
public class Main {

    /**
     * 修改generator/Contants配置类，生成自定义代码.
     * <p></p>
     * @param args
     * @return void
     * @author kingboy
     * @since 2018/8/7 00:45
     */
    public static void main(String[] args) {
        GeneralConfiguration.TABLES.forEach(table -> Generator.createByOneTable(table));
    }

}
