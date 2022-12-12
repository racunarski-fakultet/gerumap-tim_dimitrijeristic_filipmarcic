package dsw.raf.geruMap.gui.swing.view;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Implementation.Element;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter
public class StylePicker extends JFrame {
    private static JColorChooser colorChooserOut;
    private static JColorChooser getColorChooserIn;
    private static TextField thickness;
    private static Button selectionButton;
    private StylePicker(){}
    private static StylePicker instance = null;

    public static StylePicker getInstance()
    {
        if (instance == null)
        {
            instance = new StylePicker();
            instance.colorChooserOut = new JColorChooser();
            instance.colorChooserOut.setColor(Color.BLACK);
            instance.thickness = new TextField();
            thickness.setText("2");
            instance.selectionButton = new Button("Accept");

            instance.selectionButton.addActionListener((e)->{
                    for(Element element:((MapRepositoryImpl) AppCore.getInstance().getRep()).getMapSelection().getSelection())
                    {
                        element.setPaint(colorChooserOut.getColor());
                        element.setThickness(Integer.parseInt(thickness.getText()));
                        MainFrame.getInstance().getDesktop().repaint();

                    }
                instance.setVisible(false);
            });
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
        panel.add(colorChooserOut);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2,BoxLayout.X_AXIS));
        panel2.add(new Label("Choose stroke: "));
        panel2.add(thickness);
        panel2.add(selectionButton);
        panel.add(panel2);
        this.add(panel);


    }

    public JColorChooser getColorChooserOut() {
        return instance.colorChooserOut;
    }

    public int getThickness() {
        return Integer.parseInt(instance.thickness.getText());
    }
}
