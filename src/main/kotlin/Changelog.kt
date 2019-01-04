import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.ProjectManager

class Changelog : AnAction() {

    companion object {
        var changelogFileName: String? = null
    }

    override fun actionPerformed(e: AnActionEvent?) {
        val curentProject = ProjectManager.getInstance().openProjects[0]
        changelogFileName = FileEditorManager.getInstance(curentProject).selectedFiles[0].path
    }

}