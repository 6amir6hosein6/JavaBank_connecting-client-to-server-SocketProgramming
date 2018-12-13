
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author AmirHosein
 */
public class FXMLDocumentController implements Initializable {
    private Socket socket;
    static PrintWriter clientWriter;
    static Scanner clientReader;
    @FXML
    private AnchorPane content;
    @FXML
    private TextField card;
    @FXML
    private PasswordField password;
    @FXML
    private Label lbl;
    public void ok(ActionEvent actionEvent) throws IOException, JSONException {
        //String request = card.getText()+":"+password.getText();
        json_parser_message jsonParserMessage = new json_parser_message();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cardnumber",card.getText());
            jsonObject.put("password",password.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        clientWriter.println(jsonObject);
        clientWriter.flush();

        String jrespons = clientReader.nextLine();
        String respons = jsonParserMessage.json_parser(jrespons);
        if (respons.equals("good")){
            AnchorPane pane= FXMLLoader.load(getClass().getResource("job.fxml"));
            content.getChildren().setAll(pane);
        }else {
            lbl.setText(respons);
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket("localhost",3659);
            clientReader = new Scanner(socket.getInputStream());
            clientWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
