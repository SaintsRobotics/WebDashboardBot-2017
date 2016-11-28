package com.saintsrobotics.webinterfacebot.motors;
import org.json.JSONObject;
public class MotorsTestWebDashboard{
    private JSONObject vals;
    private WebDashboard web;
    public MotorsTestWebDashboard(WebDashboard web){
        this.web = web;
        vals = web.values.getJSONObject("client").getJSONObject("motors");
    }
    public Motor get(String motor){
        return Motors.get(vals.getJSONObject(motor).getInt("port"),vals.getJSONObject(motor).getBoolean("inverted"));
    }
    public void refresh(){
        Motors.stop();
        vals = web.values.getJSONObject("client").getJSONObject("motors");
    }
}