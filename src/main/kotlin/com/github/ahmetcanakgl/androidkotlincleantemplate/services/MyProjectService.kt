package com.github.ahmetcanakgl.androidkotlincleantemplate.services

import com.intellij.openapi.project.Project
import com.github.ahmetcanakgl.androidkotlincleantemplate.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
