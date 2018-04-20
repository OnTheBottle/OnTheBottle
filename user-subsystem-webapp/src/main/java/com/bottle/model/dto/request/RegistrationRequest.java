package com.bottle.model.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    private String login;
    private String password;
    private String email;
    private String name;
    private String city;
    private int age;
    private String surname;
    private String avatarUrl;
    private String country;

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", surname='" + surname + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
