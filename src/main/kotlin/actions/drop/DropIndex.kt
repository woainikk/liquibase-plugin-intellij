package actions.drop

import actions.ChangesetAction
import java.io.File

class DropIndex : ChangesetAction() {

    override fun insertChangeset(changelogFile: File) {
        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${IdValue.id}\n" +
                    "   author: ${Settings.authorName}\n" +
                    "   changes:\n" +
                    "   - dropIndex:\n" +
                    "       indexName:\n" +
                    "       tableName:\n\n"
        )
        IdValue.id++
    }

}
