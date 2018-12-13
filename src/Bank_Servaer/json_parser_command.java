package Bank_Servaer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Techno Service on 5/26/2018.
 */
public class json_parser_command {

    public String json_parser(String jsonfile) throws JSONException {
        String request=null;
        JSONObject jsonObject = new JSONObject(jsonfile);
        String command = jsonObject.getString("command");
        String distcard = jsonObject.getString("distcard");
        String money = jsonObject.getString("money");
        System.out.println("your money : "+money);
        request = command+":"+distcard+":"+money;
        return request;

    }
}
