package dsw.raf.geruMap.gui.swing.view;

import javax.swing.*;

public class TabbedPaneFrame extends JPanel {
    public TabbedPaneFrame() {
        if(this.getParent()!=null)
            this.setSize(this.getParent().getSize());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }
}
