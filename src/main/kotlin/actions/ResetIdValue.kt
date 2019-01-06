package actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import javax.swing.JOptionPane

class ResetIdValue : AnAction() {

    override fun actionPerformed(e: AnActionEvent?) {
        IdValue.id = 1
        JOptionPane.showMessageDialog(null, "Now id for changesets equals to 1.")
    }

}