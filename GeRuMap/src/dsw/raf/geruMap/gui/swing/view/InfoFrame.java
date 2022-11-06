package dsw.raf.geruMap.gui.swing.view;

import dsw.raf.geruMap.gui.swing.controller.InfoAction;
import lombok.Getter;
import lombok.Setter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BoxView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
@Getter
@Setter
public class InfoFrame extends JFrame {
    private InfoFrame(){}
    private static InfoFrame instance = null;
    private ImageIcon img;
    public static InfoFrame getInstance(ImageIcon img)
    {
        if (instance == null)
        {
            instance = new InfoFrame();
            instance.setImg(img);
            instance.initialiseGUI(img);
        }
        return instance;
    }
    private void initialiseGUI(Icon img)
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension scr_size = kit.getScreenSize();
        int scr_height = scr_size.height;
        int scr_width = scr_size.width;
        setSize(scr_width/3, scr_height/3);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Info");

        JLabel name = new JLabel("Ime: ");
        JLabel surname = new JLabel("Prezime: ");
        JLabel index = new JLabel("Indeks: ");

        JPanel text = new JPanel();
        text.setMinimumSize(new Dimension(200,200));
        text.add(name);
        text.add(surname);
        text.add(index);
        text.setLayout(new BoxLayout(text,BoxLayout.Y_AXIS));

        ImageIcon test = new ImageIcon(this.img.getImage());
        ImagePanel imeg = new ImagePanel(test.getImage());
        imeg.setMinimumSize(new Dimension(200,200));

        JPanel all = new JPanel();
        all.setLayout(new GridLayout());
        all.setMinimumSize(new Dimension(400,400));
        all.add(imeg);
        all.add(text);


        this.add(all);
    }
}
