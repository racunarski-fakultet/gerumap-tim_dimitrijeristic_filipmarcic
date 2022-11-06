package dsw.raf.geruMap.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    private Image img;

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
        this.img = img;
    }

    /*
     * metoda paintComponent se redefinise iz nasledjene klase
     * i ona ce biti zaduzena da iscrta zadatu sliku na pozadini JPanel-a
     */
    public void paintComponent(Graphics g) {
        g.drawImage(img, (int)(this.getSize().getWidth()-img.getWidth(null))/2,
                (int)(this.getSize().getHeight()-img.getHeight(null))/2, null);
    }

}
