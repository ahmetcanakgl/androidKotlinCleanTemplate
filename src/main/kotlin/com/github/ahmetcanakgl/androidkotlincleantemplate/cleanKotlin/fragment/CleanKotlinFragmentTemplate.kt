package com.github.ahmetcanakgl.androidkotlincleantemplate.cleanKotlin.fragment

import com.android.tools.idea.wizard.template.*

private const val MIN_SDK = 16

val cleanKotlinFragment
    get() = template {

        name = "Clean Kotlin Fragment"
        description = "Creates a new fragment along layout file."
        minApi = MIN_SDK

        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val isCreateFragment = booleanParameter {
            name = "Create Fragment ?"
            default = false
            help = "If given named fragment is already exists, leave it false"
        }

        val moduleName = stringParameter {
            name = "Module Name"
            default = "-"
            help = "The name of the entity class to create and use in Fragment"
            constraints = listOf(Constraint.NONEMPTY)
        }

        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////

        val isCreateViewModel = booleanParameter {
            name = "Create ViewModel ?"
            default = false
        }

        val viewModelName = stringParameter {
            name = "View Model Name"
            default = "-"
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${moduleName.value}FRViewModel" }
        }

        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////

        val isCreateUsecase = booleanParameter {
            name = "Create UseCase ?"
            default = false
        }

        val usecaseClassName = stringParameter {
            name = "Usecase Class Name"
            default = "-"
            help = "The class that implements Usecase and coroutines exists in"
            constraints = listOf(Constraint.NONEMPTY)
        }

        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////

        val isCreateRepository = booleanParameter {
            name = "Create Repository ?"
            default = false
        }

        val repositoryClassName = stringParameter {
            name = "Repository Class Name"
            default = "Repository"
            help = "The class that used in Usecase's constractor. (Suffix shouldn't be \"Repository\")"
        }

        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////

        val isCreateService = booleanParameter {
            name = "Create Service ?"
            default = false
        }

        val serviceClassName = stringParameter {
            name = "Service Class Name"
            default = "-"
            help = "Here where Retrofit calls occur, and the received Call from Api Class"
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${repositoryClassName.value.replace("Repository","")}Service" }
        }

        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////

        val isCreateApi = booleanParameter {
            name = "Create Api ?"
            default = false
        }

        val apiClassName = stringParameter {
            name = "Api Class Name"
            default = "-"
            help = "Stores endpoints with their response entity here !"
            constraints = listOf(Constraint.NONEMPTY)
            suggest = { "${repositoryClassName.value.replace("Repository","")}Api" }
        }

        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////

        val isCreateEntityModel = booleanParameter {
            name = "Create EntityModel ?"
            default = false
        }

        val entityModelClassName = stringParameter {
            name = "Entity Class Name"
            default = "-"
            help = "The model that used in View, VM, UseCase. Must be return type of api service"
            constraints = listOf(Constraint.NONEMPTY)
        }

        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////

        val layoutName = stringParameter {
            name = "Layout Name"
            default = "fragment"
            help = "The name of the layout to create for the activity"
            constraints = listOf(Constraint.LAYOUT, Constraint.UNIQUE, Constraint.NONEMPTY)
            suggest = { fragmentToLayout(moduleName.value.toLowerCase()) }
        }

        val packageNameParam = defaultFragmentPackageNameParameter

        widgets(
            CheckBoxWidget(isCreateFragment),
            TextFieldWidget(moduleName),
            CheckBoxWidget(isCreateViewModel),
            TextFieldWidget(viewModelName),
            CheckBoxWidget(isCreateUsecase),
            TextFieldWidget(usecaseClassName),
            CheckBoxWidget(isCreateRepository),
            TextFieldWidget(repositoryClassName),
            CheckBoxWidget(isCreateService),
            TextFieldWidget(serviceClassName),
            CheckBoxWidget(isCreateApi),
            TextFieldWidget(apiClassName),
            CheckBoxWidget(isCreateEntityModel),
            TextFieldWidget(entityModelClassName),
            TextFieldWidget(layoutName),
            PackageNameWidget(packageNameParam)
        )

        recipe = { data: TemplateData ->
            cleanKotlinFragment(
                moduleData = data as ModuleTemplateData,
                packageName = packageNameParam.value,

                isCreateFragment = isCreateFragment.value,
                moduleName = moduleName.value,

                isCreateViewModel = isCreateViewModel.value,
                viewModelName = viewModelName.value,

                isCreateUsecase = isCreateUsecase.value,
                usecaseClass = usecaseClassName.value,

                isCreateRepository = isCreateRepository.value,
                repositoryClass = repositoryClassName.value,

                isCreateService = isCreateService.value,
                serviceClass = serviceClassName.value,

                isCreateApi = isCreateApi.value,
                apiClass = apiClassName.value,

                isCreateEntityModel = isCreateEntityModel.value,
                entityModelClass = entityModelClassName.value,

                layoutName = layoutName.value
            )
        }
    }

val defaultFragmentPackageNameParameter
    get() = stringParameter {
        name = "Package name"
        visible = { !isNewModule }
        default = "carparts.online"
        constraints = listOf(Constraint.PACKAGE)
        suggest = { packageName }
    }