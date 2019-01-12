package actions.create

import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class CreateView : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {
        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertCreateView()
    }

    private fun insertCreateView() {
        val changelogFile = File(Settings.changelogFileName)
        addHeaderToChangelog(changelogFile)
        if (IdValue.id < findLastId(Settings.changelogFileName!!)) {
            IdValue.id = findLastId(Settings.changelogFileName!!)
        }
        changelogFile.appendText(
                "- changeSet:\n" +
                    "    id: ${IdValue.id}\n" +
                    "    author: ${Settings.authorName}\n" +
                    "      changes:\n" +
                    "        - createView:\n" +
                    "        replaceIfExists: true\n" +
                    "        selectQuery:\n" +
                    "        viewName:\n\n"
        )
        IdValue.id++

    }

}