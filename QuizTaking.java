import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class QuizTaking {
    public QuizTaking(String userName) throws Exception {
        Scanner sc = new Scanner(System.in);

        File file = new File("questions.txt");
        ArrayList<Question> al = new ArrayList<Question>();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ListIterator<Question> li = null;
        int Score = 0;

        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            al = (ArrayList<Question>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Enter GO to start quiz or enter any thing to escape");
        String go = sc.nextLine();
        if(go.equals("GO")){
            li = al.listIterator();
            while (li.hasNext()) {
                Question question = li.next();
                System.out.println(question.qstatement);
                System.out.println("a. " + question.opta);
                System.out.println("b. " + question.optb);
                System.out.println("c. " + question.optc);
                System.out.println("d. " + question.optd);
                System.out.println("Enter the option");
                char answer = sc.nextLine().charAt(0);
                if (question.correctopt == answer) {
                    Score += 10; // Increment the score by 10 for each correct answer
                    
                }
                System.out.println("");
            }
            System.out.println("Your Score: " + Score);
            new UpdateMarks(Score,userName);
        }else{

        }
    }
}

