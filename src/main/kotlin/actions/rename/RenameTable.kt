package actions.rename

import actions.Changelog
import actions.GenerateChangeSet
import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class RenameTable : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertRenameTable()
    }

    private fun insertRenameTable() {
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)
        GenerateChangeSet.id = findLastId(Changelog.changelogFileName!!)

        changelogFile.appendText(
            "  - changeSet:\n" +
                    "     id: ${GenerateChangeSet.id}\n" +
                    "     author: ${Author.authorName}\n" +
                    "     changes:\n" +
                    "     - renameTable:\n" +
                    "         newTableName:\n" +
                    "         oldTableName:\n\n"
        )
        GenerateChangeSet.id++

    }

}