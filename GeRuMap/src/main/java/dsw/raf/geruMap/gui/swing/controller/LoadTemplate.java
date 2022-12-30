package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.view.MapTreeView;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.awt.event.ActionEvent;
import java.io.File;

public class LoadTemplate extends AbstractGeruMapAction {
    private String templateGallery = "./GeRuMap/src/main/resources/Template gallery";
    public LoadTemplate()
    {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/plus.png"));
        putValue(NAME,"Load template");
        putValue(SHORT_DESCRIPTION,"Load a saved template");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        File homeDir = new File(templateGallery);
        if(!homeDir.exists()&&!homeDir.isDirectory())
        {
            homeDir.mkdir();
        }
        JFileChooser jfc = new JFileChooser(homeDir.getAbsolutePath());
        jfc.setFileView(new FileView() {
            @Override
            public Boolean isTraversable(File f) {
                return false;
            }
        });
        jfc.showOpenDialog(MainFrame.getInstance());
        if (jfc.getSelectedFile()!=null) {
            try {
                Project p = AppCore.getInstance().getSerializer().loadTemplate(jfc.getSelectedFile());
                if(p!=null)
                {
                    p.setChanged(true);
                }
                MapTreeView treeView = ((MapTreeImplementation)MainFrame.getInstance().getMapTree()).getTreeView();
                SwingUtilities.updateComponentTreeUI(treeView);
                treeView.expandPath(treeView.getSelectionPath());


            } catch (Exception ex) {
              //  ex.printStackTrace();
            }
        }
    }
}
