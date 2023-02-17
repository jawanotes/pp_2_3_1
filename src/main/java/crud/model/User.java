package crud.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component("prototype")
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;
    @Column(name = "lastname")
    private String lastName;

    public User() {
    }
    public User(Long id) {
        this.id = id;
    }

    public User(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public User(Long id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
