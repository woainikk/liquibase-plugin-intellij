import java.io.File
import java.util.*
import javax.swing.JOptionPane

fun checkAuthorAndChangelogIsDetermined(): Boolean {
    if (Settings.authorName == null) {
        JOptionPane.showMessageDialog(
            null, "Author name is empty!\n " +
                    "Set it in File -> Settings -> Tools -> Changesets Author."
        )
        return false
    }
    if (Settings.changelogFileName == null) {
        JOptionPane.showMessageDialog(
            null, "Changelog file is not determined!\n " +
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
    if (Settings.masterChangelogFileName == null) {
        JOptionPane.showMessageDialog(
            null, "Master changelog file is not determined!\n " +
                    "Click on prefered as a master changelod file and click Liquibase -> Set as a master changelog."
        )
        return false
    }
    return true
}

fun checkFileContainString(masterFile: File, changelogFileName: String): Boolean {
    val scanner = Scanner(masterFile)
    var lineNum = 0
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine()
        lineNum++
        if (line.contains(changelogFileName)) {
            return true
        }
    }
    return false
}

fun findLastId(changelogFileName: String): Int {
    val scanner = Scanner(File(changelogFileName))
    var lineNum = 0
    var maxId = 0
    while (scanner.hasNextLine()) {
        var line = scanner.nextLine()
        lineNum++
        if (line.contains("id")) {
            line = line.substringAfter(":")
            line = line.replace(" ", "")
            if (line.toInt() > maxId) {
                maxId = line.toInt()
            }
        }
    }
    return ++maxId
}