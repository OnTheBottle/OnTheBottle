package com.bottle.user.test;

import com.bottle.user.model.entity.UserEntity;
import com.bottle.user.model.hibernate.HibernateSessionFactory;
import org.hibernate.Session;

public class TestApp {

    public static void main(String[] args) {
        System.out.println("Hibernate tutorial");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        UserEntity userEntity = new UserEntity();

        userEntity.setAge(15);
        userEntity.setName("Nick");
        userEntity.setSurname("VN");
        userEntity.setEmail("email@email.com");
        userEntity.setPassword("111");
        userEntity.setAvatarUrl("www.user.com");
        userEntity.setLogin("user");
        userEntity.setCity("my city");
        userEntity.setCountry("my country");

        session.save(userEntity);
        session.getTransaction().commit();

        session.close();
    }
}
