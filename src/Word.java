public class Word {
    private final String word;  //слово-загадка
    private final boolean[] guessed;  //массив, отслеживающий отгаданные буквы

    public Word(String word) {
        this.word = word.toLowerCase();
        this.guessed = new boolean[word.length()];
    }

    public boolean guessLetter(char letter) {
        boolean found = false;
        letter = Character.toLowerCase(letter);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                guessed[i] = true;
                found = true;
            }
        }
        return found;
    }

    public boolean isGuessed() {
        for (boolean b : guessed) {
            if (!b) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            sb.append(guessed[i] ? word.charAt(i) : "_");
            sb.append(" ");
        }
        return sb.toString();
    }

    public String getWord() {
        return word;
    }

    public String getMaskedWord() {
        return "*".repeat(word.length());
    }
}