package dsw.raf.geruMap.gui.swing.controller;

import com.sun.tools.javac.Main;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SaveImageAction extends AbstractGeruMapAction{

    public SaveImageAction()
    {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("images/save_as_image.png"));
        putValue(NAME,"Save as image");
        putValue(SHORT_DESCRIPTION,"Save current map as an image");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Component map = MainFrame.getInstance().getDesktop().getSelectedComponent();
        File file;

        JFileChooser jfc = new JFileChooser();

        if(jfc.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                jfc.resetChoosableFileFilters();

                file = jfc.getSelectedFile();
                file = new File(file.getAbsolutePath() + ".png");

                saveImage(map, file);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

    }

    public void saveImage(Component component, File file) {
        BufferedImage image = new BufferedImage(component.getWidth(),component.getHeight(), BufferedImage.TYPE_INT_RGB);

        component.paint(image.getGraphics());

        try
        {
            ImageIO.write(image,"png", file);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


    }
}
