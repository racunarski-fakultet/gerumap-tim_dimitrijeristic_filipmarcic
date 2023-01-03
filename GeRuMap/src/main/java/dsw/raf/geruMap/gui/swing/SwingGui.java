package dsw.raf.geruMap.gui.swing;

import com.sun.tools.javac.Main;
import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MessageGenerator.Message;
import dsw.raf.geruMap.MessageGenerator.MessageGenerator;
import dsw.raf.geruMap.core.Gui;
import dsw.raf.geruMap.core.Subscriber;
import dsw.raf.geruMap.gui.swing.commands.CommandManager;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;

public class SwingGui implements Gui
{
    private CommandManager commandManager;
    private MainFrame instance;
    public void start()
    {
        instance = MainFrame.getInstance();
        commandManager = new CommandManager();

        disableRedoAction();
        disableUndoAction();

        AppCore.getInstance().getGenerator().subscribe(this);
        MainFrame.getInstance().setVisible(true);
    }

    @Override
    public void disableUndoAction() {
        MainFrame.getInstance().getAct_man().getUndo_act().setEnabled(false);

    }
    @Override
    public void disableRedoAction() {
        MainFrame.getInstance().getAct_man().getRedo_act().setEnabled(false);

    }
    @Override
    public void enableRedoAction() {
        MainFrame.getInstance().getAct_man().getRedo_act().setEnabled(true);

    }
    @Override
    public void enableUndoAction() {
        MainFrame.getInstance().getAct_man().getUndo_act().setEnabled(true);

    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void update(Object var1) {
        JOptionPane.showMessageDialog(MainFrame.getInstance(),((Message)var1).returnNoTimestamp(),"",((Message)var1).getType().getValue());
    }
}
