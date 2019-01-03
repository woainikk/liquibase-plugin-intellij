import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.ProjectManager
import java.io.File

class GenerateChangeSet : AnAction() {

    var id = 1

    override fun actionPerformed(e: AnActionEvent?) {

        val curentProject = ProjectManager.getInstance().openProjects[0]
        val currentfile = FileEditorManager.getInstance(curentProject).selectedFiles[0].name
        insertChangeSet(currentfile)
        println("Author name ${Author.authorName}")
    }

    private fun insertChangeSet(filename: String) {
        val changelogPath = Changelog.changelogFileName
        val changelogFile = File(changelogPath)

        if (changelogFile.length() < 10) {
            changelogFile.writeText("databaseChangeLog:\n")
        }
        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: $id\n" +
                    "   author: ${Author.authorName}\n" +
                    "   changes:\n" +
                    "   - sqlfile:\n" +
                    "       path: $filename\n" +
                    "       relativeToChangelogFile: true \n\n"
        )
        id++
    }

}