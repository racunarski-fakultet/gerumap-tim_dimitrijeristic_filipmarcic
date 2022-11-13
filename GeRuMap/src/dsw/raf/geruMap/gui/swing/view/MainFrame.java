package dsw.raf.geruMap.gui.swing.view;

import dsw.raf.geruMap.AppCore;
import dsw.raf.geruMap.MapRepository.MapRepositoryImpl;
import dsw.raf.geruMap.gui.swing.controller.ActionManager;
import dsw.raf.geruMap.gui.swing.tree.MapTree;
import dsw.raf.geruMap.gui.swing.tree.MapTreeImplementation;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
@Getter
@Setter
public class MainFrame extends JFrame
{
    private static MainFrame instance = null;
    private ActionManager act_man;
    private JMenuBar menu;
    private JToolBar tbar;
    private MapTree mapTree;
    private TabbedPane desktop;
    private TabbedPane desktop2;
    private MainFrame(){}

    public static MainFrame getInstance()
    {
        if (instance == null)
        {
            instance = new MainFrame();
            instance.initialise();
        }

        return instance;
    }
    private void initialise()
    {
        act_man = new ActionManager();
        mapTree = new MapTreeImplementation();
        initialiseGUI();
    }

    private void initialiseGUI()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension scr_size = kit.getScreenSize();
        int scr_height = scr_size.height;
        int scr_width = scr_size.width;
        setSize(scr_width/2, scr_height/2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GeruMap");

        menu = new MenuBar();
        setJMenuBar(menu);

        tbar = new Toolbar();
        add(tbar,BorderLayout.NORTH);


        desktop = new TabbedPane();
        ((MapRepositoryImpl)AppCore.getInstance().getRep()).subscribe(desktop);

        JTree tree = mapTree.generateTree(AppCore.getInstance().getRep().getProjectExplorer());

        JScrollPane sc_pane = new JScrollPane(tree);
        sc_pane.setMinimumSize(new Dimension(200,150));

        JSplitPane sp_pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,sc_pane,desktop);
        getContentPane().add(sp_pane,BorderLayout.CENTER);

        sp_pane.setDividerLocation(250);
        sp_pane.setOneTouchExpandable(true);
    }
}
