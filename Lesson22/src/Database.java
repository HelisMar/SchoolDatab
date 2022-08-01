import java.sql.*;


public class Database {

        private String server = "jdbc:mysql://localhost:3306";
        private String username = "Java1";
        private String password = "123456";
        private Statement stmt = null;
        private Connection con = null;
        private String sql = ""; //See on tavaline string mille saadame päringuga andmebaasi

        public Database() throws Exception { //throw exception tähendab et võib visata vea
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(server, username, password);
            this.stmt = con.createStatement();
}

//funktsoon, et saada kätte õpilaste nimed.
public void Test() throws SQLException {
    sql = String.format("SELECT name FROM school.students" );
    ResultSet result = stmt.executeQuery(sql);
    while(result.next())
    System.out.println(String.format("Õpilane: %s", result.getString("Name")));
   
}
public boolean[] TeacherLogin(String name) throws SQLException {
    boolean[] output = new boolean[2]; //vaatame kas sisselogimine on true v false
    sql = "SELECT * FROM school.teachers WHERE Name = '" + name + "'";
    ResultSet results = stmt.executeQuery(sql);
    while(results.next()) {
        output[0] = true;
        output[1] = results.getBoolean("Admin");
    }
    return output;
 }

public boolean CheckAdmin(String name) throws SQLException {
    boolean isAdmin = false;
    sql = "SELECT Admin FROM school.teachers WHERE Name = '" + name + "'";
    ResultSet results = stmt.executeQuery(sql); //saan vastuseks mingi rea
    while(results.next()) {  // kas on olemas mingid read?
        isAdmin = results.getBoolean("Admin"); //kui on olemas, siis on true
    }
    return isAdmin; //tagastame successi
}
public void AddSubject(String subjectName, int teacherID) throws SQLException {
        sql = "INSERT INTO school.subjects ( `Subject`, `Teacher`) VALUES ('" + subjectName + "', " + teacherID + ");"; 
        stmt.executeUpdate(sql);
    }
   
public int GetteacherByName(String name) throws SQLException {
    sql = "SELECT ID FROM school.teachers WHERE Name = '" + name + "'";
    ResultSet results = stmt.executeQuery(sql);
    while(results.next()) {
        return results.getInt("ID");
    }
    return -1;
}

//Kontroll, et kas on subject olemas. 
public boolean CheckSubject(String subjectName) throws SQLException {
    boolean found = false;
    sql = "SELECT Subject FROM school.subjects WHERE Subject = '"+ subjectName + "'";
    ResultSet results = stmt.executeQuery(sql);
    while(results.next()) {
        found = true;
    }
    return found;
}
public void AddStudent(String studentName, int studentClass, String sandm, String extra) throws SQLException {
    sql = "INSERT INTO school.students (Name, Class, Subject, Info) VALUES ('" + studentName +"', " + studentClass +",'" + sandm + "', '" + extra + "');";
    ResultSet results = stmt.executeQuery(sql);
}
}