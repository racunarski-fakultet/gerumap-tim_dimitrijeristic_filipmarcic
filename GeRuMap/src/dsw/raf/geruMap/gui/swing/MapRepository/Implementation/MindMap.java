package dsw.raf.geruMap.gui.swing.MapRepository.Implementation;

import dsw.raf.geruMap.gui.swing.MapRepository.Composite.MapNode;
import dsw.raf.geruMap.gui.swing.MapRepository.Composite.MapNodeComposite;

public class MindMap extends MapNodeComposite
{

    @Override
    protected boolean allowsChild(MapNode child) {return child instanceof Element;}
}
