package temp.news.dao.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    private String id;
    private String authorId;
    private Date date;
    private String post;
    private Security securityId;

    public Post() {
    }

    public Post(String id, String authorId, Date date, String post, Security securityId) {
        this.id = id;
        this.authorId = authorId;
        this.date = date;
        this.post = post;
        this.securityId = securityId;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "POST_ID")
    public String getId() {
        return id;
    }

    @Column(name = "AUTOR_ID")
    public String getAuthorId() {
        return authorId;
    }

    @Column(name = "DATE")
    public Date getDate() {
        return date;
    }

    @Column(name = "POST")
    public String getPost() {
        return post;
    }

    //@Column(name = "SECURITY_ID")
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="SECURITY_ID",nullable = false)
    public Security getSecurityId() {
        return securityId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setSecurityId(Security security) {
        this.securityId = security;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", authorId='" + authorId + '\'' +
                ", date=" + date +
                ", post='" + post + '\'' +
                ", securityId=" + securityId.getName() +
                '}';
    }
}
