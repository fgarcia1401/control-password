package br.com.fernando.control_password.model;

import java.io.Serializable;

/**
 * Created by fernando on 05/03/18.
 */

public class ResponseErrorApi implements Serializable {

    private String type;

    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
