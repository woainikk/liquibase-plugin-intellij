package actions.rename

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
        val changelogFile = File(Settings.changelogFileName)
        addHeaderToChangelog(changelogFile)
        if (IdValue.id < findLastId(Settings.changelogFileName!!)) {
            IdValue.id = findLastId(Settings.changelogFileName!!)
        }
        changelogFile.appendText(
            " - changeSet:\n" +
                    "   id: ${IdValue.id}\n" +
                    "   author: ${Settings.authorName}\n" +
                    "   changes:\n" +
                    "   - renameTable:\n" +
                    "       newTableName:\n" +
                    "       oldTableName:\n\n"
        )
        IdValue.id++

    }

}