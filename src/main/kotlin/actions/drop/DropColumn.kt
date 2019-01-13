package actions.drop

import actions.ChangesetAction
import java.io.File

class DropColumn : ChangesetAction() {

    override fun insertChangeset(changelogFile: File) {
        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${IdValue.id}\n" +
                    "   author: ${Settings.authorName}\n" +
                    "    changes:\n" +
                    "   - dropColumn:\n" +
                    "       columnName:\n" +
                    "       tableName:\n\n"
        )
        IdValue.id++
    }

}
