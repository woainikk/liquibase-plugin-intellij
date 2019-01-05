package actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.ProjectManager

class MasterChangelog : AnAction() {

    companion object {
        var changelogMasterName: String? = null
    }

    override fun actionPerformed(e: AnActionEvent?) {
        val curentProject = ProjectManager.getInstance().openProjects[0]
        changelogMasterName = FileEditorManager.getInstance(curentProject).selectedFiles[0].path
    }

}