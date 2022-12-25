package dsw.raf.geruMap.serializer;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.MapRepository.Implementation.Project;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapNodeTypeAdapter extends TypeAdapter<MapNode> {
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
}
