package dsw.raf.geruMap.serializer;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.Link;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;
import dsw.raf.geruMap.MapRepository.Implementation.Thought;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class MapNodeTypeAdapter extends TypeAdapter<MapNode> implements JsonSerializer<MapNode> {
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
            obj.addProperty("type","project");
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
            obj.addProperty("width",((Link) node).getSize().getWidth());
            obj.addProperty("height", ((Link) node).getSize().getHeight());
            obj.addProperty("x", ((Link) node).getPosition().x);
            obj.addProperty("y", ((Link) node).getPosition().y);
            obj.addProperty("parent", serialize(((Link) node).getParentThought(),type,jsonSerializationContext).getAsString());
            obj.addProperty("child", serialize(((Link) node).getChildThought(),type,jsonSerializationContext).getAsString());
            System.out.println("seralizing link");
        }

        return obj;
    }
}
