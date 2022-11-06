package dsw.raf.geruMap.gui.swing.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class InfoFrame extends JFrame {

    private InfoFrame(){};

    private static InfoFrame instance = null;

    public static InfoFrame getInstance()
    {
        if (instance == null)
        {
            instance = new InfoFrame();
            instance.initialiseGUI();
        }

        return instance;
    }

    private void initialiseGUI()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension scr_size = kit.getScreenSize();
        int scr_height = scr_size.height;
        int scr_width = scr_size.width;
        setSize(scr_width/4, scr_height/4);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Info");

        JPanel desktop = new JPanel();

        JLabel ime = new JLabel("Ime");
        JLabel prezime = new JLabel("Prezime");
        JLabel index = new JLabel("123Index");
        ImagePanel slika = new ImagePanel(new ImageIcon("user.png").getImage());
        JPanel text = new JPanel();
        text.add(ime);
        text.add(prezime);
        text.add(index);
        JSplitPane sp_pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,slika,text);

        getContentPane().add(sp_pane,BorderLayout.CENTER);


    }
}
