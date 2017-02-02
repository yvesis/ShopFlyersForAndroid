package com.yvesis.flyers.core.models;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by louly on 2017-01-30.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ItemCategory {
    String[] tags() default "";
    String category() default "all";


}
