package com.github.ahmetcanakgl.androidkotlincleantemplate.cleanKotlin.fragment

fun someFragment(
    packageName: String,
    moduleName: String,
    entityModelClass: String
): String {
    val entityClsProp = entityModelClass.substring(0, 1).toLowerCase() + entityModelClass.substring(1)
    val moduleNameLower = moduleName.substring(0, 1).toLowerCase() + moduleName.substring(1)

    val code = """package $packageName.$moduleNameLower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import carparts.online.core.extension.observe
import carparts.online.databinding.Fragment${moduleName}Binding
import carparts.online.feature.bases.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ${moduleName}Fragment : BaseFragment<Fragment${moduleName}Binding>() {

    private val viewModel by viewModels<${moduleName}FRViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            observe($entityClsProp, ::onXSuccess)
        }
    }

    private fun onXSuccess(response: $entityModelClass) {
        
    }

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): Fragment${moduleName}Binding {
        return Fragment${moduleName}Binding.inflate(inflater, container, false)
    }
}
"""
    return code
}

fun someFragmentLayout(
    packageName: String,
    moduleName: String
): String {
    return """<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="$packageName.${moduleName}Fragment">

</androidx.constraintlayout.widget.ConstraintLayout>
"""

}

