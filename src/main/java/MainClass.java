/**
 * Created by Артем on 18.01.2017.
 */
public class MainClass {
    public static void main(String[] args) {
//        Lesson lesson = new Lesson();
//        System.out.println(lesson.inspectionOfLesson());

        DBEditor dbEditor = new DBEditor(new ConnToDB(new User()).getStatement());
        dbEditor.deleteWord();

    }
}
