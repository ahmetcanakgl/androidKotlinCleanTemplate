package com.github.ahmetcanakgl.androidkotlincleantemplate.cleanKotlin.fragment

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies

fun RecipeExecutor.cleanKotlinFragment(
    moduleData: ModuleTemplateData,
    packageName: String,
    isCreateFragment: Boolean,
    moduleName: String,
    isCreateViewModel: Boolean,
    viewModelName: String,
    isCreateUsecase: Boolean,
    usecaseClass: String,
    isCreateRepository: Boolean,
    repositoryClass: String,
    isCreateService: Boolean,
    serviceClass: String,
    isCreateApi: Boolean,
    apiClass: String,
    isCreateEntityModel: Boolean,
    entityModelClass: String,
    layoutName: String
) {
    val (_, srcOut, resOut) = moduleData

    addAllKotlinDependencies(moduleData)

    val fragmentClass = "${moduleName}Fragment"
    val moduleNameLower = moduleName.substring(0, 1).toLowerCase() + moduleName.substring(1)

    if (isCreateFragment) {
        save(
            someFragment(
                packageName = packageName,
                moduleName = moduleName,
                entityModelClass = entityModelClass
            ),
            srcOut.resolve("$moduleNameLower/$fragmentClass.kt")
        )
    }

    if (isCreateViewModel) {
        save(
            someFragmentViewModel(
                packageName = packageName,
                moduleName = moduleName,
                usecaseClass = usecaseClass,
                entityModelClass = entityModelClass
            ),
            srcOut.resolve("$moduleNameLower/$viewModelName.kt")
        )
    }

    if (isCreateUsecase) {
        save(
            someUsecase(
                packageName = packageName,
                usecaseClass = usecaseClass,
                repositoryClass = repositoryClass,
                entityModelClass = entityModelClass
            ),
            srcOut.resolve("usecase/$usecaseClass.kt")
        )
    }

    if (isCreateRepository) {
        save(
            someRepository(
                packageName = packageName,
                repositoryClass = repositoryClass,
                entityModelClass = entityModelClass,
                serviceClass = serviceClass
            ),
            srcOut.resolve("api/$moduleNameLower/$repositoryClass.kt")
        )
    }

    if (isCreateService) {
        save(
            someService(
                packageName = packageName,
                apiClass = apiClass,
                entityModelClass = entityModelClass,
                serviceClass = serviceClass
            ),
            srcOut.resolve("api/$moduleNameLower/$serviceClass.kt")
        )
    }

    if (isCreateApi) {
        save(
            someApi(
                packageName = packageName,
                apiClass = apiClass,
                entityModelClass = entityModelClass
            ),
            srcOut.resolve("api/$moduleNameLower/$apiClass.kt")
        )
    }

    if (isCreateEntityModel) {
        save(
            someEntity(
                packageName = packageName,
                entityModelClass = entityModelClass
            ),
            srcOut.resolve("api/$entityModelClass.kt")
        )
    }

    if (isCreateFragment) {
        save(
            someFragmentLayout(
                packageName = packageName,
                moduleName = moduleName
            ),
            resOut.resolve("layout/$layoutName.xml")
        )
    }
}