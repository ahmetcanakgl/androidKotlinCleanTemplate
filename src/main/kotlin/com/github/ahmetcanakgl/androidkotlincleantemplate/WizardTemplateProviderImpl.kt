package com.github.ahmetcanakgl.androidkotlincleantemplate

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.ahmetcanakgl.androidkotlincleantemplate.cleanKotlin.activity.cleanKotlinActivity
import com.github.ahmetcanakgl.androidkotlincleantemplate.cleanKotlin.fragment.cleanKotlinFragment

class WizardTemplateProviderImpl : WizardTemplateProvider() {

    override fun getTemplates(): List<Template> = listOf(cleanKotlinFragment, cleanKotlinActivity)
}