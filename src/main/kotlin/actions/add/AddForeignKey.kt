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
            "- changeSet:\n" +
                    "   id: ${IdValue.id}\n" +
                    "   author: ${Settings.authorName}\n" +
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
        IdValue.id++
    }
}