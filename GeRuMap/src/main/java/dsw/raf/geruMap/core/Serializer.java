package dsw.raf.geruMap.core;

import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.Template;

import java.io.File;

public interface Serializer {
    MapNode loadProject(File file);
    void saveProject(Project node);
    Project loadTemplate(File file);
    void saveTemplate(Template map,String homeFolder);
}
