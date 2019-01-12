package actions.rename

import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class RenameView : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertRenameView()
    }

    private fun insertRenameView() {
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
                    "   - renameView:\n" +
                    "       newViewName:\n" +
                    "       oldViewName:\n\n"
        )
        IdValue.id++

    }

}