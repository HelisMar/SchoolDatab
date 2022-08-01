import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static int maxQuestion = 0;
   
    public static void main(String[] args) throws Exception {
      // db = new Database();
      //  db.Test();
      Teacher teacher = new Teacher();

        teacher.Login();//Peab küsima kasutaja nime ja ütlema, et logisid sisse
        if(teacher.isLoggedIn) {  //System.out.println(String.format("%s(%s) - Küsimus?", teacher.name, teacher.isAdmin));
        Actions(teacher);
        /*PrintNewQuestion(teacher, "Sisesta valik: ");
        int n = Integer.parseInt(System.console().readLine());
        if(n <= 6){
          System.out.println("Valisid küsimuse = " + n);
        }else {
          System.out.println("Sellist valikut ei ole");
        }*/
        
        } 
    }
    public static void Actions(Teacher Teacher) throws SQLException {
      while(true) {
        PrintOptions(Teacher.isAdmin);
        int input = CheckInt(Teacher);
        if (input == 0){
            break;
          }
        if (input >= 1 && input <= maxQuestion) {  
          switch(input) {
            case 1:
              System.out.println(input);
              break;
            case 2:
            System.out.println(input);
              break;
            case 3:
            Teacher.ActionAddStudent();
              break;
            case 4:
            System.out.println(input);
             break;
            case 5:
            Teacher.ActionAddSubject();
              break;
            case 6:
            System.out.println(input);
              break;
            default:
              System.out.println("Ei olnud õige valik!");
              break;
            }  
          }
        }
      }
    public static int CheckInt(Teacher teacher) {  // kontrollib, et kui kasutaja sisestab vale andmed, et ei annaks errorit
        try {
          PrintNewQuestion(teacher, "Sisesta valik: ");
          return Integer.parseInt(System.console().readLine());
         } catch (Exception e) {
              return 0;
          }
        }
    public static void PrintOptions(boolean isAdmin) {
        HashMap <String, String> optionsAdmin = new HashMap<>();
        optionsAdmin.put("1", "Vaata õpilasi keda õpetad");
        optionsAdmin.put("2", "Vaata aineid mida õpetad");
        optionsAdmin.put("3", "Lisa õpilasi");
        optionsAdmin.put("4", "Kustuta õpilasi");
        optionsAdmin.put("5", "Lisa aineid");
        optionsAdmin.put("6", "Kustuta aineid");

        HashMap <String, String> options = new HashMap<>();
        options.put("1", "Vaata õpilasi keda õpetad");
        options.put("2", "Vaata aineid mida õpetad");

        if(isAdmin) {
          maxQuestion = optionsAdmin.size();
            for(Map.Entry<String, String> entry: optionsAdmin.entrySet()){
                System.out.println(String.format("%s. %s", entry.getKey(), entry.getValue()));}
        }else{
          maxQuestion = options.size();
            for(Map.Entry<String, String> entry: options.entrySet()){
            System.out.println(String.format("%s. %s", entry.getKey(), entry.getValue()));
        }      
    }  
  }

  public static void PrintNewQuestion(Teacher teacher, String question) {
    /*if(teacher.isAdmin) {
        System.out.print(String.format("%s%s- %s", teacher.name, " (Admin) ", question));
    } else {
        System.out.print(String.format("%s - %s", teacher.name, question));
    }*/

    //                                                               Loogiline tehe  ?    TRUE    : FALSE
    System.out.print(String.format("%s%s- %s", teacher.name, teacher.isAdmin ? " (Admin) " : " ", question));
} 
}

