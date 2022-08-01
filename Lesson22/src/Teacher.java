import java.sql.SQLException;

public class Teacher {
    static Database db = null;
    public String name = "";
    public boolean isAdmin = false;
    public boolean isLoggedIn = false;

    public Teacher() throws Exception {
        db = new Database();
    }
    
    public void Login() throws SQLException {
        // Kui sisestas valesti, küsib uuesti. Kui õpetaja sisestas "" siis läheb välja
        while(true) {
            System.out.print("Sisesta nimi: ");
            name = System.console().readLine(); 
            if(name.equals(""))  //sobib ka lenght == 0
            break; 
            boolean[] teacherCheck = db.TeacherLogin(name);
            if(teacherCheck[0]) { // Tagastab TRUE kui õpetaja oli leitud, FALSE kui ei leidnud
                System.out.println("Oled Sisseloginud!");
                isLoggedIn = true;
                isAdmin = teacherCheck[1];
                break;
            } else {
                System.out.println("Ei saanud sisselogida!");
            }
        }
    }
    public void ActionAddSubject() throws SQLException {
        String subject = "";
        int teacherID = -1;
        while(true) {
            System.out.print("Aine nimi: ");
            subject = System.console().readLine();
            if(!db.CheckSubject(subject)){
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
        String sandm = "";
        String extra = "";

        System.out.println("Sisesta õpilase nimi: ");
        studentName = System.console().readLine(); // Teha unikaalsuse kontroll! (sama mis subject)
        System.out.println("Sisesta extra: ");
        extra = System.console().readLine();
        System.out.println("Sisesta õpilase Klass:");
        studentClass = Integer.parseInt(System.console().readLine()); // Teha piirang 1-9.klass
        


        db.AddStudent(studentName, studentClass, sandm, extra);

    }
}