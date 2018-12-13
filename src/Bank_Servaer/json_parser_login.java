package Bank_Servaer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AmirHosein on 5/26/2018.
 */
public class json_parser_login {

    public String parsing(String jsonString) throws JSONException {
        String request=null;
        JSONObject jsonObject = new JSONObject(jsonString);
        String cardnumber = jsonObject.getString("cardnumber");
        String password = jsonObject.getString("password");
        request = cardnumber+":"+password;
        return request;
    }
}
