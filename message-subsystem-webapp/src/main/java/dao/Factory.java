package dao;

public class Factory {
    private static PostDAO postDAO=null;
    private static Factory instance=null;
    public static synchronized Factory getInstance(){
        if (instance==null){
            instance=new Factory();
        }
        return instance;
    }
    public PostDAO getPostDAO(){
        if(postDAO==null) {
            postDAO=new PostsDAOImpl();
        }
        return postDAO;
    }
}
