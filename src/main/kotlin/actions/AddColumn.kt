package actions

import Author
import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.io.File

class AddColumn : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertAddingColumn()
    }

    private fun insertAddingColumn() {
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)

        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${GenerateChangeSet.id}\n" +
                    "   author: ${Author.authorName}\n" +
                    "   changes:\n" +
                    "   - addColumn:\n" +
                    "         columns:\n" +
                    "           - column:\n" +
                    "               name:\n" +
                    "               type: \n" +
                    "         tableName:\n\n"
        )
        GenerateChangeSet.id++


    }

}