package actions.add

import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

class AddNotNullConstraint : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        insertNotNullConstraint()
    }

    private fun insertNotNullConstraint() {
        val changelogFile = File(Settings.changelogFileName)
        addHeaderToChangelog(changelogFile)
        if (IdValue.id < findLastId(Settings.changelogFileName!!)) {
            IdValue.id = findLastId(Settings.changelogFileName!!)
        }
        changelogFile.appendText(
              """- changeSet:
                    "    id: ${IdValue.id}
                    "    author: ${Settings.authorName}
                    "    changes:
                    "    - addNotNullConstraint:
                    "       columnDataType:
                    "       columnName:
                    "       defaultNullValue:
                    "       tableName:"""
        )
        IdValue.id++
    }

}