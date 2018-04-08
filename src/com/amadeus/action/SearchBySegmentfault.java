package com.amadeus.action;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import org.apache.commons.lang3.StringUtils;

import javax.swing.ImageIcon;

/**
 * 在Segmentfault网站搜索问题
 */
public class SearchBySegmentfault extends AnAction {

    public SearchBySegmentfault(){
        super(null, null, new ImageIcon(
                SearchBySegmentfault.class.getClassLoader().getResource("icon/sf-icon.png")
        ));
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        String segmentfault = "https://segmentfault.com/search?q=";
        CaretModel caretModel = e.getData(LangDataKeys.EDITOR).getCaretModel();
        Caret currentCaret = caretModel.getCurrentCaret();
        String selectedText = currentCaret.getSelectedText().trim();
        try{
            selectedText = selectedText.replaceAll(" ", "+");
        }catch (NullPointerException ex){
            System.out.println("Text can't be empty" + ex.getMessage());
        }

        if (StringUtils.isNotBlank(selectedText)){
            BrowserUtil.browse(segmentfault + selectedText);
        }else {
            BrowserUtil.browse(segmentfault);
        }
    }
}
