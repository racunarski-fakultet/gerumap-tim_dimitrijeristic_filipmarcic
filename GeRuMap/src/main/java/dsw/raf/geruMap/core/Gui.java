package dsw.raf.geruMap.core;

import dsw.raf.geruMap.gui.swing.commands.CommandManager;

public interface Gui extends Subscriber {
    void start();
    void disableUndoAction();
    void disableRedoAction();

    void enableUndoAction();
    void enableRedoAction();

    CommandManager getCommandManager();
}
