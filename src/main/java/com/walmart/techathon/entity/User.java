package com.walmart.techathon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Cloneable {
    private String userId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String mobileNumber;
    private WplusMember wplusMember;


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
