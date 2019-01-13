package actions.add

import actions.ChangesetAction
import java.io.File

class AddAutoIncrement : ChangesetAction() {

    override fun insertChangeset(changelogFile: File) {
        changelogFile.appendText(
            "- changeSet:\n" +
                    "    id: ${IdValue.id}\n" +
                    "    author: ${Settings.authorName}\n" +
                    "       changes:\n" +
                    "       - addAutoIncrement:\n" +
                    "          columnDataType:\n" +
                    "          columnName:\n" +
                    "          tableName:\n" +
                    "          incrementBy: 1\n" +
                    "          startWith: \n\n"
        )
        IdValue.id++
    }

}