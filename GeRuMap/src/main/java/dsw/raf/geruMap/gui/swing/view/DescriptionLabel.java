package dsw.raf.geruMap.gui.swing.view;



import javax.swing.*;
import java.util.Objects;

public class DescriptionLabel extends JLabel {

    public DescriptionLabel() { this.setAlignmentX(CENTER_ALIGNMENT);
        this.setText("<html>PROJEKAT:<br/>AUTOR:</html>");}
    String autor;
    String name;

    public void updateText(String name,String autor)
    {
        this.autor = Objects.requireNonNullElse(autor, " ");
        this.name = Objects.requireNonNullElse(name, " ");
        String string = "<html>PROJEKAT: "+this.name+"<br/> AUTOR: "+this.autor+"</html>";
        this.setText(string);
    }

}

