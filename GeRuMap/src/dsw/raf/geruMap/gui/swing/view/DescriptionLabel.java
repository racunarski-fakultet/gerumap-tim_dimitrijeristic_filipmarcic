package dsw.raf.geruMap.gui.swing.view;

import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.core.Subscriber;

import javax.swing.*;

public class DescriptionLabel extends JLabel implements Subscriber {

    public DescriptionLabel() { this.setAlignmentX(CENTER_ALIGNMENT);
    this.setText("<html>PROJEKAT:<br/>AUTOR:</html>");}

    @Override
    public void update(Object var1) {
        if(var1 instanceof Project)
        {
            Project proj =(Project) var1;
            String autor;
            if(proj.getAutor()!=null)
                autor = proj.getAutor();
            else
                autor="";
            String name;
            if(proj.getName()!=null)
                name = proj.getName();
            else
                name="";

            String string = "<html>PROJEKAT: "+name+"<br/> AUTOR: "+autor+"</html>";
            this.setText(string);
        }
        else if(var1 instanceof ProjectExplorer)
            this.setText("<html>PROJEKAT:<br/>AUTOR:</html>");
    }
}
