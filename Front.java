import java.util.Scanner;

public class Front  {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Quizzer");
        System.out.println("STUDENT enter s , ADMIN enter a");
        char whoUser = sc.nextLine().charAt(0);

        if(whoUser=='s'){
            new Login("Student");
        }else if(whoUser == 'a'){
            new Login("Admin");
        }else{
            System.out.println("This option does not exists");
        }
    }
}
