package actions.drop

import actions.Changelog
import actions.GenerateChangeSet
import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.io.File

class DropIndex : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertDropIndex()
    }

    private fun insertDropIndex() {
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)

        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${GenerateChangeSet.id}\n" +
                    "   author: ${Author.authorName}\n" +
                    "   changes:\n" +
                    "   - dropIndex:\n" +
                    "       indexName:\n" +
                    "       tableName:\n\n"
        )
        GenerateChangeSet.id++

    }
}
