package Customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Techno Service on 5/25/2018.
 */
public class getingCashControler implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private Label lbl;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        json_parser_message jsonParserMessage = new json_parser_message();
        String jrespons = FXMLDocumentController.clientReader.nextLine();
        String respons = null;
        try {
            respons = jsonParserMessage.json_parser(jrespons);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        lbl.setText("Your cash is : "+respons);
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("job.fxml"));
        content.getChildren().setAll(pane);
    }

    public void Exit(ActionEvent actionEvent) {

    }
}
