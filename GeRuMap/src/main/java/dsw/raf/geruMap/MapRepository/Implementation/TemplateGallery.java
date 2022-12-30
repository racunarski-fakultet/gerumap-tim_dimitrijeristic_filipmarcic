package dsw.raf.geruMap.MapRepository.Implementation;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class TemplateGallery
{
    ArrayList<Template> templates;

    public boolean add_template(MindMap temp,String name)
    {
      //  templates.add(new Template(temp,name,));
        return true;
    }
}
