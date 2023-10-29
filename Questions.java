import java.util.*;
import java.io.*;

class Question implements Serializable {
    static int questionCounter = 1; // Static counter for question numbers
    int qno;
    String qstatement;
    String opta;
    String optb;
    String optc;
    String optd;
    char correctopt;

    Question(String qstatement, String opta, String optb, String optc, String optd, char correctopt) {
        this.qno = questionCounter++;
        this.qstatement = qstatement;
        this.opta = opta;
        this.optb = optb;
        this.optc = optc;
        this.optd = optd;
        this.correctopt = correctopt;
    }

    public String toString() {
        return qno + " " + qstatement + " " + opta + " " + optb + " " + optc + " " + optd + " " + correctopt;
    }
}

class QuestionDemo {
    public QuestionDemo() throws Exception {
        int choice = -1;
        Scanner s = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        File file = new File("questions.txt");
        ArrayList<Question> al = new ArrayList<Question>();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ListIterator li = null;

        if (file.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            al = (ArrayList<Question>) ois.readObject();
            if (!al.isEmpty()) {
                Question.questionCounter = al.get(al.size() - 1).qno + 1;
            }
            ois.close();
        }

        do {
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * ");
            System.out.println("EDIT QUIZ SECTION");
            System.out.println("1. Add Question");
            System.out.println("2. Display all Questions");
            System.out.println("3. Search Question");
            System.out.println("4. Delete Question");
            System.out.println("5. Update Question");
            System.out.println("0. Exit");
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * ");
            System.out.println("Enter your choice");
            choice = s.nextInt();

            switch (choice) {
                case 1:
                System.out.println("- - - - - - - - - - - - - - - - - -");
                    System.out.println("Enter how many Questions you want : ");
                    int n = s.nextInt();
                    for (int i = 0; i < n; i++) {
                        System.out.println("Enter Question: ");
                        String qstatement = sc.nextLine();

                        System.out.println("Enter Option a: ");
                        String opta = sc.nextLine();

                        System.out.println("Enter Option b: ");
                        String optb = sc.nextLine();

                        System.out.println("Enter Option c: ");
                        String optc = sc.nextLine();

                        System.out.println("Enter Option d: ");
                        String optd = sc.nextLine();

                        System.out.println("Enter Correct Option: ");
                        char correctopt = sc.nextLine().charAt(0);

                        al.add(new Question(qstatement, opta, optb, optc, optd, correctopt));
                    }
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(al);
                    oos.close();
                    break;
                case 2: {
                    System.out.println("- - - - - - - - - - - - - - - - - -");
                    li = al.listIterator();
                    while (li.hasNext()) {
                        Question question = (Question) li.next();
                        System.out.println(question.qno+" "+question.qstatement);
                    }
                    break;
                }
                case 3: {
                    System.out.println("- - - - - - - - - - - - - - - - - -");
                    System.out.println("Enter Question no. to Search");
                    int searchQno = s.nextInt();
                    boolean found = false;

                    li = al.listIterator();
                    while (li.hasNext()) {
                        Question question = (Question) li.next();
                        if (question.qno == searchQno) {
                            System.out.println("Question No: " + question.qno);
                            System.out.println("Question: " + question.qstatement);
                            System.out.println("Option a: " + question.opta);
                            System.out.println("Option b: " + question.optb);
                            System.out.println("Option c: " + question.optc);
                            System.out.println("Option d: " + question.optd);
                            System.out.println("Correct Option: " + question.correctopt);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Question not found.");
                    }

                    break;
                }
                case 4:{
                    System.out.println("- - - - - - - - - - - - - - - - - -");
                    System.out.println("Enter Question no. to Delete");
                    int deleteQno = s.nextInt();
                    boolean deleted = false;

                    li = al.listIterator();
                    while (li.hasNext()) {
                        Question question = (Question) li.next();
                        if (question.qno == deleteQno) {
                            li.remove();
                            deleted = true;
                            break;
                        }
                    }

                    if (deleted) {
                        System.out.println("Question deleted.");
                        // Recalculate the questionCounter based on the remaining questions
                        Question.questionCounter = 1; // Reset to 1
                        for (Question question : al) {
                            question.qno = Question.questionCounter++;
                        }
                        // Save the updated ArrayList to the file
                        oos = new ObjectOutputStream(new FileOutputStream(file));
                        oos.writeObject(al);
                        oos.close();
                    } else {
                        System.out.println("Question not found, so cannot be deleted.");
                    }

                    break;
                }
                case 5: {
                    System.out.println("- - - - - - - - - - - - - - - - - -");
                    System.out.println("Enter Question no. to Update");
                    int updateQno = s.nextInt();
                    boolean updated = false;

                    li = al.listIterator();
                    while (li.hasNext()) {
                        Question question = (Question) li.next();
                        if (question.qno == updateQno) {
                            System.out.println("Enter updated Question: ");
                            String updatedStatement = sc.nextLine();
                            System.out.println("Enter updated Option a: ");
                            String updatedOptA = sc.nextLine();
                            System.out.println("Enter updated Option b: ");
                            String updatedOptB = sc.nextLine();
                            System.out.println("Enter updated Option c: ");
                            String updatedOptC = sc.nextLine();
                            System.out.println("Enter updated Option d: ");
                            String updatedOptD = sc.nextLine();
                            System.out.println("Enter updated Correct Option: ");
                            char updatedCorrectOpt = sc.nextLine().charAt(0);

                            question.qstatement = updatedStatement;
                            question.opta = updatedOptA;
                            question.optb = updatedOptB;
                            question.optc = updatedOptC;
                            question.optd = updatedOptD;
                            question.correctopt = updatedCorrectOpt;

                            updated = true;
                            break;
                        }
                    }

                    if (updated) {
                        System.out.println("Question updated.");
                        oos = new ObjectOutputStream(new FileOutputStream(file));
                        oos.writeObject(al);
                        oos.close();
                    } else {
                        System.out.println("Question not found, so cannot be updated.");
                    }

                    break;
                }
            }
        } while (choice != 0);
    }
}