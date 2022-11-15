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
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Info");

        JLabel name = new JLabel("Ime: ");
        name.setBorder(BorderFactory.createEmptyBorder(7,5,7,5));
        JLabel surname = new JLabel("Prezime: ");
        surname.setBorder(BorderFactory.createEmptyBorder(7,5,7,5));
        JLabel index = new JLabel("Indeks: ");
        index.setBorder(BorderFactory.createEmptyBorder(7,5,7,5));

        JPanel text = new JPanel();
        text.setBorder(BorderFactory.createEmptyBorder(7,5,7,5));
        text.add(name);
        text.add(surname);
        text.add(index);
        text.setLayout(new BoxLayout(text,BoxLayout.PAGE_AXIS));

        ImageIcon test = new ImageIcon(this.img.getImage());
        ImagePanel imeg = new ImagePanel(test.getImage());
        imeg.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JPanel all = new JPanel();
        all.setLayout(new GridLayout());
        all.add(imeg);
        all.add(text);

        this.setMinimumSize(new Dimension(300,200));
        this.setMaximumSize(new Dimension(350,200));
        this.add(all);
    }
}
