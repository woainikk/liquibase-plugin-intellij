package actions.drop

import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class DropIndex : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertDropIndex()
    }

    private fun insertDropIndex() {
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
                    "   - dropIndex:\n" +
                    "       indexName:\n" +
                    "       tableName:\n\n"
        )
        IdValue.id++

    }
}
