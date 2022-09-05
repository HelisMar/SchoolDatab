import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Teacher {
    static Database db = null;
    public String name = "";
    public boolean isAdmin = false;
    public boolean isLoggedIn = false;
    public int teacherID = -1;

    public Teacher() throws Exception {
        db = new Database();
    }
    
    public void Login() throws SQLException {
        // Kui sisestas valesti, küsib uuesti. Kui õpetaja sisestas "" siis läheb välja
        while(true) {
            System.out.print("Sisesta nimi: ");
            name = System.console().readLine(); 
            if(name.equals("")) { //sobib ka lenght == 0
            break; 
        }
        boolean[] teacherCheck = db.TeacherLogin(name);
        if(teacherCheck[0]) { // Tagastab TRUE kui õpetaja oli leitud, FALSE kui ei leidnud
            System.out.println("Oled Sisseloginud!");
            isLoggedIn = true;
            isAdmin = teacherCheck[1];
            teacherID = db.GetTeacherID(name);
            break;
        } else {
                System.out.println("Ei saanud sisselogida!");
            }
        }
    }
    
    public void ActionAddSubject() throws SQLException {
        String subject = "";
        int teacherID = -1;
        while(true) { //küsib nii kaua kuni sisestab aine, mida ei ole andmebaasis
            System.out.print("Aine nimi: ");
            subject = System.console().readLine();
            if(!db.CheckSubject(subject)){  //siin läheb tsüklist välja kui kasutaja sisestas aine, mida ei olnud
                break;
            }
        }
        while(true) {
            System.out.print("Sisesta õpetaja nimi: ");
            String teacherName = System.console().readLine();
            teacherID = db.GetteacherByName(teacherName);
            if(teacherID > 0) {
                break;
            }
        }
        db.AddSubject(subject, teacherID);
    }

    public void ActionAddStudent () throws SQLException{
        String studentName = "";
        int studentClass = -1;
        String sandm = "";  // Aine ja hinnete combo, AineID=AineHinne,AineID=AineHinne,....
        String extra = "";

        System.out.println("Sisesta õpilase nimi: ");
        studentName = System.console().readLine(); // Teha unikaalsuse kontroll! (sama mis subject)
        System.out.println("Sisesta lisainfo: ");
        extra = System.console().readLine();
        System.out.println("Sisesta õpilase Klass:");
        studentClass = Integer.parseInt(System.console().readLine()); // Teha piirang 1-9.klass
        

        List<String> subjects = db.GetAllSubjects();
        List<String> studentSandm = new ArrayList<>();

        while(true) {
            for (String subject : subjects) {
                System.out.println(subject);
            }
           
            String subjectName = "";
            
            while(true) {
                System.out.print("Sisesta aine(exit=e): ");
                subjectName = System.console().readLine(); // Teha kontroll kas sisestatud aine oli õige
                if(subjects.contains(subjectName) || subjectName.equals("e")) {
                    break;
                }
            }

            if(subjectName.equals("e")) {
                break;
            }
            
            int subjectID = db.GetSubjectID(subjectName);
            System.out.print(String.format("Sisesta %s hinne: ", subjectName));
            int mark = Integer.parseInt(System.console().readLine());
            sandm = subjectID + "=" + mark;
            studentSandm.add(sandm);
            subjects.remove(subjectName);
            if(subjects.size() == 0) {
                break;
            }
        }
        db.AddStudent(studentName, studentClass, String.join(",", studentSandm), extra);
    }

    public void ActionShowTeacherSubjects() throws SQLException {
        List<String> subjects = db.GetCurrentTeacherSubjects(teacherID);
        System.out.println("Ained on: ");
        for (String subject : subjects) {
            System.out.println(subject);
        }
    }

    public void ActionDeleteStudent(){
        String studentName = "";
        int studentID = -1;
    }
    
        
}