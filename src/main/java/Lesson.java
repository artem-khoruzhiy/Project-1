import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Артем on 22.01.2017.
 */
public class Lesson {
    DictionaryOfThisLesson dict = null;
    Scanner scanner = new Scanner(System.in);
    private Set<Word> words = null;
    private int amount;
    Lesson(){
        dict = new DictionaryOfThisLesson(new ConnToDB(new User()).getStatement());
        do {
            System.out.println("Enter amount of words.");
            amount = Integer.parseInt(scanner.next());
        }while (!amountWordsInDB(amount));
    }
    
    public String inspectionOfLesson(){
        words = dict.fillSetForThisLesson(amount);
        Iterator it = words.iterator();
        int amTrue = 0, amFalse = 0;
        while (it.hasNext()){
            Word w = (Word)it.next();
            int a = (int)Math.round(Math.random() + 1);
            if (a == 1){
                System.out.print(w.getWordEng() + " - ");
                if (w.getWordRus().equals(scanner.next())){
                    amTrue++;
                }else {
                    amFalse++;
                }
                continue;
            }
            if (a == 2){
                System.out.print(w.getWordRus() + " - ");
                if (w.getWordEng().equals(scanner.next())){
                    amTrue++;
                }else {
                    amFalse++;
                }
                continue;
            }
        }
        return "true: " + amTrue + ", false: " + amFalse;
    }

    public boolean amountWordsInDB(int amount){
        ResultSet rs = null;
        int countRows = 0;
        try {
            rs = dict.getStatement().executeQuery("SELECT COUNT(*) FROM words;");
            rs.next();
            countRows = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Problem.");
        }
        if (countRows >= amount)
            return true;
        else return false;
    }
}
