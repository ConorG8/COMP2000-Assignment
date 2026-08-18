
public class Cell {
    private String id;
    private CharacterState state;

    public Cell(String id, CharacterState initialState){
        this.id = id;
        this.state = initialState;
    }

    public String getId() { return id; }
    public CharacterState getState() { return state; }

    public void changeState(CharacterState newState){
        this.state = newState;
    }

    public int onCollision(Cell opponent){
        return this.state.checkMatchup(opponent.getState());
    }
}
