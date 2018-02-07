package com.bottle.findPerson.hibernate.model;

import javax.persistence.*;

@Entity
@Table(name = "overflow", schema = "find_person_flow", catalog = "")
public class OverflowEntity {
    private int id;
    private String offName;
    private String offSecondName;
    private String offCity;
    private String offRegion;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "off_name", nullable = true, length = 45)
    public String getOffName() {
        return offName;
    }

    public void setOffName(String offName) {
        this.offName = offName;
    }

    @Basic
    @Column(name = "off_second_name", nullable = true, length = 45)
    public String getOffSecondName() {
        return offSecondName;
    }

    public void setOffSecondName(String offSecondName) {
        this.offSecondName = offSecondName;
    }

    @Basic
    @Column(name = "off_city", nullable = true, length = 45)
    public String getOffCity() {
        return offCity;
    }

    public void setOffCity(String offCity) {
        this.offCity = offCity;
    }

    @Basic
    @Column(name = "off_region", nullable = true, length = 45)
    public String getOffRegion() {
        return offRegion;
    }

    public void setOffRegion(String offRegion) {
        this.offRegion = offRegion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OverflowEntity that = (OverflowEntity) o;

        if (id != that.id) return false;
        if (offName != null ? !offName.equals(that.offName) : that.offName != null) return false;
        if (offSecondName != null ? !offSecondName.equals(that.offSecondName) : that.offSecondName != null)
            return false;
        if (offCity != null ? !offCity.equals(that.offCity) : that.offCity != null) return false;
        if (offRegion != null ? !offRegion.equals(that.offRegion) : that.offRegion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (offName != null ? offName.hashCode() : 0);
        result = 31 * result + (offSecondName != null ? offSecondName.hashCode() : 0);
        result = 31 * result + (offCity != null ? offCity.hashCode() : 0);
        result = 31 * result + (offRegion != null ? offRegion.hashCode() : 0);
        return result;
    }
}
