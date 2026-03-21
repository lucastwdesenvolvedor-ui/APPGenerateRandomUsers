package org.api.controller;

import static org.api.checagem.check.checkCountry;
import static org.api.user_print.getUser.usersGet;

import org.api.user.User;
import org.api.controller.HttpClientLib;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class controller_api {

    @GetMapping("/users")
    public List<User> users(@RequestParam String pais , @RequestParam int count) throws Exception {

            if(!checkCountry(pais)){
                throw new IllegalArgumentException("Código do país inválido. Digite um código válido. paises disponiveis AU, BR, CA, CH, DE, DK, ES, FI, FR, GB, IE, IN, IR, MX, NL, NO, NZ, RS, TR, UA, US.");
            };
            if(count < 1 || count > 100){
                throw new IllegalArgumentException("Número de usuários inválido. Digite um número entre 1 e 100.");
            }

            String json = HttpClientLib.get("https://randomuser.me/api/?nat=" + pais.toUpperCase()+"&results=" + count);
            JSONObject ojt = new JSONObject(json);
            JSONArray results = ojt.getJSONArray("results");

            return usersGet(results , count);
    }

}
