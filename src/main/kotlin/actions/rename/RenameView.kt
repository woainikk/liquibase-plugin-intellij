package actions.rename

import actions.Changelog
import actions.GenerateChangeSet
import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.io.File

class RenameView : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertRenameView()
    }

    private fun insertRenameView() {
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)

        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${GenerateChangeSet.id}\n" +
                    "   author: ${Author.authorName}\n" +
                    "   changes:\n" +
                    "   - renameView:\n" +
                    "       newViewName:\n" +
                    "       oldViewName:\n\n"
        )
        GenerateChangeSet.id++

    }

}