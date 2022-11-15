package dsw.raf.geruMap.gui.swing;

import com.sun.tools.javac.Main;
import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MessageGenerator.Message;
import dsw.raf.geruMap.MessageGenerator.MessageGenerator;
import dsw.raf.geruMap.core.Gui;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;

public class SwingGui implements Gui
{
    public void start()
    {
        AppCore.getInstance().getGenerator().subscribe(this);
        MainFrame.getInstance().setVisible(true);
    }

    @Override
    public void update(Object var1) {
        JOptionPane.showMessageDialog(MainFrame.getInstance(),((Message)var1).returnNoTimestamp(),"",((Message)var1).getType().getValue());
    }
}
