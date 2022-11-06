package dsw.raf.geruMap.gui.swing.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
@Getter
@Setter
public class ImagePanel extends JPanel{
    private Image img;
    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
        this.img = img;
    }
    public void paintComponent(Graphics g) {
        g.drawImage(img, (int)(this.getSize().getWidth()-img.getWidth(null))/2,
                (int)(this.getSize().getHeight()-img.getHeight(null))/2, 100,133,null);
    }
}
