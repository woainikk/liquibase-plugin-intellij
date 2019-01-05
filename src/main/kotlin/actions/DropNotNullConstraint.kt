package actions

import Author
import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.io.File

class DropNotNullConstraint : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertDropNotNullConstraint()
    }

    private fun insertDropNotNullConstraint() {
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)

        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${GenerateChangeSet.id}\n" +
                    "   author: ${Author.authorName}\n" +
                    "   changes:\n" +
                    "   - dropNotNullConstraint:\n" +
                    "       columnDataType:\n" +
                    "       columnName:\n" +
                    "       tableName:\n\n"
        )
        GenerateChangeSet.id++

    }

}