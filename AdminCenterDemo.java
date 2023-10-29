
import java.util.*;
import java.io.*;

class AdminCenter implements Serializable {
    static int studentCounter = 1; // Static counter for student numbers
    int sno;
    int rollno;
    String studentName;
    String stuUsername;
    String stuPass;
    int marks;

    AdminCenter(int rollno,String studentName, String stuUsername, String stuPass, int marks) {
        this.sno = studentCounter++;
        this.rollno = rollno;
        this.studentName = studentName;
        this.stuUsername = stuUsername;
        this.stuPass = stuPass;
        this.marks = marks;
    }

    public String toString() {
        return sno + " " + studentName + " " + rollno + " " + stuUsername + " " + stuPass + " " + marks;
    }
}

public class AdminCenterDemo {
    public AdminCenterDemo() throws Exception {
        int choice = -1;
        Scanner s = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        File file = new File("studentDetails.txt");
        ArrayList<AdminCenter> students = new ArrayList<AdminCenter>();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ListIterator<AdminCenter> li = null;

        if (file.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            students = (ArrayList<AdminCenter>) ois.readObject();
            if (!students.isEmpty()) {
                AdminCenter.studentCounter = students.get(students.size() - 1).sno + 1;
            }
            ois.close();
        }

        do {
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * ");
            System.out.println("THIS IS ADMIN CENTER");
            System.out.println("1. Add New Student");
            System.out.println("2. All Student Details");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student");
            System.out.println("6. Edit Quiz");
            System.out.println("0. Exit");
            System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * ");
            System.out.println("Enter your choice");
            choice = s.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter how many Students you want to add: ");
                    int n = s.nextInt();

                    for (int i = 0; i < n; i++) {
                    System.out.println("Enter Student Roll no: ");
                    int rollno = s.nextInt();

                    System.out.println("Enter Student Name: ");
                    String studentname = sc.nextLine();

                    System.out.println("Enter UserName for Student: ");
                    String stuUsername = sc.nextLine();

                    System.out.println("Set Students Password: ");
                    String stuPass = sc.nextLine();

                    System.out.println("Set Students Marks: ");
                    int marks = s.nextInt();

                    students.add(new AdminCenter(rollno, studentname, stuUsername, stuPass, marks));
                    }
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(students);
                    oos.close();
                    break;
                case 2: {
                    li = students.listIterator();
                    System.out.println("------------------------------------------");
                    while (li.hasNext()) {
                        AdminCenter studentDetail = li.next();
                        System.out.println(studentDetail.rollno+" | "+studentDetail.studentName+" | "+studentDetail.marks+" | "+ studentDetail.stuUsername+" | Password: "+studentDetail.stuPass);
                    }
                    System.out.println("------------------------------------------");
                    break;
                }
                case 3: {
                    System.out.println("Enter Student Roll to Search");
                    int search = s.nextInt(); // Read the roll number as an integer
                    boolean found = false;
                
                    li = students.listIterator();
                    while (li.hasNext()) {
                        AdminCenter studentDetail = li.next();
                        if (studentDetail.rollno == search) { // Compare roll numbers
                            System.out.println("Student Name: " + studentDetail.stuUsername);
                            System.out.println("Marks: " + studentDetail.marks);
                            found = true;
                            break;
                        }
                    }
                
                    if (!found) {
                        System.out.println("Student not found.");
                    }
                
                    break;
                }
                
                case 4: {
                    System.out.println("Enter Student Roll no. to Delete");
                    int deleteno = s.nextInt();
                    boolean deleted = false;

                    li = students.listIterator();
                    while (li.hasNext()) {
                        AdminCenter studentDetail = li.next();
                        if (studentDetail.rollno == deleteno) {
                            li.remove();
                            deleted = true;
                            break;
                        }
                    }

                    if (deleted) {
                        System.out.println("Student deleted.");
                    } else {
                        System.out.println("Student not found, so cannot be deleted.");
                    }

                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(students);
                    oos.close();

                    break;
                }
                case 5: {
                    System.out.println("Enter Student Roll no. to Update");
                    int updateno = s.nextInt();
                    boolean updated = false;

                    li = students.listIterator();
                    while (li.hasNext()) {
                        AdminCenter studentDetail = li.next();
                        if (studentDetail.rollno == updateno) {
                            System.out.println("Enter updated Marks: ");
                            int updatedMarks = s.nextInt();

                            studentDetail.marks = updatedMarks;
                            updated = true;
                            break;
                        }
                    }

                    if (updated) {
                        System.out.println("Marks updated.");
                        oos = new ObjectOutputStream(new FileOutputStream(file));
                        oos.writeObject(students);
                        oos.close();
                    } else {
                        System.out.println("Student not found, so cannot be updated.");
                    }

                    break;
                }
                case 6:{
                    new QuestionDemo();
                }
            }
        } while (choice != 0);
    }
}
