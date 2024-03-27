package cz.spsmb.model;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "console")
public class Console implements Serializable {
    public Console(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "console_id")
    long id;
    String brand;
    int year;
    @OneToMany(mappedBy = "rating")
    List<Rating> ratingList = new ArrayList<>();



    //@ManyToOne(fetch = FetchType.LAZY)
    //Type type;
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    Type type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public double getAvgRating(){
        long sum = 0;
        for (Rating r:ratingList) {
            sum += r.getRating();
        }
        return sum/(double)ratingList.size();
    }

    @Override
    public String toString() {
        return "Console{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", ratingList=" + ratingList.size() +
                ", type=" + type +
                '}';
    }
}