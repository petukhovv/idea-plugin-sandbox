package org.jetbrains.kotlin

import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.openapi.ui.popup.JBPopupListener
import com.intellij.openapi.ui.popup.util.BaseListPopupStep
import javax.swing.event.ListSelectionEvent

class HelloAction : AnAction("Hello") {
    lateinit var pr: Project

    override fun actionPerformed(event: AnActionEvent) {
        pr = event.project!!
        val l = BaseListPopupStep("Choose test runner", "1", "2", "3", "4")
        val list = JBPopupFactory.getInstance().createListPopup(l)
        list.showUnderneathOf(event.inputEvent.component)

    }

    override fun update(anActionEvent: AnActionEvent) {
        val psiFile = anActionEvent.getData(CommonDataKeys.PSI_FILE)
        anActionEvent.presentation.isEnabledAndVisible = psiFile?.virtualFile?.path?.endsWith(".kt") == true
    }

    fun onSelect(e: ListSelectionEvent) {
        Messages.showMessageDialog(pr, "Hello world!", e.firstIndex.toString(), Messages.getInformationIcon())
    }
}
