package Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Techno Service on 5/26/2018.
 */

public class transfer_money_controler implements Initializable{

    @FXML
    private AnchorPane content;
    @FXML
    private TextField dist;
    @FXML
    private TextField dolarMoney;
    @FXML
    private TextField centMoney;
    public void ok(ActionEvent actionEvent) {
        String money = dolarMoney.getText()+"."+centMoney.getText();

        System.out.println("money transfer : "+money);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("command","tranfer");
            jsonObject.put("distcard",dist.getText());
            jsonObject.put("money",money);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FXMLDocumentController.clientWriter.println(jsonObject);
        FXMLDocumentController.clientWriter.flush();
    }

    public void back(ActionEvent actionEvent) throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("job.fxml"));
        content.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
