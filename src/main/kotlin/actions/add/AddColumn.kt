package actions.add

import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class AddColumn : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertAddingColumn()
    }

    private fun insertAddingColumn() {
        val changelogFile = File(Settings.changelogFileName)
        addHeaderToChangelog(changelogFile)
        if (IdValue.id < findLastId(Settings.changelogFileName!!)) {
            IdValue.id = findLastId(Settings.changelogFileName!!)
        }
        findLastId(Settings.changelogFileName!!)
        changelogFile.appendText(
            """
- changeSet:
id: ${IdValue.id}
author: ${Settings.authorName}
changes:
- addColumn:
columns:
- column:
name:
type:
tableName:"""
        )
        IdValue.id++
    }

}