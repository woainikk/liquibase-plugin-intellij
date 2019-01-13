package actions.rename

import actions.ChangesetAction
import java.io.File

class RenameView : ChangesetAction() {

    override fun insertChangeset(changelogFile: File) {
        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${IdValue.id}\n" +
                    "   author: ${Settings.authorName}\n" +
                    "   changes:\n" +
                    "   - renameView:\n" +
                    "       newViewName:\n" +
                    "       oldViewName:\n\n"
        )
        IdValue.id++
    }

}