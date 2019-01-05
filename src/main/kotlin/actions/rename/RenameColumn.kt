package actions.rename

import actions.Changelog
import actions.GenerateChangeSet
import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class RenameColumn : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertRenameColumn()
    }

    private fun insertRenameColumn() {
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)
        GenerateChangeSet.id = findLastId(Changelog.changelogFileName!!)

        changelogFile.appendText(
            "  - changeSet:\n" +
                    "     id: ${GenerateChangeSet.id}\n" +
                    "     author: ${Author.authorName}\n" +
                    "     changes:\n" +
                    "     - renameColumn:\n" +
                    "         columnDataType:\n" +
                    "         newColumnName:\n\n" +
                    "         oldColumnName:\n" +
                    "         tableName:\n\n"
        )
        GenerateChangeSet.id++

    }

}