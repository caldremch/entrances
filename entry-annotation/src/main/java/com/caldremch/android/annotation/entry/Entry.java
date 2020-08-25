package com.caldremch.android.annotation.entry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Caldremch
 * @date 2020-08-23
 * @email caldremch@163.com
 * @describe Annotation for the entry
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface Entry {
}
