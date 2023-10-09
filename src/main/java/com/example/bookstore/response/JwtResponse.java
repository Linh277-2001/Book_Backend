package com.example.bookstore.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private int id;
    private String name;
    private String username;
    private String email;
    private List<String> roles;
    private String avatar;
    private  boolean status;
    public JwtResponse(String accessToken, int id,String name, String username, String email, List<String> roles, String avatar, boolean status) {
        this.token = accessToken;
        this.id = id;
        this.name=name;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.avatar= avatar;
        this.status=status;
    }

//    public JwtResponse(String accessToken, int id, String name, String username, String email, List<String> roles) {
//        this.token = accessToken;
//        this.id = id;
//        this.name=name;
//        this.username = username;
//        this.email = email;
//        this.roles = roles;
//    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
