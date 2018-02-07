package com.bottle.findPerson.hibernate.factory;


import javax.security.auth.login.Configuration;

public class HibernateUtil {
      private   static  SessionFactory sessionFactory = null;
        static {
            try{
                sessionFactory=new Configuration().configure().buildSessionFactory();
            }catch(Exeption e){
                e.printStacktTrace();
            }
        }
        public static sessoinFactory getSessionFactory(){
            return sessionFactory;
        }

    }

