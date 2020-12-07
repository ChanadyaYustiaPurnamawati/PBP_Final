package com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("data")
    @Expose
    private List<userDAOCustomer> users = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<userDAOCustomer> getUsers() {
        return users;
    }

    public String getMessage() {
        return message;
    }

    public void setUsers(List<userDAOCustomer> users) {
        this.users = users;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}