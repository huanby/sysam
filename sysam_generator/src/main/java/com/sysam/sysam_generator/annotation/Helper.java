package com.sysam.sysam_generator.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ProjectName: sysam
 * @Package: com.sysam.sysam_generator.annotation
 * @ClassName: Helper
 * @Author: jibl
 * @Description:
 * @Date: 2021/2/4 17:29
 * @Version: 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Helper {
    @AliasFor(annotation = Component.class)
    String value() default "";
}

