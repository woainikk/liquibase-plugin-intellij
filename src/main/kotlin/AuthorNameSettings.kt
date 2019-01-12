import com.intellij.openapi.options.Configurable

import javax.swing.JComponent

class AuthorNameSettings : Configurable {

    private val settings = AuthorNameInputGui()

    override fun createComponent(): JComponent? = settings.panel

    override fun apply() {
        Settings.authorName = settings.author?.text ?: "user"
    }

    override fun getDisplayName(): String = "Input your name"

    override fun isModified(): Boolean = AuthorNameInputGui().author?.text != null

}