package figures;

public class StateMachine implements Cloneable{
	private State currentState;

	public void Initialize(State startingState)
    {
        currentState = startingState;
        currentState.Enter();
    }
	
    public void ChangeState(State newState)
    {
        if (newState == null) return;
        
        if(newState == currentState && !(currentState instanceof CreateState))return;
        currentState.Exit();
        currentState = newState;
        currentState.Enter();
    }

	public State getCurrentState() {
		return currentState;
	}
}
