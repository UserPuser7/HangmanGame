public class Player {
    private int mistakes;  // Количество ошибок

    public Player() {
        this.mistakes = 0;
    }

    public void makeMistake() {
        mistakes++;
    }

    public int getMistakes() {
        return mistakes;
    }
}