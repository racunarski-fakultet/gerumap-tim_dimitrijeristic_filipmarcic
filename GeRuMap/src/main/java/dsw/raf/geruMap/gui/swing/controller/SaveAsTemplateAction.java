package dsw.raf.geruMap.gui.swing.controller;

import com.sun.tools.javac.Main;
import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.Template;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class SaveAsTemplateAction extends AbstractGeruMapAction{
    private String templateGallery = "./GeRuMap/src/main/resources/Template gallery";

    public SaveAsTemplateAction()
    {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/plus.png"));
        putValue(NAME,"Save as template");
        putValue(SHORT_DESCRIPTION,"Save current map as a template  ");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(templateGallery);
        jfc.setFileView(new FileView() {
            @Override
            public Boolean isTraversable(File f) {
                return false;
            }
        });
        //MindMap map = (MindMap) ((MapView)MainFrame.getInstance().getDesktop().getSelectedComponent()).getMyMap();
        MindMap map = null;
        if(!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof MindMap))
            return;
        else
            map = (MindMap) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
        if(map==null)
            return;
        Template template=null;
        String fileName = JOptionPane.showInputDialog("Unesi ime šablona");
        File homeDir = new File(templateGallery);

        if(!homeDir.exists()&&!homeDir.isDirectory())
        {
            homeDir.mkdir();
        }

        if (!fileName.isEmpty())
        {
                template = new Template(map, fileName);
        }
        else
        {
            return;
        }

        AppCore.getInstance().getSerializer().saveTemplate(template,homeDir.getAbsolutePath()+"/"+fileName);

        ((Project)map.getParent()).setChanged(true);
    }
}
