package com.kingboy.provider.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * MybatisPlus属性自动注入配置.
 * <p></p>
 *
 * @author KingBoy - KingBoyWorld@163.com
 * @since 2018-08-07 02:02
 */
@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 当前操作人 TODO 实际项目中设置为当前登录的用户
     */
    private String operator = "KingBoy";

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        setFieldValByName("createBy", operator, metaObject);
        setFieldValByName("deleteFlag", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        setFieldValByName("updateBy", operator, metaObject);
    }

}
