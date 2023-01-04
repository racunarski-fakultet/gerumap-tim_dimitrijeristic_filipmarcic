package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveAction extends AbstractGeruMapAction{
    public SaveAction()
    {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/save.png"));
        putValue(NAME,"Save");
        putValue(SHORT_DESCRIPTION,"Save current project");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Project project = ((MapRepositoryImpl)AppCore.getInstance().getRep()).getSelectedProj();
        File projectFile = null;
        JFileChooser jfc = new JFileChooser();

        if (!project.isChanged()) {
            return;
        }

        if (project.getHome_folder() == null || project.getHome_folder().isEmpty()) {
            if (jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                projectFile = jfc.getSelectedFile();

                project.setHome_folder(projectFile.getPath());
            } else {
                return;
            }

        }

        AppCore.getInstance().getSerializer().saveProject(project);

        project.setChanged(false);
    }
}
