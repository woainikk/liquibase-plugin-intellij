import com.intellij.openapi.components.ServiceManager

object Settings {

    private val persistentSettings by lazy { ServiceManager.getService(PersistentSettings::class.java) }

    var authorName: String?
        set(value) {
            persistentSettings.authorName = value
        }
        get() = persistentSettings.authorName

    var changelogFileName: String?
        set(value) {
            persistentSettings.currentChangelogName = value
        }
        get() = persistentSettings.currentChangelogName

    var masterChangelogFileName: String?
        set(value) {
            persistentSettings.masterChangelogName = value
        }
        get() = persistentSettings.masterChangelogName


}
