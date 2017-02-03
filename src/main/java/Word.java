/**
 * Created by Артем on 18.01.2017.
 */
public class Word {
    private String wordEng;
    private String wordRus;

    Word(String wordRus, String wordEng){
        this.wordRus = wordRus;
        this.wordEng = wordEng;
    }

    public String getWordEng() {
        return wordEng;
    }

    public String getWordRus() {
        return wordRus;
    }


    @Override
    public String toString() {
        return "English word: " + wordEng + "; Russian word: " + wordRus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        if (!wordEng.equals(word.wordEng)) return false;
        return wordRus.equals(word.wordRus);

    }

    @Override
    public int hashCode() {
        int result = wordEng.hashCode();
        result = 31 * result + wordRus.hashCode();
        return result;
    }
}
