import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
interface Check{
    int adminCheck();
}

class UserDataCheck {
    private String Admin1 = "rishabh";
    private String Pass1 = "rishabh123";
    private String Admin2 = "sulabh";
    private String Pass2 = "sulabh123";

    public int adminCheck(String userName, String userPass) {
        int f = 0;
        if ((userName.equals(Admin1) && userPass.equals(Pass1)) || (userName.equals(Admin2) && userPass.equals(Pass2))) {
            f = 1;
        }
        return f;
    }
}

public class Login extends UserDataCheck{
    public Login(String whoUser) throws Exception{
        Scanner sc = new Scanner(System.in);
        Console console = System.console();
        System.out.println("Welcome To Quizzer");
        System.out.println("Enter Username");
        String userName = sc.nextLine();
        System.out.println("Enter Password");
        char[] passwordChars = console.readPassword();
        String userPass = new String(passwordChars);

        File file = new File("studentDetails.txt");
        ArrayList<AdminCenter> students = new ArrayList<AdminCenter>();
        ObjectInputStream ois = null;
        ListIterator<AdminCenter> li = null;

        if(whoUser.equals("Student")){
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
                students = (ArrayList<AdminCenter>) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            String searchUsername = userName;
            String searchPass = userPass;
            boolean found = false;

            li = students.listIterator();
            while (li.hasNext()) {
                AdminCenter studentDetail = li.next();
                if (studentDetail.stuUsername.equals(searchUsername) && studentDetail.stuPass.equals(searchPass)) {
                    System.out.println("Username and Password are Correct");
                    new QuizTaking(searchUsername);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Username or Password is Incorrect");
            }
        }else{
            int f= adminCheck(userName,userPass);
            if(f==0){
                System.out.println("Username or Password is Incorrect");
            }else{
                new AdminCenterDemo();
            }
        }
    }
}
