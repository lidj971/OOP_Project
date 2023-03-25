package figures;

public class StateMachine {
	private State currentState;

	public void Initialize(State startingState)
    {
        currentState = startingState;
        currentState.Enter();
    }
	
    public void ChangeState(State newState)
    {
        if (newState == null) return;
        currentState.Exit();
        currentState = newState;
        currentState.Enter();
    }

	public State getCurrentState() {
		return currentState;
	}
}
