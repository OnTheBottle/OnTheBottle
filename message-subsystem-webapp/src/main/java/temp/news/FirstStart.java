package temp.news;

import temp.news.dao.HibernateFactory;
import temp.news.dao.entity.Post;
import temp.news.dao.entity.Security;
import temp.news.dao.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public final class FirstStart {
    private static List<Security> securityList;
    private static List<User> userList;
    private static List<Post> postList;

    public static void initialization() {
        try (Session session = HibernateFactory.getSessionFactory().openSession()) {
            initSecurity(session);
            initUser(session);
            initPost(session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initSecurity(Session session) {
        securityList = session.createQuery("from Security ").setFirstResult(1).list();
        if (securityList.size() == 0) {
            Transaction transaction = session.beginTransaction();
            session.save(new Security(1, "Public"));
            session.save(new Security(2, "Group"));
            session.save(new Security(3, "Private"));
            transaction.commit();
            securityList = session.createQuery("from Security ").list();
            System.out.println(securityList);
        }
    }

    private static void initUser(Session session) {
        userList = session.createQuery("from User ").setFirstResult(1).list();
        if (userList.size() == 0) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(
                    String.valueOf(UUID.randomUUID()),
                    "Alex",
                    "Korka",
                    "Dubai",
                    "/image/avatar/ava1.img"));
            session.save(new User(
                    String.valueOf(UUID.randomUUID()),
                    "Hren",
                    "Sgori",
                    "Jmot",
                    "/image/avatar/ava2.img"));
            session.save(new User(
                    String.valueOf(UUID.randomUUID()),
                    "Kolya",
                    "Klumba",
                    "Pechka",
                    "/image/avatar/ava3.img"));
            session.save(new User(
                    String.valueOf(UUID.randomUUID()),
                    "Nina",
                    "Gaga",
                    "Myau",
                    "/image/avatar/ava4.img"));
            session.save(new User(
                    String.valueOf(UUID.randomUUID()),
                    "Oleg",
                    "Dibrov",
                    "Coco",
                    "/image/avatar/ava5.img"));
            session.save(new User(
                    String.valueOf(UUID.randomUUID()),
                    "Vita",
                    "Days",
                    "Anasha",
                    "/image/avatar/ava6.img"));
            transaction.commit();
            userList = session.createQuery("from User ").list();
            //System.out.println(userList);
        }
    }

    private static void initPost(Session session) throws InterruptedException {
        postList = session.createQuery("from Post ").setFirstResult(1).list();
        if (postList.size() == 0) {
            Transaction transaction = session.beginTransaction();
            //String id, String authorId, Date date, String post, Security securityId
            session.save(new Post(
                    String.valueOf(UUID.randomUUID()),
                    userList.get(0).getId(),
                    new Date(),
                    "Okey Okey Okey. It's first post!",
                    securityList.get(0)
            ));
            Thread.sleep(3000);
            session.save(new Post(
                    String.valueOf(UUID.randomUUID()),
                    userList.get(1).getId(),
                    new Date(),
                    "It's second post!",
                    securityList.get(1)
            ));
            Thread.sleep(3000);
            session.save(new Post(
                    String.valueOf(UUID.randomUUID()),
                    userList.get(0).getId(),
                    new Date(),
                    "It's third post!",
                    securityList.get(2)
            ));
            Thread.sleep(3000);
            session.save(new Post(
                    String.valueOf(UUID.randomUUID()),
                    userList.get(0).getId(),
                    new Date(),
                    "It's fourth post!",
                    securityList.get(0)
            ));
            Thread.sleep(3000);
            session.save(new Post(
                    String.valueOf(UUID.randomUUID()),
                    userList.get(2).getId(),
                    new Date(),
                    "It's fifth post!",
                    securityList.get(0)
            ));
            Thread.sleep(3000);
            session.save(new Post(
                    String.valueOf(UUID.randomUUID()),
                    userList.get(2).getId(),
                    new Date(),
                    "djushfdjshfdsfh",
                    securityList.get(0)
            ));
            Thread.sleep(3000);
            session.save(new Post(
                    String.valueOf(UUID.randomUUID()),
                    userList.get(2).getId(),
                    new Date(),
                    "ФЫывфы выавыаололд фвыакфыв вфыавфы авфыа фыва вфыавы",
                    securityList.get(0)
            ));
            Thread.sleep(3000);
            session.save(new Post(
                    String.valueOf(UUID.randomUUID()),
                    userList.get(3).getId(),
                    new Date(),
                    "Hello all!",
                    securityList.get(0)
            ));
            Thread.sleep(3000);
            session.save(new Post(
                    String.valueOf(UUID.randomUUID()),
                    userList.get(4).getId(),
                    new Date(),
                    "I want drink",
                    securityList.get(1)
            ));
            Thread.sleep(3000);
            session.save(new Post(
                    String.valueOf(UUID.randomUUID()),
                    userList.get(5).getId(),
                    new Date(),
                    "Ш рфму іуч!!!",
                    securityList.get(2)
            ));
            transaction.commit();
        }
    }

    public static Set<User> getFriends() {
        Set<User> friends = new HashSet<>();
        friends.add(userList.get(1));
        friends.add(userList.get(3));
        friends.add(userList.get(4));
        return friends;
    }
}
