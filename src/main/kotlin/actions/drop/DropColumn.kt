package actions.drop

import Author
import actions.Changelog
import actions.GenerateChangeSet
import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class DropColumn : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {
        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertDroppingColumn()
    }

    private fun insertDroppingColumn() {
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)
        GenerateChangeSet.id = findLastId(Changelog.changelogFileName!!)

        changelogFile.appendText(
            "  - changeSet:\n" +
                    "     id: ${GenerateChangeSet.id}\n" +
                    "     author: ${Author.authorName}\n" +
                    "     changes:\n" +
                    "     - dropColumn:\n" +
                    "         columnName:\n" +
                    "         tableName:\n\n"
        )
        GenerateChangeSet.id++

    }

}
