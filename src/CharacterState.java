public interface CharacterState {
    String getType();
    int checkMatchup(CharacterState opponent);
    String getActionPhrase();
}
