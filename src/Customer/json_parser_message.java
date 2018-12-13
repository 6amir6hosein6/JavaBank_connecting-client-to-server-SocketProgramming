package Customer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Techno Service on 5/27/2018.
 */
public class json_parser_message {
    public String json_parser(String json) throws JSONException {
        String message = null;
        JSONObject jsonObject = new JSONObject(json);
        message = jsonObject.getString("message");
        return message;
    }
}
