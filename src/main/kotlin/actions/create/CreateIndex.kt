package actions.create

import actions.Changelog
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
        val changelogFile = File(Changelog.changelogFileName)
        addHeaderToChangelog(changelogFile)
        if (IdValue.id < findLastId(Changelog.changelogFileName!!)) {
            IdValue.id = findLastId(Changelog.changelogFileName!!)
        }

        changelogFile.appendText(
            """- changeSet:
                        id: ${IdValue.id}
                        author: ${Author.authorName}
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