import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Артем on 18.01.2017.
 */
public class DictionaryOfThisLesson {
    private Set<Word> words = null;
    private Statement statement = null;

    DictionaryOfThisLesson(Statement statement){
        words = new HashSet<Word>();
        this.statement = statement;
    }

    public Set<Word> fillSetForThisLesson(int amount){
        int c = 1;
        while (c <= amount) {
            boolean a =  words.add(getRandomWordFromDB());
            if (a){
                c++;
            }

        }
        return words;
    }

    public Word getRandomWordFromDB() {
        ResultSet resultSet;
        ResultSet setOfOneWord;
        int rowCount;
        int rowNum;
        String rusW = null;
        String engW = null;
        try {
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM words;");
            resultSet.next();
            rowCount = resultSet.getInt(1);
            rowNum = (int)Math.round((rowCount - 1) * Math.random());
            setOfOneWord = statement.executeQuery("SELECT * FROM words LIMIT " + rowNum + ", 1;");
            setOfOneWord.next();
            rusW = setOfOneWord.getString("word_russian");
            engW = setOfOneWord.getString("word_english");
        }catch (SQLException e){
            System.out.println("problem");
        }
        return new Word(rusW, engW);
    }

    @Override
    public String toString(){
        return words.toString();
    }

    public Statement getStatement() {
        return statement;
    }
    
}