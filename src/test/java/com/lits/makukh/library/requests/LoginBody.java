package com.lits.makukh.library.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginBody{
    private String email;
    private String password;

}
