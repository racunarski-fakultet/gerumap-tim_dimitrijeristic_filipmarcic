package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.gui.swing.view.StylePicker;

import javax.swing.*;
import javax.swing.text.Style;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class StyleAction extends AbstractGeruMapAction {

    public StyleAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("images/style.png"));
        putValue(NAME, "Change style");
        putValue(SHORT_DESCRIPTION, "Change style of an element");
    }

    public void actionPerformed(ActionEvent arg0) {
        StylePicker stylePicker = StylePicker.getInstance();
        stylePicker.setVisible(true);
    }
}
