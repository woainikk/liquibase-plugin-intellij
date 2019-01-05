import actions.Changelog
import actions.MasterChangelog
import java.io.File
import javax.swing.JOptionPane

fun checkAuthorAndChangelogIsDetermined(): Boolean {
    if (Author.authorName == null) {
        JOptionPane.showMessageDialog(
            null, "Author name is empty!\n " +
                    "Set it in File -> Settings -> Tools -> Changesets Author."
        )
        return false
    }
    if (Changelog.changelogFileName == null) {
        JOptionPane.showMessageDialog(
            null, "actions.Changelog file is not determined!\n " +
                    "Click on prefered as a changelod file and click Liquibase -> Set as a changelog."
        )
        return false
    }
    return true
}

fun addHeaderToChangelog(changelogFile: File) {
    if (changelogFile.length() < 2) {
        changelogFile.writeText("databaseChangeLog:\n")
    }
}

fun checkMasterChangelogDetermined(): Boolean {
    if (MasterChangelog.changelogMasterName == null) {
        JOptionPane.showMessageDialog(
            null, "Master changelog file is not determined!\n " +
                    "Click on prefered as a master changelod file and click Liquibase -> Set as a master changelog."
        )
        return false
    }
    return true
}