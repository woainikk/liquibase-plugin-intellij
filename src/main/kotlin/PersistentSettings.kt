import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "LiquibaseInfo.Author.Changeset.ChangelogMaster",
    storages = [Storage("PluginLiqubaseInfo.xml")]
)
class PersistentSettings : PersistentStateComponent<PersistentSettings> {

    var authorName: String? = null
    var masterChangelogName: String? = null
    var currentChangelogName: String? = null

    override fun getState(): PersistentSettings = this

    override fun loadState(state: PersistentSettings) = XmlSerializerUtil.copyBean(state, this)

}