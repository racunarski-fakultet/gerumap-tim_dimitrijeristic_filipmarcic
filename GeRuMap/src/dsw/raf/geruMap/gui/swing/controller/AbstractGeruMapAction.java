package dsw.raf.geruMap.gui.swing.controller;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public abstract class AbstractGeruMapAction extends AbstractAction implements ActionPerformed
{
    public ImageIcon loadIcon(String fileName){

        URL imageURL = getClass().getResource(fileName);
        ImageIcon icon = null;

        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        }
        else {
            System.err.println("Resource not found: " + fileName);
        }
        return icon;
    }


}
