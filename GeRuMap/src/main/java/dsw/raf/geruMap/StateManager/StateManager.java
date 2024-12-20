package dsw.raf.geruMap.StateManager;

import dsw.raf.geruMap.MapRepository.Implementation.MindMap;
import dsw.raf.geruMap.StateManager.States.*;
import dsw.raf.geruMap.gui.swing.view.MainFrame;
import dsw.raf.geruMap.gui.swing.view.MapView;


public class StateManager {
    private AbstractState currentState;
    private ZoomState zoomState;
    private LinkState linkState;
    private SelectionState selectionState;
    private AddState addState;
    private DeleteState deleteState;
    private MoveState moveState;
    private NavigateState navigateState;

    private void initStates()
    {
        addState = new AddState();
        zoomState = new ZoomState();
        linkState = new LinkState();
        selectionState = new SelectionState();
        deleteState = new DeleteState();
        moveState = new MoveState();
        navigateState = new NavigateState();
        currentState = selectionState;
    }
    public StateManager(){initStates();}

    public AbstractState getCurrentState()
    {
        return currentState;
    }

    public void setLinkState() {
        MindMap myMap = (MindMap) ((MapView) MainFrame.getInstance().getDesktop().getSelectedComponent()).getMyMap();
        myMap.reset_g();
        this.currentState = linkState;
    }

    public void setSelectionState() {
        this.currentState = selectionState;
    }

    public void setAddState() {
        this.currentState = addState;
    }

    public void setDeleteState(){this.currentState = deleteState;}

    public void setMoveState(){this.currentState = moveState;}

    public void setNavigateState(){this.currentState = navigateState;}

    public void setZoomState(){this.currentState = zoomState;}
}
