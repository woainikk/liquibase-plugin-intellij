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


    override fun actionPerformed(e: AnActionEvent?) {

        val currentProject = ProjectManager.getInstance().openProjects[0]
        Settings.changelogFileName = FileEditorManager.getInstance(currentProject).selectedFiles[0].path
        val changelogAbsolutePath = File(Settings.changelogFileName).absolutePath.substringAfter("resources")
        println(changelogAbsolutePath)

        if (!checkMasterChangelogDetermined()) return
        val changelogMasterFile = File(Settings.masterChangelogFileName)

        if (!checkFileContainString(changelogMasterFile, changelogAbsolutePath)) {
            addHeaderToChangelog(changelogMasterFile)
            changelogMasterFile.appendText(
                "    - include:\n" +
                        "        file: $changelogAbsolutePath\n"
            )
        }
    }

}