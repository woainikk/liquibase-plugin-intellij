package actions.create

import actions.Changelog
import actions.GenerateChangeSet
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
                    "   - createView:\n" +
                    "       replaceIfExists: true\n" +
                    "       selectQuery:\n" +
                    "       viewName:\n\n"
        )
        IdValue.id++

    }

}