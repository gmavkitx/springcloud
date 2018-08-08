package com.kingboy.provider.common.utils.validate;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

/**
 * PastDate校验规则.
 * <p></p>
 *
 * @author KingBoy - KingBoyWorld@163.com
 * @since 2018-08-07 02:02
 */
@Slf4j
public class PastTimeValidate implements ConstraintValidator<PastDate, LocalDateTime> {

    @Override
    public void initialize(PastDate pastDate) {
        log.info("init enum PastDate");
    }

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext context) {
        return localDateTime.isBefore(LocalDateTime.now()) ? true : false;
    }
}
