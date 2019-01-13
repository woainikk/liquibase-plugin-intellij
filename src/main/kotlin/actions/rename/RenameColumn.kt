package actions.rename

import actions.ChangesetAction
import java.io.File

class RenameColumn : ChangesetAction() {

    override fun insertChangeset(changelogFile: File) {
        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${IdValue.id}\n" +
                    "   author: ${Settings.authorName}\n" +
                    "   changes:\n" +
                    "   - renameColumn:\n" +
                    "       columnDataType:\n" +
                    "       newColumnName:\n" +
                    "       oldColumnName:\n" +
                    "       tableName:\n\n"
        )
        IdValue.id++
    }

}