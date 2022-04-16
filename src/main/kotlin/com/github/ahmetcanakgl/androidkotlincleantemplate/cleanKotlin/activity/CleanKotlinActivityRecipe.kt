package com.github.ahmetcanakgl.androidkotlincleantemplate.cleanKotlin.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest

fun RecipeExecutor.cleanKotlinActivity(
    moduleData: ModuleTemplateData,
    packageName: String,
    moduleName: String,
    layoutName: String
) {
    val (projectData, srcOut, resOut) = moduleData

    addAllKotlinDependencies(moduleData)

    val activityClass = "${moduleName}Activity"
    // This will generate new manifest (with activity) to merge it with existing
    generateManifest(
        moduleData = moduleData,
        activityClass = activityClass,
        packageName = packageName,
        isLauncher = false,
        hasNoActionBar = true,
        generateActivityTitle = true)

    save(
        someActivity(packageName, moduleName),
        srcOut.resolve("$activityClass.kt")
    )
    save(
        someActivityLayout(packageName, moduleName),
        resOut.resolve("layout/$layoutName.xml")
    )
}