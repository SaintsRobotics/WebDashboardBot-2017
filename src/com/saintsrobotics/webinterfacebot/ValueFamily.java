import org.json.JSONArray;
import org.json.JSONObject;

public class ValueFamily {
	public final String index;
	private final JSONObject values;
	public ValueFamily(String index, JSONObject wi){
		this.index = index;
		this.values = wi;
	}
	public Object get(String key){
		return values.getJSONObject("client").getJSONObject(index).get(key);
	}
	public int getInt(String key){
		return values.getJSONObject("client").getJSONObject(index).getInt(key);
	}
	public double getDouble(String key){
		return values.getJSONObject("client").getJSONObject(index).getDouble(key);
	}
	public String getString(String key){
		return values.getJSONObject("client").getJSONObject(index).getString(key);
	}
	public JSONObject getJSON(String key){
		return values.getJSONObject("client").getJSONObject(index).getJSONObject(key);
	}
	public JSONArray getArray(String key){
		return values.getJSONObject("client").getJSONObject(index).getJSONArray(key);
	}
	public Object getOutput(String key){
		return values.getJSONObject("server").getJSONObject(index).get(key);
	}
	public int getIntOutput(String key){
		return values.getJSONObject("server").getJSONObject(index).getInt(key);
	}
	public double getDoubleOutput(String key){
		return values.getJSONObject("server").getJSONObject(index).getDouble(key);
	}
	public String getStringOutput(String key){
		return values.getJSONObject("server").getJSONObject(index).getString(key);
	}
	public JSONObject getJSONOutput(String key){
		return values.getJSONObject("server").getJSONObject(index).getJSONObject(key);
	}
	public JSONArray getArrayOutput(String key){
		return values.getJSONObject("server").getJSONObject(index).getJSONArray(key);
	}
	public JSONObject getAll(){
		return values.getJSONObject("server").getJSONObject(index);
	}
	public JSONObject getAllOutputs(){
		return values.getJSONObject("client").getJSONObject(index);
	}
	public void change(String key, Object value){
		JSONObject obj = new JSONObject();
		obj.put(key, value);
		change(obj);
	}
	public void change(JSONObject obj){
		JSONObject wrapper = new JSONObject();
		wrapper.put("type", "delta");
		wrapper.put(index, obj);
		SocketDummy.send(wrapper.toString());
	}
	
}
