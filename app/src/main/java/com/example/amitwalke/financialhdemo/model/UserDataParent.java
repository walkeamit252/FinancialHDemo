package com.example.amitwalke.financialhdemo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class UserDataParent implements Serializable,Comparator{

@SerializedName("user_first_name")
private String userFirstName;
@SerializedName("user_last_name")
private String userLastName;
@SerializedName("user_mobile")
private String userMobile;

public String getUserFirstName() {
return userFirstName;
}

public void setUserFirstName(String userFirstName) {
this.userFirstName = userFirstName;
}

public String getUserLastName() {
return userLastName;
}

public void setUserLastName(String userLastName) {
this.userLastName = userLastName;
}

public String getUserMobile() {
return userMobile;
}

public void setUserMobile(String userMobile) {
this.userMobile = userMobile;
}

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

}