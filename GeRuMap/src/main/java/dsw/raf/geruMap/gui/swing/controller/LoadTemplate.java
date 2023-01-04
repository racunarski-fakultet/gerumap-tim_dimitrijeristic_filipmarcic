package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.view.MapTreeView;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class LoadTemplate extends AbstractGeruMapAction {
    private String templateGallery = "./GeRuMap/src/main/resources/Template gallery";
    public LoadTemplate()
    {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/load_template.png"));
        putValue(NAME,"Load template");
        putValue(SHORT_DESCRIPTION,"Load a saved template");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        int create=JOptionPane.showConfirmDialog(MainFrame.getInstance().getDesktop(),"Create a new map?",null,JOptionPane.YES_NO_OPTION );
        System.out.println(create);


        File homeDir = new File(templateGallery);
        if(!homeDir.exists()&&!homeDir.isDirectory())
        {
            homeDir.mkdir();
        }
        JFileChooser jfc = new JFileChooser(homeDir.getAbsolutePath());
//        jfc.setFileView(new FileView() {
//            @Override
//            public Boolean isTraversable(File f) {
//                return homeDir.getAbsolutePath().equals(f);
//            }
//        });
        disableNav(jfc);
        jfc.showOpenDialog(MainFrame.getInstance());

        if (jfc.getSelectedFile()!=null) {
            try {
                MapNode p = null;
                if(create==0)
                {
                   p = AppCore.getInstance().getSerializer().loadProject(jfc.getSelectedFile());
                }
                else
                {
                    p = AppCore.getInstance().getSerializer().loadTemplate(jfc.getSelectedFile());

                }

                    if(p!=null)
                    {
                        if(create!=0)
                            ((Project) p).setChanged(true);
                        else
                            ((Project)p.getParent()).setChanged(true);
                    }

                MapTreeView treeView = ((MapTreeImplementation)MainFrame.getInstance().getMapTree()).getTreeView();
                SwingUtilities.updateComponentTreeUI(treeView);
                treeView.expandPath(treeView.getSelectionPath());


            } catch (Exception ex) {
              //  ex.printStackTrace();
            }
        }
    }

    private void disableNav(Container c) {
        for (Component x : c.getComponents())
            if (x instanceof JComboBox)
                ((JComboBox)x).setEnabled(false);
            else if (x instanceof JButton) {
                String text = ((JButton)x).getText();
                if (text == null || text.isEmpty())
                    ((JButton)x).setEnabled(false);
            }
            else if (x instanceof Container)
                disableNav((Container)x);
    }
}
