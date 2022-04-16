package com.github.ahmetcanakgl.androidkotlincleantemplate.cleanKotlin.activity

import com.android.tools.idea.wizard.template.ProjectTemplateData

fun someActivity(
    packageName: String,
    moduleName: String
): String {

    val code = """package $packageName

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import carparts.online.databinding.ActivityLoginBinding
import carparts.online.feature.bases.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ${moduleName}Activity : BaseActivity<Activity${moduleName}Binding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        showToolbar = false
        showBottomNavBar = true
        super.onCreate(savedInstanceState)
    }

    override fun inflateLayout(
            parent: FrameLayout,
            inflater: LayoutInflater
    ): Activity${moduleName}Binding {
        return Activity${moduleName}Binding.inflate(inflater, parent, true)
    }
}
"""
    return code
}


fun someActivity1(
    packageName: String,
    entityName: String,
    layoutName: String,
    projectData: ProjectTemplateData
) = """package $packageName

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import ${projectData.applicationPackage}.R;

class ${entityName}sActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${layoutName.toLowerCase()})
    }
}
"""

fun someActivityLayout(packageName: String, entityName: String): String {
    return """<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="$packageName.${entityName}Activity">

</androidx.constraintlayout.widget.ConstraintLayout>
"""
}