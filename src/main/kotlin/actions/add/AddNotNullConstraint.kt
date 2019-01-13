package actions.add

import actions.ChangesetAction
import java.io.File

class AddNotNullConstraint : ChangesetAction() {

    override fun insertChangeset(changelogFile: File) {
        changelogFile.appendText(
            "- changeSet:\n" +
                    "    id: ${IdValue.id}\n" +
                    "    author: ${Settings.authorName}\n" +
                    "    changes:\n" +
                    "    - addNotNullConstraint:\n" +
                    "       columnDataType:\n" +
                    "       columnName:\n" +
                    "       defaultNullValue:\n" +
                    "       tableName:\n"
        )
        IdValue.id++
    }

}