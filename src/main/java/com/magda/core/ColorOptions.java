package com.magda.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE}) //on class level
public @interface ColorOptions {
    public enum Color {
        GREEN, YELLOW, BLUE, RED, WHITE, GREY, BROWN
    }

    Color color() default Color.WHITE;

}
