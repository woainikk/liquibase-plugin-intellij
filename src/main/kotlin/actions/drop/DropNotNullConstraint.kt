package actions.drop

import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class DropNotNullConstraint : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertDropNotNullConstraint()
    }

    private fun insertDropNotNullConstraint() {
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
                    "   - dropNotNullConstraint:\n" +
                    "       columnDataType:\n" +
                    "       columnName:\n" +
                    "       tableName:\n\n"
        )
        IdValue.id++

    }

}