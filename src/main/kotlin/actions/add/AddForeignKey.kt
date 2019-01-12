package actions.add

import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class AddForeignKey : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertAddForeignKey()
    }

    private fun insertAddForeignKey() {
        val changelogFile = File(Settings.changelogFileName)
        addHeaderToChangelog(changelogFile)
        if (IdValue.id < findLastId(Settings.changelogFileName!!)) {
            IdValue.id = findLastId(Settings.changelogFileName!!)
        }
        changelogFile.appendText(
            """- changeSet:
                    "   id: ${IdValue.id}
                    "   author: ${Settings.authorName}
                    "   changes:
                    "   - addForeignKeyConstraint:
                    "       baseColumnNames:
                    "       baseTableName:
                    "       constraintName:
                    "       initiallyDeferred:
                    "       onDelete:
                    "       onUpdate:
                    "       referencedColumnNames:
                    "       referencedTableName:"""
        )
        IdValue.id++
    }
}