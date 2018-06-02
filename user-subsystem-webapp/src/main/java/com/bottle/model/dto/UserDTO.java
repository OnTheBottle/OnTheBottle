package com.bottle.model.dto;

import com.bottle.entity.User;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String name;
    private String surname;
    private int age;
    private String avatarUrl;
    private String city;
    private String country;
    private List<UUID> friendsId;
    private String friendStatus;

    public static Builder newBuilder() {
        return new UserDTO().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public UserDTO build() {
            return UserDTO.this;
        }

        public Builder setId(UUID id) {
            UserDTO.this.id = id;
            return this;
        }

        public Builder setName(String name) {
            UserDTO.this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            UserDTO.this.surname = surname;
            return this;
        }

        public Builder setAge(int age) {
            UserDTO.this.age = age;
            return this;
        }

        public Builder setAvatarUrl(String avatarUrl) {
            UserDTO.this.avatarUrl = avatarUrl;
            return this;
        }

        public Builder setCity(String city) {
            UserDTO.this.city = city;
            return this;
        }

        public Builder setCountry(String country) {
            UserDTO.this.country = country;
            return this;
        }
    }
}