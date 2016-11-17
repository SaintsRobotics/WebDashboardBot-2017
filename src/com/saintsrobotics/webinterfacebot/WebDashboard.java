import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;
public class WebDashboard {
	public JSONObject values;
	public WebDashboard(){
		try {
			values = new JSONObject(new String(Files.readAllBytes(Paths.get("html/define.json"))));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void onMessage(String message){
		JSONObject json = new JSONObject(message);
		if(json.getString("type").equals("error")){
			return;
			//welp
			//TODO: actually fix this
		}
		boolean confirm = json.getString("type").equals("confirm");
		Iterator<String> keys =json.keys();
		while(keys.hasNext()){
			String changeKey = keys.next();
			if(changeKey.equals("type")){
				if(!keys.hasNext()) break;
				changeKey = keys.next();
			}
			JSONObject changeValues = values.getJSONObject(confirm?"client":"server").getJSONObject(changeKey);
			Iterator<String> things = json.getJSONObject(changeKey).keys();
			while(things.hasNext()){
				String key = things.next();
				if(!changeValues.has(key)){
					SocketDummy.send("{'type':'error','message':'I never told you to do that'}");
					return;
				}
			}
			things = json.getJSONObject(changeKey).keys();
			while(things.hasNext()){
				String key = things.next();
				System.out.println(confirm?"client":"server" + " " + changeKey + " " + key);
				values.getJSONObject(confirm?"client":"server").getJSONObject(changeKey).put(key, json.getJSONObject(changeKey).get(key));
			}
		}
	}
	public ValueFamily family(String s){
		return new ValueFamily(s,this.values);
	}
}
