import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Артем on 18.01.2017.
 */
public class DBEditor {
    private Statement statement = null;
    private final String queryForAdd = "INSERT INTO words(word_russian, word_english) VALUES";
    private final String queryForDelete = "DELETE FROM words WHERE ";
    private String engWord = null;
    private String rusWord = null;
    private BufferedReader br = null;

    DBEditor(Statement statement){
        this.statement = statement;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void addWord() {
        try {
            System.out.println("Addition of the new word to database.");
            do {
                System.out.println("Enter word in russian");
                rusWord = br.readLine();
            }while (belongingToLanguage(rusWord).equals("english"));
            do {
                System.out.println("Enter word in english");
                engWord = br.readLine();
            }while (belongingToLanguage(engWord).equals("russian"));

            if (statement.executeUpdate(queryForAdd + "('" + rusWord + "','" + engWord +"');") != 0){
                System.out.println("The word was added: " + rusWord + " - " + engWord);
                return;
            }
            System.out.print("The word wasn't added: " + rusWord + " - " + engWord);
        }
        catch (IOException e){
            System.out.println("Problem with inputting words");
        }
        catch (SQLException e){
            System.out.println("Problem with the addition of the words to database.");
            System.out.println("The word wasn't added.");
        }
    }

    public void deleteWord() {
        System.out.println("Enter word to deletion.");
        String wordToDel = null;
        try {
            String word = br.readLine();
            wordToDel = transformationWord(word);
        } catch (IOException e) {
            System.out.println("Problem with input.(del)");
        }
        if (belongingToLanguage(wordToDel).equals("english")){
            try {
                if (statement.executeUpdate(queryForDelete + "word_english = " + "'" + wordToDel + "'") != 0) {
                    System.out.print(wordToDel + " was deleted.");
                    return;
                }
            }catch (SQLException e){
                System.out.println("Deletion problem (eng)");
            }
        }
        if (belongingToLanguage(wordToDel).equals("russian")){
            try {
                if (statement.executeUpdate(queryForDelete + "word_russian = " + "'" + wordToDel + "'") != 0){
                    System.out.print(wordToDel + " was deleted.");
                    return;
                }
            }catch (SQLException e){
                System.out.println("Deletion problem (rus)");
            }
        }
        System.out.println("Nonexistent word.");
    }

    public String belongingToLanguage(String wordToInspection){


        String word = wordToInspection.toLowerCase();
        int cEng = 0;
        int cRus = 0;
        for (int i = 0; i < word.length(); i++){
            if ((word.charAt(i) >= 97 && word.charAt(i) <= 122) || word.charAt(i) == 45)
                cEng++;
            if ((word.charAt(i) >= 1072 && word.charAt(i) <= 1103) || word.charAt(i) == 45)
                cRus++;
        }

        if (cEng == word.length())
            return "english";
        if (cRus == word.length())
            return "russian";
        return "Word does not exist";
    }

    public String transformationWord(String s){
        String a = s.trim();
        while(a.contains("  ")) {
            String repl = a.replace("  ", " ");
            a=repl;
        }
        return a;
    }
}
