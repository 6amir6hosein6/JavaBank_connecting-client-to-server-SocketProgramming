package Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by AmirHosein on 5/25/2018.
 */

public class jobControler implements Initializable {
    @FXML
    private RadioButton cash;
    @FXML
    private RadioButton money;
    @FXML
    private Label lbl;
    @FXML
    private AnchorPane content;

    public void ok(ActionEvent actionEvent) throws IOException {
        String job = null;
        JSONObject jsonObject = new JSONObject();
        if(cash.isSelected()){
            try {
                jsonObject.put("command","cash");
                jsonObject.put("distcard","0");
                jsonObject.put("money",0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            FXMLDocumentController.clientWriter.println(jsonObject);
            FXMLDocumentController.clientWriter.flush();

            AnchorPane pane = FXMLLoader.load(getClass().getResource("getingCash.fxml"));
            content.getChildren().setAll(pane);


        }else if (money.isSelected()){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("transfer_money.fxml"));
            content.getChildren().setAll(pane);
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
