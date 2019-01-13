package actions.create

import actions.ChangesetAction
import java.io.File

class CreateView : ChangesetAction() {

    override fun insertChangeset(changelogFile: File) {
        changelogFile.appendText(
            "- changeSet:\n" +
                    "    id: ${IdValue.id}\n" +
                    "    author: ${Settings.authorName}\n" +
                    "      changes:\n" +
                    "        - createView:\n" +
                    "        replaceIfExists: true\n" +
                    "        selectQuery:\n" +
                    "        viewName:\n\n"
        )
        IdValue.id++

    }

}