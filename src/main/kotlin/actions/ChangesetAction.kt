package actions

import addHeaderToChangelog
import checkAuthorAndChangelogIsDetermined
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import findLastId
import java.io.File

abstract class ChangesetAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {
        if (!checkAuthorAndChangelogIsDetermined()) {
            return
        }
        val changelogFile = File(Settings.changelogFileName)
        setSettingsForChangeset(changelogFile)
        insertChangeset(changelogFile)
    }

    private fun setSettingsForChangeset(changelog: File) {
        addHeaderToChangelog(changelog)
        if (changelog.length() > 10) {
            IdValue.id = findLastId(Settings.changelogFileName!!)
        }

    }

    abstract fun insertChangeset(changelogFile: File)

}