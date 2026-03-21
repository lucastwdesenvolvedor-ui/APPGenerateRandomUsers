package org.api.user_print;

import org.api.user.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class getUser {
    public static List<User> usersGet(JSONArray results , int nprint){
        List<User> todosUsuarios = new ArrayList<>();

        for (int i = 0; i < nprint; i++) {
        JSONObject user = results.getJSONObject(i);

        JSONObject name = user.getJSONObject("name");
        String first = name.getString("first");
        String last = name.getString("last");
        String title = name.getString("title");

        JSONObject login = user.getJSONObject("login");
        String email = user.getString("email");
        String cell = user.getString("cell");
        String usname = login.getString("username");

        JSONObject location = user.getJSONObject("location");
        String city = location.getString("city");
        String country = location.getString("country");

        String fullname =  title +" "+ first +" "+ last;

        User usuario = new User(fullname , usname, email, cell, country, city);
        todosUsuarios.add(usuario);
        }
        return todosUsuarios;
    }
}
