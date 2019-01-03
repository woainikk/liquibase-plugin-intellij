import com.intellij.openapi.options.Configurable

import javax.swing.JComponent

class AuthorNameSettings : Configurable {

    val settings = AuthorNameInputGui()

    override fun createComponent(): JComponent? = settings.panel

    override fun apply() {
        Author.authorName = settings.author?.text ?: "user"
        println(settings.author?.text)
        println(Author.authorName)

    }

    override fun getDisplayName(): String = "Input your name"

    override fun isModified(): Boolean = AuthorNameInputGui().author?.text != null

}