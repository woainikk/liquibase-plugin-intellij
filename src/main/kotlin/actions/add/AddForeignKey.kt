package actions.add

import Author
import actions.Changelog
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
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)
        if (IdValue.id < findLastId(Changelog.changelogFileName!!)) {
            IdValue.id = findLastId(Changelog.changelogFileName!!)
        }
        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${IdValue.id}\n" +
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
        IdValue.id++
    }
}