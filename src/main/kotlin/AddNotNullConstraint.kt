import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.ProjectManager
import java.io.File
import javax.swing.JOptionPane

class AddNotNullConstraint : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {

        val curentProject = ProjectManager.getInstance().openProjects[0]
        if (Author.authorName == null) {
            JOptionPane.showMessageDialog(
                null, "Author name is empty!\n " +
                        "Set it in File -> Settings -> Tools -> Changesets Author."
            )
            return
        }
        if (Changelog.changelogFileName == null) {
            JOptionPane.showMessageDialog(
                null, "Changelog file is not determined!\n " +
                        "Click on prefered as a changelod file and click Liquibase -> Set as a changelog."
            )
            return

        }
        insertNotNullConstraint()
    }

    private fun insertNotNullConstraint() {
        val changelogPath = Changelog.changelogFileName
        val changelogFile = File(changelogPath)

        if (changelogFile.length() < 10) {
            changelogFile.writeText("databaseChangeLog:\n")
        }
        changelogFile.appendText(
            "- changeSet:\n" +
                    "   id: ${GenerateChangeSet.id}\n" +
                    "   author: ${Author.authorName}\n" +
                    "   changes:\n" +
                    "   - addNotNullConstraint:\n" +
                    "       columnDataType:\n" +
                    "       columnName:\n" +
                    "       defaultNullValue:\n" +
                    "       tableName:\n\n"
        )
        GenerateChangeSet.id++


    }


}