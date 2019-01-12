package actions.add

import actions.Changelog
import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class AddAutoIncrement : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {
        checkAuthorAndChangelogIsDetermined()
        insertAddAutoIncrement()
    }

    private fun insertAddAutoIncrement() {
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
                        - addAutoIncrement:
                           columnDataType:
                           columnName:
                           tableName:
                           incrementBy: 1
                           startWith: """
        )
        IdValue.id++

    }

}