package dsw.raf.geruMap;

import java.lang.reflect.AccessibleObject;

public class AppCore
{
    private AppCore instance = null;
    public static void main(String Args[])
    {

    }
    public AppCore getInstance()
    {
        if(instance.equals(null))
            instance = new AppCore();

        return instance;
    }
    private AppCore()
    {
    }
}
