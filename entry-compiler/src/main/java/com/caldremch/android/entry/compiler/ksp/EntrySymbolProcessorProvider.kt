package com.caldremch.android.entry.compiler.ksp

import com.caldremch.android.annotation.EntryConst
import com.caldremch.android.annotation.entry.Entry
import com.caldremch.android.annotation.entry.IEntry
import com.caldremch.android.annotation.entry.IEntryCollection
import com.caldremch.android.entry.compiler.ksp.utils.KSPLoggerWrapper
import com.caldremch.android.entry.compiler.ksp.utils.check
import com.caldremch.android.entry.compiler.ksp.utils.isSubclassOf
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.*
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.KotlinPoetKspPreview
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo

@KotlinPoetKspPreview
class EntrySymbolProcessorProvider : SymbolProcessorProvider {

    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return EntrySymbolProcessor(
            KSPLoggerWrapper(environment.logger), environment.codeGenerator
        )
    }

    class EntrySymbolProcessor(
        private val logger: KSPLoggerWrapper,
        private val codeGenerator: CodeGenerator,
    ) : SymbolProcessor {
        @Suppress("SpellCheckingInspection")
        companion object {
            val ENTRY_CLASS_NAME = Entry::class.qualifiedName!!
        }

        override fun process(resolver: Resolver): List<KSAnnotated> {
            val symbol = resolver.getSymbolsWithAnnotation(ENTRY_CLASS_NAME)

            val elements = symbol
                .filterIsInstance<KSClassDeclaration>()
                .toList()

            if (elements.isNotEmpty()) {
                logger.info(">>> EntrySymbolProcessor init. <<<")
                try {
                    generateEntryFile(elements)
                } catch (e: Exception) {
                    logger.exception(e)
                }
            }
            return emptyList()
        }

        @Suppress("SpellCheckingInspection")
        private fun generateEntryFile(elements: List<KSClassDeclaration>) {
            /** entrys: MutableList<IEntry>? */
            val parameterName = "entrys"
            val parameterSpec = ParameterSpec.builder(
                parameterName,
                MUTABLE_LIST.parameterizedBy(IEntry::class.asClassName()).copy(nullable = true)
            ).build()

            /** override fun load(entrys: List<IEntry>?) */
            val loadMethod: FunSpec.Builder = FunSpec
                .builder("load")
                .addModifiers(KModifier.OVERRIDE)
                .addParameter(parameterSpec)
            val dependency = mutableSetOf<KSFile>()
            for (element in elements) {
                // You can remove it! if it do not to be check
                logger.check(element.isSubclassOf("com.caldremch.android.annotation.entry.IEntry")) {
                    "Entry annotated with @Entry 's must be inherit from IEntry!"
                }
                loadMethod.addStatement("entrys?.add(%T())", element.toClassName())
                element.containingFile?.let {
                    dependency.add(it)
                }
            }

            val file =
                FileSpec.builder(EntryConst.ENTRY_PKG, EntryConst.ENTRY_NAME)
                    .addType(
                        TypeSpec.classBuilder(
                            ClassName(
                                EntryConst.ENTRY_PKG,
                                EntryConst.ENTRY_NAME
                            )
                        )
                            .addSuperinterface(IEntryCollection::class)
                            .addFunction(loadMethod.build())
                            .build()
                    )
                    .build()

            file.writeTo(codeGenerator, true, dependency)
            logger.info(">>> Entry processor stop. <<<")
        }
    }

}

