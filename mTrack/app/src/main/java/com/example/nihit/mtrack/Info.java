package com.example.nihit.mtrack;

/**
 * Created by Nihit on 19-02-2017.
 */
public class Info {

    private  String Id,Time,Route_name;

    public Info(String ID,String Time,String Route_name){
        this.setId(ID);
        this.setTime(Time);
        this.setBus_no(Route_name);
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getBus_no() {
        return Route_name;
    }

    public void setBus_no(String route_name) {
        Route_name = route_name;
    }
}
