package dsw.raf.geruMap.gui.swing.view;

import lombok.Getter;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;

@Getter
public class StylePicker extends JFrame {
    private static JColorChooser colorChooser;
    private static TextField thickness;
    private StylePicker(){}
    private static StylePicker instance = null;

    public static StylePicker getInstance()
    {
        if (instance == null)
        {
            instance = new StylePicker();
            instance.colorChooser = new JColorChooser();
            instance.thickness = new TextField();
            instance.initialiseGUI();
        }
        return instance;
    }
    private void initialiseGUI()
    {
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Style picker");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(colorChooser);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.X_AXIS));
        panel2.add(new Label("Choose stroke: "));
        panel2.add(thickness);
        panel.add(panel2);

        this.add(panel);


    }

}
