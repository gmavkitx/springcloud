package com.kingboy.provider.common.utils.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 时间校验.
 * <p>
 *     是否为过去的时间点
 * </p>
 *
 * @author KingBoy - KingBoyWorld@163.com
 * @since 2018-08-07 02:02
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastTimeValidate.class)
public @interface PastDate {

    /**
     * 错误信息
     */
    String message() default "日期校验失败";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
