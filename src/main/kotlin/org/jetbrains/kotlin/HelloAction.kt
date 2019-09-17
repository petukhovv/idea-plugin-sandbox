package org.jetbrains.kotlin

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.ui.popup.JBPopupListener
import com.intellij.openapi.ui.popup.PopupStep
import com.intellij.openapi.ui.popup.util.BaseListPopupStep
import javax.swing.event.ListSelectionEvent

class HelloAction : AnAction("Hello") {
    lateinit var pr: Project

    override fun actionPerformed(event: AnActionEvent) {
        pr = event.project!!
        val l = object : BaseListPopupStep<String>("Choose test runner", "1", "2", "3", "4") {
            override fun onChosen(selectedValue: String?, finalChoice: Boolean): PopupStep<*> {
                onChosen(selectedValue)
                return super.onChosen(selectedValue, finalChoice)!!
            }
        }
        val list = JBPopupFactory.getInstance().createListPopup(l)
        list.showUnderneathOf(event.inputEvent.component)
    }

    override fun update(anActionEvent: AnActionEvent) {
        val psiFile = anActionEvent.getData(CommonDataKeys.PSI_FILE)
        anActionEvent.presentation.isEnabledAndVisible = psiFile?.virtualFile?.path?.endsWith(".kt") == true
    }

    fun onChosen(selectedValue: String?) {

    }
}
