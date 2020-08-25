package com.caldremch.android.entry.compiler.base

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Filer
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.util.Elements

/**
 *
 * @author Caldremch
 *
 * @date 2020-08-18 13:21
 *
 * @email caldremch@163.com
 *
 * @describe
 *
 **/
abstract class BaseProcessor : AbstractProcessor() {

    protected lateinit var filter: Filer
    protected lateinit var logger: Logger
    protected lateinit var elementUtils: Elements

    override fun init(processingEnvironment: ProcessingEnvironment) {
        filter = processingEnvironment.filer
        logger = Logger(processingEnvironment.messager)
        elementUtils = processingEnvironment.elementUtils
        val options = processingEnvironment.options //注解器配置参数

    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

}