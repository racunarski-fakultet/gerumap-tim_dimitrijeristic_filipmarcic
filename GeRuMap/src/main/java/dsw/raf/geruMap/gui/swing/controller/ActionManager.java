package dsw.raf.geruMap.gui.swing.controller;

import dsw.raf.geruMap.gui.swing.controller.StateActions.*;
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
    private AddStateAction addstate_act;
    private DeleteStateAction deletestate_act;
    private LinkStateAction linkstate_act;
    private MoveStateAction movestate_act;
    private SelectionStateAction selectionstate_act;
    private StyleAction style_act;
    private ZoomOutAction zoomOut_act;
    private ZoomInAction zoomIn_act;
    private NavigateStateAction navigation_act;
    private ZoomStateAction zoomstate_act;
    private SaveAction save_act;
    private LoadAction load_act;
    private SaveAsTemplateAction savetemplate_act;
    private LoadTemplate loadtemplate_act;
    private SaveImageAction saveimage_act;
    private CentralTAction central_act;
    private UndoAction undo_act;
    private RedoAction redo_act;

    public ActionManager() {init_actions();}

    private void init_actions()
    {
        exit_act = new ExitAction();
        newpr_act = new NewProjectAction();
        inf_act = new InfoAction();
        rename_act = new RenameAction();
        autor_act = new AuthorAction();
        delete_act = new DeleteAction();
        addstate_act = new AddStateAction();
        deletestate_act = new DeleteStateAction();
        linkstate_act = new LinkStateAction();
        movestate_act = new MoveStateAction();
        selectionstate_act = new SelectionStateAction();
        navigation_act = new NavigateStateAction();
        zoomstate_act = new ZoomStateAction();
        style_act = new StyleAction();
        zoomIn_act = new ZoomInAction();
        zoomOut_act = new ZoomOutAction();
        save_act = new SaveAction();
        load_act = new LoadAction();
        savetemplate_act = new SaveAsTemplateAction();
        loadtemplate_act = new LoadTemplate();
        saveimage_act = new SaveImageAction();
        central_act = new CentralTAction();
        undo_act = new UndoAction();
        redo_act = new RedoAction();

    }
}
