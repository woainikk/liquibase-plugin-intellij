package actions.drop

import actions.ChangesetAction
import java.io.File

class DropTable : ChangesetAction() {

    override fun insertChangeset(changelogFile: File) {
        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${IdValue.id}\n" +
                    "   author: ${Settings.authorName}\n" +
                    "   changes:\n" +
                    "   - dropTable:\n" +
                    "       tableName:\n" +
                    "       cascadeConstraints: true\n\n"
        )
        IdValue.id++
    }

}