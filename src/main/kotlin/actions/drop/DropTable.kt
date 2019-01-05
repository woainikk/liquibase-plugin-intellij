package actions.drop

import actions.Changelog
import actions.GenerateChangeSet
import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class DropTable : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertDropTable()
    }

    private fun insertDropTable() {
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)
        GenerateChangeSet.id = findLastId(Changelog.changelogFileName!!)

        changelogFile.appendText(
            "  - changeSet:\n" +
                    "     id: ${GenerateChangeSet.id}\n" +
                    "     author: ${Author.authorName}\n" +
                    "     changes:\n" +
                    "     - dropTable:\n" +
                    "         tableName:\n" +
                    "         cascadeConstraints: true\n\n"
        )
        GenerateChangeSet.id++

    }

}