import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "prefe",
    storages = [Storage("")]
)
class PersistantSettings : PersistentStateComponent<PersistantSettings> {

    val authorName: String? = null
    val masterChangelogName: String? = null
    val currentChangelogName: String? = null

    override fun getState(): PersistantSettings? = this

    override fun loadState(state: PersistantSettings) = XmlSerializerUtil.copyBean(state, this)

}