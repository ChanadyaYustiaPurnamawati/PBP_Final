package com.chanadyayustiapurnamawati.pbp_uas_final_e.Motor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MotorResponse {
    @SerializedName("data")
    @Expose
    private List<motorDAO> users = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<motorDAO> getUsers() {
        return users;
    }

    public String getMessage() {
        return message;
    }

    public void setUsers(List<motorDAO> users) {
        this.users = users;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
