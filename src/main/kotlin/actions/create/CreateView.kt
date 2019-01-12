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
            """- changeSet:
                       id: ${IdValue.id}
                       author: ${Settings.authorName}
                       changes:
                       - createView:
                           replaceIfExists: true
                           selectQuery:
                           viewName:"""
        )
        IdValue.id++

    }

}