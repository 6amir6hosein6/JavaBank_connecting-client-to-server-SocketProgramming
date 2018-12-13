package Bank_Servaer;

import org.json.JSONException;
import org.json.JSONObject;

import javax.security.auth.login.LoginException;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Techno Service on 5/24/2018.
 */
public class BankServer {
    static PrintWriter serverWriter;
    static Scanner serverReader;
    public static void main(String[] args) throws JSONException {
        JSONObject js = new JSONObject();
        //-------------------------Server-------------------------------------
        try {
            ServerSocket serverSocket = new ServerSocket(3659);
            Socket clientSocket = serverSocket.accept();
            serverWriter = new PrintWriter(clientSocket.getOutputStream());
            serverReader = new Scanner(clientSocket.getInputStream());
            System.out.println("new customer connected !!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //--------------------------------------------------------------

        //----------------------Checking account------------------------
        String request = serverReader.nextLine();
        json_parser_login jp_login = new json_parser_login();
        String[] login = jp_login.parsing(request).split(":");
        String cardnumber = login[0];
        String password = login[1]; ;
        System.out.println("card number is : "+cardnumber);
        System.out.println("password : "+password);
        CheckingAccount checkingAccount = new CheckingAccount();
        try {
            checkingAccount.checking_cardnumber(cardnumber);
            int number_of_inserting=0;
            while(true) {



                login = jp_login.parsing(request).split(":");
                cardnumber = login[0];
                password = login[1];
                try {
                    checkingAccount.checking_password(cardnumber, password);

                } catch (LoginException e) {
                    js.put("message",e.getMessage());
                    serverWriter.println(js);
                    BankServer.serverWriter.flush();


                    number_of_inserting++;
                    //System.out.println(number_of_inserting);
                    request = serverReader.nextLine();
                }
                if (number_of_inserting == 3) {
                    checkingAccount.making_account_not_accessible(cardnumber);
                    System.out.println("your account is not accessible any more!!!");
                    break;
                }
            }
        } catch (LoginException e) {
            js.put("message",e.getMessage());
            serverWriter.println(js);
            BankServer.serverWriter.flush();
        } catch (SQLException e) {
        }
        //--------------------------------------------------------------
    }
}

