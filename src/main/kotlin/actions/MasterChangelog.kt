package actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.ProjectManager

class MasterChangelog : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {
        val currentProject = ProjectManager.getInstance().openProjects[0]
        Settings.masterChangelogFileName = FileEditorManager.getInstance(currentProject).selectedFiles[0].path
    }

}