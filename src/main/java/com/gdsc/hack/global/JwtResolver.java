package com.gdsc.hack.global;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER,ElementType.ANNOTATION_TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface  JwtResolver {
}
