package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.view.MapTreeView;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class LoadAction extends AbstractGeruMapAction {
    public LoadAction()
    {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/load.png"));
        putValue(NAME,"Load");
        putValue(SHORT_DESCRIPTION,"Load a saved project");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = jfc.getSelectedFile();
                MapNode p = AppCore.getInstance().getSerializer().loadProject(file);
                if(p instanceof Project)
                    ((Project) p).setChanged(false);
                else if(p instanceof MindMap)
                {
                    Project parent = (Project) p.getParent();
                    parent.setChanged(true);
                }

                MapTreeView treeView = ((MapTreeImplementation)MainFrame.getInstance().getMapTree()).getTreeView();
                SwingUtilities.updateComponentTreeUI(treeView);
                treeView.expandPath(treeView.getSelectionPath());


            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
