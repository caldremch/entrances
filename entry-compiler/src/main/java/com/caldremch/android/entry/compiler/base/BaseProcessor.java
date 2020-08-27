package com.caldremch.android.entry.compiler.base;

import java.util.Map;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;

/**
 * @author Caldremch
 * @date 2020-08-27 13:53
 * @email caldremch@163.com
 * @describe
 **/
public abstract class BaseProcessor extends AbstractProcessor {

    protected Filer filter;
    protected Logger logger;
    protected Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        filter = processingEnvironment.getFiler();
        logger = new Logger(processingEnvironment.getMessager());
        logger.info("EntryProcessor init.......");
        elementUtils = processingEnvironment.getElementUtils();
        Map<String, String> options = processingEnvironment.getOptions(); //注解器配置参数

    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
