package actions.create

import actions.ChangesetAction
import java.io.File

class CreateIndex : ChangesetAction() {

    override fun insertChangeset(changelogFile: File) {
        changelogFile.appendText(
            "- changeSet:\n" +
                    "    id: ${IdValue.id}\n" +
                    "    author: ${Settings.authorName}\n" +
                    "      changes:\n" +
                    "      - createIndex:\n" +
                    "      columns:\n" +
                    "        - column:\n" +
                    "        name:\n" +
                    "        type:\n" +
                    "        indexName:\n" +
                    "        tableName:\n" +
                    "        unique:\n\n"
        )
        IdValue.id++
    }

}