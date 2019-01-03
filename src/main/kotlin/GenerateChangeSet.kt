import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.ProjectManager
import java.io.File

class GenerateChangeSet : AnAction() {

    var id = 0

    override fun actionPerformed(e: AnActionEvent?) {

        val curentProject = ProjectManager.getInstance().openProjects[0]
        val currentfile = FileEditorManager.getInstance(curentProject).selectedFiles[0].name

        insertChangeSet(currentfile)
    }

    fun insertChangeSet(filename: String) {
        //TODO: Get it automatically
        val changelogPath = "/home/woaini/IdeaProjects/testPlugin/src/db.changelog-1.4.yaml"
        id++
        //TODO:Chech if file is empty instead
        if (id == 1) {
            File(changelogPath).writeText("databaseChangeLog:\n")
        }

        //TODO: settings for author name
        File(changelogPath).appendText(
            "- changeSet:\n" +
                    "   id: $id\n" +
                    "   author: bobova\n" +
                    "   changes:\n" +
                    "   - sqlfile:\n" +
                    "       path: $filename\n" +
                    "       relativeToChangelogFile: true \n\n"
        )

    }

}