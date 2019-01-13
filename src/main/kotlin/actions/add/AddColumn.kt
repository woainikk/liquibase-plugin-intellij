package actions.add

import actions.ChangesetAction
import java.io.File

class AddColumn : ChangesetAction() {

    override fun insertChangeset(changelogFile: File) {
        changelogFile.appendText(
            " - changeSet:\n" +
                    "     id: ${IdValue.id}\n" +
                    "     author: ${Settings.authorName}\n" +
                    "       changes:\n" +
                    "       - addColumn:\n" +
                    "           columns:\n" +
                    "            - column:\n" +
                    "                name:\n" +
                    "                type:\n" +
                    "           tableName:\n\n"
        )
        IdValue.id++
    }

}