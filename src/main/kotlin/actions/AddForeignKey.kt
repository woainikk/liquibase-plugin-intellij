package actions

import Author
import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.ProjectManager
import java.io.File

class AddForeignKey : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertAddForeignKey()
    }

    private fun insertAddForeignKey() {
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)

        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${GenerateChangeSet.id}\n" +
                    "   author: ${Author.authorName}\n" +
                    "   changes:\n" +
                    "   - addForeignKeyConstraint:\n" +
                    "       baseColumnNames:\n" +
                    "       baseTableName:\n" +
                    "       constraintName:\n" +
                    "       initiallyDeferred:\n" +
                    "       onDelete:\n" +
                    "       onUpdate:\n" +
                    "       referencedColumnNames:\n" +
                    "       referencedTableName:\n\n"
        )
        GenerateChangeSet.id++

    }
}