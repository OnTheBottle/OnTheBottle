package com.bottle.userWall.dao;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts", schema = "onthebottle")
public class Posts {
    private int id;
    private String text;
    private Date date;
    private String image;
    private String security;

    @Override
    public String toString() {
        return date+text+image+id+";";
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text", nullable = true, length = 300)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = true)
    public Date getDate(){
        return  date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "image", nullable = true, length = 255)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   @Basic
   @Column(name = "security", nullable = true, length = 60)
   public String getSecurity(){
        return security;
}
   public  void setSecurity(String security){
        this.security=security;
}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Posts that = (Posts) o;

        if (id != that.id) return false;
        if (text != null ? !text.equals( that.text ) : that.text != null) return false;
        if (date != null ? !date.equals( that.date ) : that.date != null) return false;
        if (image != null ? !image.equals( that.image ) : that.image != null) return false;
        if (security != null ? !security.equals( that.security ) : that.security != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (security != null ? security.hashCode() : 0);
        return result;
    }
}
