package dsw.raf.geruMap.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.ProjectExplorer;
import dsw.raf.geruMap.MapRepository.Implementation.Template;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.MessageGenerator.MessageTypes;
import dsw.raf.geruMap.core.Serializer;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class GsonSerializer implements Serializer
{
    private final Gson gson = new GsonBuilder().setLenient().registerTypeAdapter(MapNodeTypeAdapter.class, new MapNodeTypeAdapter()).create();
    private final MapNodeTypeAdapter adapter = new MapNodeTypeAdapter();
    @Override
    public MapNode loadProject(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            String string = Files.readString(file.toPath());
            String string2= string.replaceAll("Template","MindMap");

            MapNode temp = MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
            MapNode node = null;
            if(temp instanceof Project)
                node = adapter.deserialize(gson.fromJson(string2, JsonElement.class),temp);
            else if(temp instanceof MindMap)
                node = adapter.deserialize(gson.fromJson(string2, JsonElement.class),temp.getParent());
            else if(temp instanceof ProjectExplorer)
                node = adapter.deserialize(gson.fromJson(string2, JsonElement.class),temp);
//           if(Project.class.isAssignableFrom(MapNode.class)||(MindMap.class.isAssignableFrom(MapNode.class)))
            return node;
//           return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveProject(Project project)
    {
        try (FileWriter writer = new FileWriter(project.getHome_folder())) {
            gson.toJson(adapter.serialize(project,null,null), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project loadTemplate(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            if(!(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof MindMap))
                return null;
            else//((MindMap) MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode()).getChildren().isEmpty())
                return (Project) adapter.deserialize(gson.fromJson(Files.readString(file.toPath()), JsonElement.class),MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveTemplate(Template map,String homeFolder) {
        try (FileWriter writer = new FileWriter(homeFolder)) {
            gson.toJson(adapter.serialize(map,null,null), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
