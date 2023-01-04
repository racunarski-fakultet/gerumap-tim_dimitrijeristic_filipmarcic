package dsw.raf.geruMap.serializer;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.sun.tools.javac.Main;
import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.MapRepository.Composite.MapNodeComposite;
import dsw.raf.geruMap.MapRepository.Implementation.*;
import dsw.raf.geruMap.MessageGenerator.MessageGenerator;
import dsw.raf.geruMap.MessageGenerator.MessageTypes;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import dsw.raf.geruMap.gui.swing.tree.model.MapTreeItem;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.StylePicker;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;

public class MapNodeTypeAdapter extends TypeAdapter<MapNode> implements JsonSerializer<MapNode> {
    Gson gson = new GsonBuilder().setLenient().registerTypeAdapter(MapNodeTypeAdapter.class, this).create();
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
//            obj.addProperty("counter",((Project) node).getCounter());
            obj.addProperty("folder",((Project) node).getHome_folder());
            System.out.println("seralizing proj");

            List<MapNode> maps = ((Project) node).getChildren();
            int cnt = 0;

            for (MapNode i : maps)
            {
                obj.addProperty("map " + cnt++,this.serialize(i,type,jsonSerializationContext).toString());
            }
        }
        else if(node instanceof Template)
        {
            obj.addProperty("type","Template");
//            obj.addProperty("counter",((MindMap) node).getCounter());
            System.out.println("seralizing template");
            obj.addProperty("centralthought",serialize(((MindMap) node).getCentral_thought(),type,jsonSerializationContext).toString());


            List<MapNode> elems = ((MindMap) node).getChildren();
            int cnt = 0;

            for (MapNode i : elems)
            {
                obj.addProperty("element " + cnt++,this.serialize(i,type,jsonSerializationContext).toString());
            }
        }
        else if (node instanceof MindMap)
        {
            obj.addProperty("type","MindMap");
//            obj.addProperty("counter",((MindMap) node).getCounter());
            obj.addProperty("centralthought",serialize(((MindMap) node).getCentral_thought(),type,jsonSerializationContext).toString());
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
            obj.addProperty("iscentral", ((Thought) node).isCentral());
            obj.addProperty("color", ((Thought) node).getPaint().getRGB());
            obj.addProperty("width",((Thought) node).getSize().getWidth());
            obj.addProperty("height", ((Thought) node).getSize().getHeight());
            obj.addProperty("x", ((Thought) node).getPosition().x);
            obj.addProperty("y", ((Thought) node).getPosition().y);
            obj.addProperty("thickness",((Thought)node).getThickness());
        }
        else if(node instanceof Link)
        {
            obj.addProperty("type","Link");
            obj.addProperty("color", ((Link) node).getPaint().getRGB());
            obj.addProperty("thickness",((Link)node).getThickness());
            obj.addProperty("parent", serialize(((Link) node).getParentThought(),type,jsonSerializationContext).toString());
            obj.addProperty("child", serialize(((Link) node).getChildThought(),type,jsonSerializationContext).toString());
        }

        return obj;
    }



    public MapNode deserialize(JsonElement jsonElement, MapNode parentNode) throws JsonParseException {

        if(jsonElement == null)
        {
            System.out.println("null");
            return null;
        }


        MapTreeImplementation mapTreeImplementation = (MapTreeImplementation) MainFrame.getInstance().getMapTree();
        JsonObject object = jsonElement.getAsJsonObject();
      //  System.out.println(object);
        String elemType = object.get("type").getAsString();
        MapNode node = null;


        if (elemType.equals("Project"))
        {
            int counter = 0;
            ProjectExplorer projectExplorer = AppCore.getInstance().getRep().getProjectExplorer();
            Project project = new Project(object.get("name").getAsString(), projectExplorer);

            if(object.get("author")!=null)
                project.setAutor(object.get("author").getAsString());

//            project.setCounter(object.get("counter").getAsInt());
            project.setHome_folder(object.get("folder").getAsString());

            MainFrame.getInstance().getMapTree().add_node((MapTreeItem) mapTreeImplementation.getTreeModel().getRoot(), project);

            while (object.get("map " + counter) != null) {
                JsonElement jsonElement1;
                String string = object.get("map " + counter).getAsString();
                string.replace("\\","");
                jsonElement1 = gson.fromJson(string, JsonElement.class);

                MapNode node1 = deserialize(jsonElement1,project);
                node1.setParent(project);
                counter++;
            }
            node = project;
        }
        else if (elemType.equals("MindMap"))
        {
            System.out.println("deserializing map");
            MindMap mindMap = new MindMap(object.get("name").getAsString(), (MapNodeComposite) parentNode);
//            mindMap.setCounter(object.get("counter").getAsInt());
            int counter = 0;
            MainFrame.getInstance().getMapTree().add_node(mapTreeImplementation.findNode(parentNode), mindMap);

            JsonElement pom;
            String string1 = object.get("centralthought").getAsString();
            string1.replace("\\","");
            System.out.println();
            pom = gson.fromJson(string1, JsonElement.class);
            Thought central = (Thought) deserialize(pom,mindMap);


            while (object.get("element " + counter)!= null)
            {
                JsonElement jsonElement1;
                String string = object.get("element " + counter).getAsString();
                string.replace("\\","");
                jsonElement1 = gson.fromJson(string, JsonElement.class);

                MapNode node1 = deserialize(jsonElement1,mindMap);
                MainFrame.getInstance().getMapTree().add_node(mapTreeImplementation.findNode(mindMap), node1);
                counter++;
            }
            MapTreeItem temp1 = mapTreeImplementation.findNode(central);
            mindMap.setCentral_thought((Thought) temp1.getMapNode());
            node = mindMap;
        }
        else if (elemType.equals("Thought")) {
            Color color = new Color(object.get("color").getAsInt());
            Point thoughtPoint = new Point(object.get("x").getAsInt(), object.get("y").getAsInt());
            Dimension thoughtDimension = new Dimension(object.get("height").getAsInt(), object.get("width").getAsInt());
            node = new Thought(object.get("name").getAsString(), (MapNodeComposite) parentNode, thoughtPoint, thoughtDimension, object.get("thickness").getAsInt(), color);
            ((Thought)node).setCentral(object.get("iscentral").getAsBoolean());
        }
        else if (elemType.equals("Link"))
        {
            Color color = new Color(object.get("color").getAsInt());
            JsonElement jsonElement1;
            String string = object.get("parent").getAsString();
            string.replace("\\","");
            jsonElement1 = gson.fromJson(string, JsonElement.class);
            MapNode parent = deserialize(jsonElement1,parentNode);
            string = object.get("child").getAsString();
            string.replace("\\","");
            jsonElement1 = gson.fromJson(string, JsonElement.class);
            MapNode child = deserialize(jsonElement1,parentNode);
            MapTreeItem temp1 = mapTreeImplementation.findNode(parent);
            MapTreeItem temp2 = mapTreeImplementation.findNode(child);


            node= new Link((MapNodeComposite) parentNode, (Thought) temp1.getMapNode(), (Thought) temp2.getMapNode(),object.get("thickness").getAsInt(),color);
        }
        else if (elemType.equals("Template"))
        {
            MindMap mindMap = (MindMap) parentNode;
            boolean flag = mindMap.getChildren().isEmpty();
//
//                if(!flag)
//                {
//                    JsonElement jelement = serialize(mindMap1,null,null);
//                    String str = gson.toJson(jelement);
//                    String str2 = str.replace("MindMap","Template");
//                    String str3 = str2.replace(mindMap.getName(),object.get("name").getAsString());
//                    System.out.println("AAAA");
//                  //  String str4 = str3.replace("\"counter\":"+pom.get("counter").getAsString(),"\"counter\":"+object.get("counter"));
//                    System.out.println("test"+str3);
//                    // System.out.println("second time = "+str);
//                    System.out.println("test"+gson.toJson(jsonElement));
//                  //  System.out.println(str3.equals(jsonElement.getAsString()));
//                    if(str3==jsonElement.getAsString().toString())
//                    {
//                        System.out.println("RETURNING NULL");
//
//                        return null;
//                    }
//                }
            if(mindMap.getCentral_thought()!=null)
            {
                AppCore.getInstance().getGenerator().generateMessage("Vec postoji centralni pojam", MessageTypes.ERROR_MESSAGE);
                return null;

            }
//
            System.out.println("CONTINUING");
//            mindMap.setCounter(object.get("counter").getAsInt());
            mindMap.getMaprep().getMapSelection().remove_all();
            int counter = 0;


            JsonElement pom;
            String string1 = object.get("centralthought").getAsString();
            string1.replace("\\","");
            System.out.println();
            pom = gson.fromJson(string1, JsonElement.class);
            Thought central = (Thought) deserialize(pom,mindMap);

            while (object.get("element " + counter)!= null)
            {
                JsonElement jsonElement1;
                String string = object.get("element " + counter).getAsString();
                string.replace("\\","");
                jsonElement1 = gson.fromJson(string, JsonElement.class);

                MapNode node1 = deserialize(jsonElement1,mindMap);

                if(mindMap.getCentral_thought()!=null)
                {
                    if (node1 instanceof Thought)
                        if (((Thought) node1).isCentral())
                        {
                            ((Thought) node1).setCentral(false);
                            ((Thought) node1).setThickness(StylePicker.getInstance().getThickness());
                        }
                }




                MainFrame.getInstance().getMapTree().add_node(mapTreeImplementation.findNode(mindMap), node1);
                if(!flag)
                {
                    mindMap.getMaprep().getMapSelection().add_selected((Element) node1);
                }
                counter++;
            }
            if(mindMap.getCentral_thought()==null)
            {
                MapTreeItem temp1 = mapTreeImplementation.findNode(central);
                mindMap.setCentral_thought((Thought) temp1.getMapNode());
            }

            return parentNode.getParent();
        }
        return node;
    }


}
