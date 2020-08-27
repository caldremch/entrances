package com.caldremch.android.entry.compiler;

import com.caldremch.android.annotation.EntryConst;
import com.caldremch.android.annotation.entry.Entry;
import com.caldremch.android.annotation.entry.IEntry;
import com.caldremch.android.annotation.entry.IEntryCollection;
import com.caldremch.android.entry.compiler.base.BaseProcessor;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * @author Caldremch
 * @date 2020-08-27 13:58
 * @email caldremch@163.com
 * @describe
 **/
public final class EntryProcessor extends BaseProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (set != null && !set.isEmpty()) {
            logger.info("EntryProcessor start.......");
            Set<? extends Element> clzs = roundEnvironment.getElementsAnnotatedWith(Entry.class);
            handleAnnotation(clzs);
            logger.info("EntryProcessor done.......");
            return true;
        }
        return false;
    }

    private void handleAnnotation(Set<? extends Element> entryElements) {
        TypeElement typeEle = elementUtils.getTypeElement(IEntryCollection.class.getName());

        ParameterizedTypeName inputEntryListType = ParameterizedTypeName.get(ClassName.get(List.class), ClassName.get(IEntry.class));
        ParameterSpec entryParamSpec = ParameterSpec.builder(inputEntryListType, "entrys").build();
        MethodSpec.Builder methodBuild = MethodSpec.methodBuilder(typeEle.getEnclosedElements().get(0).getSimpleName().toString())
                .addAnnotation(Override.class)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(entryParamSpec);

        for (Element an : entryElements) {
            if (an.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) an;
                methodBuild.addStatement("entrys.add(new $T())", typeElement);
            }
        }

        //创建类
        try {
            JavaFile.builder(
                    EntryConst.ENTRY_PKG, TypeSpec.classBuilder(EntryConst.ENTRY_NAME)
                            .addSuperinterface(IEntryCollection.class)
                            .addModifiers(Modifier.PUBLIC)
                            .addMethod(methodBuild.build())
                            .build()
            ).build().writeTo(filter);
        } catch (Exception e) {
            logger.error(e);
        }

    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> typeList = new HashSet<>();
        typeList.add(Entry.class.getName());
        return typeList;
    }
}
