package org.acme.getting.started;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;


@Entity
public class Book extends PanacheEntity {

   //private Integer id;

   public String name;

   public Integer publicationYear;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }
   public static Book findByName(String name){
        return find("name", name).firstResult();
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", publicationYear='" + publicationYear + 
            '}';
    }
   
}