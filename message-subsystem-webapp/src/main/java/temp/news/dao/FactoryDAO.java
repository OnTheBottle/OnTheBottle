package temp.news.dao;

public class FactoryDAO {
    private static PostDAO postDAO = null;
    private static UserDAO userDAO = null;
    private static FactoryDAO factoryDAO = null;

    public static synchronized FactoryDAO getFactory() {
        if (factoryDAO == null) {
            factoryDAO = new FactoryDAO();
        }
        return factoryDAO;
    }

    public PostDAO getPostDAO() {
        if (postDAO == null) {
            postDAO = new PostDAO();
        }
        return postDAO;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }
}
