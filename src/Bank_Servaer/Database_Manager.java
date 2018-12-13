package Bank_Servaer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Techno Service on 5/24/2018.
 */
public class Database_Manager {
    private Connection db_connection;
    public void connect_to_database() throws SQLException {
        db_connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_account","root","0021979677");
    }
    public void insert(String fname,String lname,String cardnumber ,String accountnumber ,float mojodi ,String password) throws SQLException {
        Statement statement = db_connection.createStatement();
        statement.execute("insert into account values ('"+fname+"','"+lname+"','"+cardnumber+"','"+accountnumber+"',"+mojodi+",'"+password+"')");
    }
    public void update(String cardnumber) throws SQLException {
        Statement statement = db_connection.createStatement();
        statement.execute("UPDATE account SET access=FALSE WHERE cardnumber='"+cardnumber+"'");
    }
    public void delete(int accountnumber) throws SQLException {
        Statement statement = db_connection.createStatement();
        statement.execute("DELETE FROM downloads WHERE accountnumber="+accountnumber+"");
    }
    public ArrayList<Account> account() throws SQLException {
        ArrayList<Account> accounts = new ArrayList<>();
        Statement statement = db_connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from account");
        if(resultSet.first()){
            do{
                Account account = new Account(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getFloat(5),
                        resultSet.getString(6),
                        resultSet.getBoolean(7)

                );
                accounts.add(account);
            }while (resultSet.next());
        }
        return accounts;
    }
    public String tranfering_to(String cardnumber) throws SQLException {
        String person = null;
        Statement statement = db_connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from account WHERE cardnumber = '"+cardnumber+"'");
        person = resultSet.getString(0)+"  ";
        person = person + resultSet.getString(1);
        System.out.println("to : "+person);
        return person;
    }
    public void updatemoney(String cardnumber,float money) throws SQLException {
        Statement statement = db_connection.createStatement();
        statement.execute("UPDATE account SET mijodi= (SELECT mijodi WHERE cardnumber='"+cardnumber+"' )+"+money+" WHERE cardnumber='"+cardnumber+"'");
    }
}
