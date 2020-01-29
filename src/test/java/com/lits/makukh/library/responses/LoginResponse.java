package com.lits.makukh.library.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    @JsonProperty("r")
    private AccessInfo r;

    public AccessInfo getR() {
        return r;
    }

    public void setR(AccessInfo r) {
        this.r = r;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "r=" + r +
                '}';
    }
}
