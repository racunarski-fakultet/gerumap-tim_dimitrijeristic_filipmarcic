package dsw.raf.geruMap.gui.swing.view;

import javax.swing.*;

public class ProjectView extends JPanel {
    public ProjectView() {
        if(this.getParent()!=null)
            this.setSize(this.getParent().getSize());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }
}
