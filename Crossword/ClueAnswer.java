package Crossword;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ClueAnswer{
    Connection connection;
    PreparedStatement statement;
    ResultSet results;
    String query, query1, query2, query3;
    
    ArrayList<String> qID = new ArrayList<>();
    ArrayList<String> clue = new ArrayList<>();
    ArrayList<String> answer = new ArrayList<>();
    int oneSbj, twoSbj, threeSbj;
    
    ClueAnswer(int number, boolean eng, boolean cs, boolean gk){
        jdbc(number,eng,cs,gk);
    } 
    
  
    
   public void jdbc(int number, boolean eng, boolean cs, boolean gk){
       int n1, n2, n3, n4;
       n1 = number/2;
       n2 = number - n1;
       n3 = number/3;
       n4 = number - n3 - n3;
       
       try {  
            Class.forName("com.mysql.jdbc.Driver"); 
            connection = DriverManager.getConnection("jdbc:mysql://103.47.184.194:5588/Crossword?useSSL=false", "2015-CSE-015", "CSE-015");
            if(eng && cs && gk){
                query1 = "Select* from((Select* from ENGLISH Order by rand() limit "+n3+") UNION (Select* from GENERAL_KNOWLEDGE Order by rand() limit "+n3+") UNION (Select* from COMPUTER_SCIENCE Order by rand() limit "+n4+")) as c order by char_length(Answer) DESC"; 
            }
            else if(eng && cs && !gk){
                query1 = "Select* from((Select* from ENGLISH Order by rand() limit "+n1+") UNION (Select* from COMPUTER_SCIENCE Order by rand() limit "+n2+")) as c order by char_length(Answer) DESC";
            }
            else if(eng && !cs && gk){
                query1 = "Select* from((Select* from ENGLISH Order by rand() limit "+n1+") UNION (Select* from GENERAL_KNOWLEDGE Order by rand() limit "+n2+")) as c order by char_length(Answer) DESC";  
            }
            else if(!eng && cs && gk){
                query1 = "Select* from((Select* from COMPUTER_SCIENCE Order by rand() limit "+n1+") UNION (Select* from GENERAL_KNOWLEDGE Order by rand() limit "+n2+")) as c order by char_length(Answer) DESC";  
            }
            else if(eng && !cs && !gk){
                query1 = "select * from(select * from ENGLISH Order by rand() limit "+number+") as c order by char_length(Answer) DESC";
            }
            else if(!eng && cs && !gk){
                query1 = "select * from(select * from COMPUTER_SCIENCE Order by rand() limit "+number+") as c order by char_length(Answer) DESC";
            }
            else if(!eng && !cs && gk){
                query1 = "select * from(select * from GENERAL_KNOWLEDGE Order by rand() limit "+number+") as c order by char_length(Answer) DESC";
            }
            
            
           
                statement = connection.prepareStatement(query1);    
                results = statement.executeQuery(); 
                
                while (results.next()) {
                    qID.add(results.getString(1));
                    clue.add(results.getString(2));
                    answer.add(results.getString(3));
           
            }
            connection.close();
        }
            catch(Exception e) {
                System.out.println(e);
            }  
    }
    
   public ArrayList<String> getAnsArrayList(){
       return answer;
    }
    
    public ArrayList<String> getClueArrayList(){
        return clue;
    }
}
