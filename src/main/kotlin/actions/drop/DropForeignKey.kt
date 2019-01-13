package actions.drop

import actions.ChangesetAction
import java.io.File

class DropForeignKey : ChangesetAction() {

    override fun insertChangeset(changelogFile: File) {
        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${IdValue.id}\n" +
                    "   author: ${Settings.authorName}\n" +
                    "   changes:\n" +
                    "   - dropForeignKeyConstraint:\n" +
                    "       baseTableName:\n" +
                    "       constraintName:\n\n"
        )
        IdValue.id++
    }

}