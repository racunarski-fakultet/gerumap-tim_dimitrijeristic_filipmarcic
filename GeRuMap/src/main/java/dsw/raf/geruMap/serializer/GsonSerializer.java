package dsw.raf.geruMap.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.core.Serializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonSerializer implements Serializer
{
    private final Gson gson = new GsonBuilder().registerTypeAdapter(MapNodeTypeAdapter.class, new MapNodeTypeAdapter()).create();
    private final MapNodeTypeAdapter adapter = new MapNodeTypeAdapter();
    @Override
    public Project loadProject(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            return gson.fromJson(fileReader, Project.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveProject(Project project) {
        try (FileWriter writer = new FileWriter(project.getHome_folder())) {
            gson.toJson(adapter.serialize(project,null,null), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
