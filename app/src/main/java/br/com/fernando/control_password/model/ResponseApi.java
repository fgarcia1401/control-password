package br.com.fernando.control_password.model;

import java.io.Serializable;

/**
 * Created by Fernando
 */

public class ResponseApi implements Serializable {


    private String type;

    private String token;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
