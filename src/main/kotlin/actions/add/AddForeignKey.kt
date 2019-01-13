package actions.add

import actions.ChangesetAction
import addHeaderToChangelog
import findLastId
import java.io.File

class AddForeignKey : ChangesetAction() {

    override fun insertChangeset(changelogFile: File) {
        val changelogFile = File(Settings.changelogFileName)
        addHeaderToChangelog(changelogFile)
        if (changelogFile.length() > 10) {
            IdValue.id = findLastId(Settings.changelogFileName!!)
        }
        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${IdValue.id}\n" +
                    "   author: ${Settings.authorName}\n" +
                    "   changes:\n" +
                    "   - addForeignKeyConstraint:\n" +
                    "       baseColumnNames:\n" +
                    "       baseTableName:\n" +
                    "       constraintName:\n" +
                    "       initiallyDeferred:\n" +
                    "       onDelete:\n" +
                    "       onUpdate:\n" +
                    "       referencedColumnNames:\n" +
                    "       referencedTableName:\n\n"
        )
        IdValue.id++
    }

}