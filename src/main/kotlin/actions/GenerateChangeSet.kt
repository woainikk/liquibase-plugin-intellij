package actions

import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import checkFileContainString
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.ProjectManager
import findLastId
import java.io.File

class GenerateChangeSet : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        val curentProject = ProjectManager.getInstance().openProjects[0]
        val currentfile = FileEditorManager.getInstance(curentProject).selectedFiles[0].name
        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertChangeSet(currentfile)
        if (IdValue.id < findLastId(Settings.changelogFileName!!)) {
            IdValue.id = findLastId(Settings.changelogFileName!!)
        }
    }

    private fun insertChangeSet(filename: String) {
        val changelogFile = File(Settings.changelogFileName)
        addHeaderToChangelog(changelogFile)
        if (checkFileContainString(changelogFile, filename)) return
        changelogFile.appendText(
               "- changeSet:\n" +
                    "    id: ${IdValue.id}\n" +
                    "    author: ${Settings.authorName}\n" +
                    "    changes:\n" +
                    "    - sqlFile:\n" +
                    "        path: $filename\n" +
                    "        relativeToChangelogFile: true \n\n"
        )
        IdValue.id++
    }

}