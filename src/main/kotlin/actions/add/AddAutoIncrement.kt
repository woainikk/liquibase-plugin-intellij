package actions.add

import actions.Changelog
import actions.GenerateChangeSet
import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.io.File

class AddAutoIncrement : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {
        checkAuthorAndChangelogIsDetermined()
        insertAddAutoIncrement()
    }

    private fun insertAddAutoIncrement() {
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)
        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${GenerateChangeSet.id}\n" +
                    "   author: ${Author.authorName}\n" +
                    "   changes:\n" +
                    "   - addAutoIncrement:\n" +
                    "       columnDataType:\n" +
                    "       columnName:\n" +
                    "       tableName:\n" +
                    "       incrementBy: 1\n" +
                    "       startWith:\n\n"
        )
        GenerateChangeSet.id++

    }

}