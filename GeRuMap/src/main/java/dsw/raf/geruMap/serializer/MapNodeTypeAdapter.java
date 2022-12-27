package dsw.raf.geruMap.serializer;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sun.tools.javac.Main;
import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.*;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;

import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;

public class MapNodeTypeAdapter extends TypeAdapter<MapNode> implements JsonSerializer<MapNode>,JsonDeserializer<MapNode> {
    @Override
    public void write(JsonWriter out, MapNode project) throws IOException
    {
        out.beginObject();
        out.name("project_name");
        out.value(project.toString());
        out.name("maps");
        out.beginArray();

        for (MapNode i : ((Project)project).getChildren())
        {
            out.value(i.toString());
            out.name(i.getName());
            out.beginArray();

            for(MapNode j : ((MindMap)i).getChildren())
            {
                out.value(j.toString());
            }

            out.endArray();
        }

        out.endArray();
        out.endObject();
    }

    @Override
    public Project read(JsonReader in) throws IOException
    {
        String proj_name;
        List<String> map_names = new ArrayList<>();
        Map<String,List<String>> node_names = new HashMap<>();

        in.beginObject();

        while(in.hasNext())
        {
            String name = in.nextName();

            if(name.equals("project_name"))
            {
                proj_name = in.nextString();
            }
            else if (name.equals("maps"))
            {
                map_names.add(in.nextString());
            }
            else
            {
                if (!node_names.containsKey(name))
                {
                    node_names.put(name,new ArrayList<>());
                }

                node_names.get(name).add(in.nextString());
            }
        }



        return null;
    }

    @Override
    public JsonElement serialize(MapNode node, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject obj = new JsonObject();

        obj.addProperty("name",node.getName());

        if (node instanceof Project)
        {
            obj.addProperty("type","Project");
            obj.addProperty("author",((Project) node).getAutor());
            obj.addProperty("counter",((Project) node).getCounter());
            obj.addProperty("folder",((Project) node).getHome_folder());
            System.out.println("seralizing proj");

            List<MapNode> maps = ((Project) node).getChildren();
            int cnt = 0;

            for (MapNode i : maps)
            {
                obj.addProperty("map " + cnt++,this.serialize(i,type,jsonSerializationContext).toString());
            }
        }
        else if (node instanceof MindMap)
        {
            obj.addProperty("type","MindMap");
            obj.addProperty("counter",((MindMap) node).getCounter());
            System.out.println("seralizing map");

            List<MapNode> elems = ((MindMap) node).getChildren();
            int cnt = 0;

            for (MapNode i : elems)
            {
                obj.addProperty("element " + cnt++,this.serialize(i,type,jsonSerializationContext).toString());
            }
        }
        else if (node instanceof Thought)
        {
            obj.addProperty("type","Thought");
            obj.addProperty("color", ((Thought) node).getPaint().getRGB());
            obj.addProperty("width",((Thought) node).getSize().getWidth());
            obj.addProperty("height", ((Thought) node).getSize().getHeight());
            obj.addProperty("x", ((Thought) node).getPosition().x);
            obj.addProperty("y", ((Thought) node).getPosition().y);
            System.out.println("seralizing thought");
        }
        else
        {
            obj.addProperty("type","Link");
            obj.addProperty("color", ((Link) node).getPaint().getRGB());
//            obj.addProperty("width",((Link) node).getSize().getWidth());
//            obj.addProperty("height", ((Link) node).getSize().getHeight());
//            obj.addProperty("x", ((Link) node).getPosition().x);
//            obj.addProperty("y", ((Link) node).getPosition().y);
            obj.addProperty("parent", serialize(((Link) node).getParentThought(),type,jsonSerializationContext).toString());
            obj.addProperty("child", serialize(((Link) node).getChildThought(),type,jsonSerializationContext).toString());
            System.out.println("seralizing link");
        }

        return obj;
    }

    @Override
    public MapNode deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if(jsonElement.isJsonNull())
            return null;
        MapTreeImplementation mapTreeImplementation = (MapTreeImplementation) MainFrame.getInstance().getMapTree();
        JsonObject object = jsonElement.getAsJsonObject();
        String elemType = object.get("type").toString();

        MapNode node = null;


        if (elemType == "Project") {
            int counter = 0;
            ProjectExplorer projectExplorer = AppCore.getInstance().getRep().getProjectExplorer();
            Project project = new Project(object.get("name").getAsString(), projectExplorer);
            project.setAutor(object.get("author").getAsString());
            project.setCounter(object.get("counter").getAsInt());
            project.setHome_folder(object.get("folder").getAsString());
            MainFrame.getInstance().getMapTree().add_node((MapTreeItem) mapTreeImplementation.getTreeModel().getRoot(), project);

            while (deserialize(object.get("map " + counter), type, jsonDeserializationContext) != null) {
                MapNode node1 = deserialize(object.get("map " + counter), type, jsonDeserializationContext);
                node1.setParent(project);
                MainFrame.getInstance().getMapTree().add_node(mapTreeImplementation.findNode(project), node1);
                counter++;
            }
            node = project;
        } else if (elemType == "MindMap") {
            MindMap mindMap = new MindMap(object.get("name").getAsString(), null);
            mindMap.setCounter(object.get("counter").getAsInt());
            int counter = 0;
            while (deserialize(object.get("element " + counter), type, jsonDeserializationContext) != null) ;
            {

                MapNode node1 = deserialize(object.get("element " + counter), type, jsonDeserializationContext);
                node1.setParent(mindMap);
                MainFrame.getInstance().getMapTree().add_node(mapTreeImplementation.findNode(mindMap), node1);
                counter++;
            }
            node = mindMap;
        } else if (elemType == "Thought") {
            Color color = new Color(object.get("paint").getAsInt());
            Point thoughtPoint = new Point(object.get("x").getAsInt(), object.get("y").getAsInt());
            Dimension thoughtDimension = new Dimension(object.get("height").getAsInt(), object.get("width").getAsInt());
            Thought thought = new Thought(object.get("name").getAsString(), null, thoughtPoint, thoughtDimension, object.get("thickness").getAsInt(), color);
            node = thought;
        } else if (elemType == "Link")
        {
            Color color = new Color(object.get("paint").getAsInt());
            MapNode parent = deserialize(object.get("parent"),type,jsonDeserializationContext);
            MapNode child = deserialize(object.get("child"),type,jsonDeserializationContext);
            Link link = new Link((Thought) parent, (Thought) child,object.get("thickness").getAsInt(),color);
            node=link;

        }

        return node;
    }
}
