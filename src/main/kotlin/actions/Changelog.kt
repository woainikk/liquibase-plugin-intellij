package actions

import addHeaderToChangelog
import checkFileContainString
import checkMasterChangelogDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.ProjectManager
import java.io.File

class Changelog : AnAction() {

    companion object {
        var changelogFileName: String? = null
    }

    override fun actionPerformed(e: AnActionEvent?) {

        val curentProject = ProjectManager.getInstance().openProjects[0]
        changelogFileName = FileEditorManager.getInstance(curentProject).selectedFiles[0].path
        val changelogAbsolutePath = File(changelogFileName).absolutePath

        if (!checkMasterChangelogDetermined()) return
        val changelogMasterFile = File(MasterChangelog.changelogMasterName)

        if (!checkFileContainString(changelogMasterFile, changelogFileName!!)) {
            addHeaderToChangelog(changelogMasterFile)
            changelogMasterFile.appendText(
                "    - include:\n" +
                        "        file: $changelogAbsolutePath\n"
            )
        }
    }

}