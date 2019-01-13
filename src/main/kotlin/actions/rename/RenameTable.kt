package actions.rename

import actions.ChangesetAction
import java.io.File

class RenameTable : ChangesetAction() {

    override fun insertChangeset(changelogFile: File) {
        changelogFile.appendText(
            " - changeSet:\n" +
                    "   id: ${IdValue.id}\n" +
                    "   author: ${Settings.authorName}\n" +
                    "   changes:\n" +
                    "   - renameTable:\n" +
                    "       newTableName:\n" +
                    "       oldTableName:\n\n"
        )
        IdValue.id++
    }

}