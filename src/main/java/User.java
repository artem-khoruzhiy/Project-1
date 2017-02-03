import java.util.Scanner;

/**
 * Created by Артем on 18.01.2017.
 */
public class User {
    private String USER;
    private String PASSWORD;

    User(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        USER = scanner.nextLine();
        System.out.println("Enter your password:");
        PASSWORD = scanner.nextLine();
    }

    public String getUSER() {
        return USER;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}
