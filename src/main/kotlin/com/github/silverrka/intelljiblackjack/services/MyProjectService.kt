package com.github.silverrka.intelljiblackjack.services

import com.intellij.openapi.project.Project
import com.github.silverrka.intelljiblackjack.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
