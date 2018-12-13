package Bank_Servaer;

import com.sun.org.apache.bcel.internal.generic.FLOAD;
import org.json.JSONException;
import org.json.JSONObject;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by AmirHosein on 5/24/2018.
 */
public class CheckingAccount {
    Database_Manager db = new Database_Manager();
    ArrayList<Account> accounts;


    public void checking_cardnumber(String cardnumber) throws LoginException, SQLException {
        db.connect_to_database();
        accounts = db.account();
        for (Account account : accounts) {
            if (account.getCard_number().equals(cardnumber)) {
                System.out.println("Wellcome " + account.getFname());
                if (account.isAccess() == false) {
                    throw new LoginException("your acount is not accessible !!!");
                }
            }
        }
    }

    public void checking_password(String cardnumber, String password) throws LoginException, SQLException, JSONException {
        JSONObject js;
        db.connect_to_database();
        accounts = db.account();
        for (Account account : accounts) {
            if (account.getCard_number().equals(cardnumber)) {
                if (account.isAccess()==true) {
                    if ((account.getPassword().equals(password))) {
                        js = new JSONObject();
                        js.put("message","good");
                        BankServer.serverWriter.println(js);
                        BankServer.serverWriter.flush();

                        String request = BankServer.serverReader.nextLine();
                        json_parser_command jp_login = new json_parser_command();
                        String[] command = jp_login.json_parser(request).split(":");
                        if (command[0].equals("cash")){
                            js = new JSONObject();
                            js.put("message",account.getMojodi()+"");
                            BankServer.serverWriter.println(js);
                            BankServer.serverWriter.flush();
                        }else if(command[0].equals("tranfer")){

                            double money = Double.parseDouble(command[2]);
                            System.out.println("your real money : "+money);
                            if (account.getMojodi()<money){
                                System.out.println("not posible");
                            }else{
                                db.updatemoney(account.getCard_number(), -(float) money);
                                db.updatemoney(command[1], (float) money);
                            }
                        }


                    } else {
                        throw new LoginException("Wrong password!!!");
                    }
                }else{
                    throw new LoginException("you are out of access!!!");
                }
            }
        }
    }

    public void making_account_not_accessible(String cardnumber) throws SQLException {
        db.update(cardnumber);
        for (Account account : accounts) {
            if (account.getCard_number().equals(cardnumber)) {
                account.setAccess(false);
            }
        }
    }
}
