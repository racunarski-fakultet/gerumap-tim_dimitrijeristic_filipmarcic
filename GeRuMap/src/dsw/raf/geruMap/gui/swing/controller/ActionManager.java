package dsw.raf.geruMap.gui.swing.controller;

import lombok.Getter;
import lombok.Setter;

import java.security.PrivateKey;

@Getter
@Setter
public class ActionManager
{
    private ExitAction exit_act;
    private NewProjectAction newpr_act;
    private InfoAction inf_act;
    private RenameAction rename_act;
    private AuthorAction autor_act;
    private DeleteAction delete_act;
    public ActionManager() {init_actions();}

    private void init_actions()
    {
        exit_act = new ExitAction();
        newpr_act = new NewProjectAction();
        inf_act = new InfoAction();
        rename_act = new RenameAction();
        autor_act = new AuthorAction();
        delete_act = new DeleteAction();
    }
}
