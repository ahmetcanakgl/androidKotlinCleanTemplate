package com.github.ahmetcanakgl.androidkotlincleantemplate.cleanKotlin.activity

import com.android.tools.idea.wizard.template.*

private const val MIN_SDK = 16

val cleanKotlinActivity
    get() = template {

        name = "Clean Kotlin Activity"
        description = "Creates a new activity along layout file."
        minApi = MIN_SDK

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val packageNameParam = defaultActivityPackageNameParameter

        val entityName = stringParameter {
            name = "Entity Name"
            default = "Wurst"
            help = "The name of the entity class to create and use in Activity"
            constraints = listOf(Constraint.NONEMPTY)
        }

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "my_act"
            help = "The name of the layout to create for the activity"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { "${activityToLayout(entityName.value.toLowerCase())}s" }
        }

        widgets(
            TextFieldWidget(entityName),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageNameParam)
        )

        recipe = { data: TemplateData ->
            cleanKotlinActivity(
                moduleData = data as ModuleTemplateData,
                packageName = packageNameParam.value,
                moduleName = entityName.value,
                layoutName = layoutName.value
            )
        }
    }

val defaultActivityPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = "com.mycompany.myapp"
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }