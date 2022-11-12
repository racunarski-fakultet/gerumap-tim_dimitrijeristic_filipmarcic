package dsw.raf.geruMap.gui.swing.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionManager
{
    private ExitAction exit_act;
    private NewProjectAction newpr_act;
    private InfoAction inf_act;
    private RenameAction rename_act;

    public ActionManager() {init_actions();}

    private void init_actions()
    {
        exit_act = new ExitAction();
        newpr_act = new NewProjectAction();
        inf_act = new InfoAction();
        rename_act = new RenameAction();
    }
}
