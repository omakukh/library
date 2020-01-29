package com.lits.makukh.library.responses;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccessInfo{
    @JsonProperty("access_token")
    private String access_token;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("expires_in")
    private Integer expires_in;
    @JsonProperty("token_type")
    private String token_type;

}