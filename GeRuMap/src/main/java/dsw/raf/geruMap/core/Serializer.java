package dsw.raf.geruMap.core;

import dsw.raf.geruMap.MapRepository.Implementation.Project;

import java.io.File;

public interface Serializer {
    Project loadProject(File file);
    void saveProject(Project node);
}
