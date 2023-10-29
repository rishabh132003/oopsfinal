import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class UpdateMarks {
    public UpdateMarks(int Score, String userName) throws Exception {
        File file = new File("studentDetails.txt");
        ArrayList<AdminCenter> students = new ArrayList<AdminCenter>();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ListIterator<AdminCenter> li = null;

        // Load the student data from the file
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            students = (ArrayList<AdminCenter>) ois.readObject();
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

        boolean updated = false;
        li = students.listIterator();
        while (li.hasNext()) {
            AdminCenter studentDetail = li.next();
            if (studentDetail.stuUsername.equals(userName)) { // Use equals for string comparison
                int updatedMarks = Score;

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
    }
}
