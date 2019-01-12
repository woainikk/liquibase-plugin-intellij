package actions.create

import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class CreateIndex : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertAddIndex()
    }

    private fun insertAddIndex() {
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
                        - createIndex:
                           columns:
                           - column:
                               name:
                               type:
                           indexName:
                           tableName:
                           unique:"""
        )
        IdValue.id++

    }
}