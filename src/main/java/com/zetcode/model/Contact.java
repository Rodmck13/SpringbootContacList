package com.zetcode.model;


import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String number;

    public Contact() {
    }

    public Contact(String name, String number) {

        this.name = name;
        this.number = number;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getNumber() {

        return number;
    }

    public void setNumber(String number) {

        this.number = number;
    }

    @Override
    public int hashCode() {

        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.number);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Contact other = (Contact) obj;
        if (this.number != other.number) {
            return false;
        }

        if (!Objects.equals(this.name, other.name)) {
            return false;
        }

        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {

        var builder = new StringBuilder();
        builder.append("Contact{id=").append(id).append(", name=")
                .append(name).append(", number=")
                .append(number).append("}");

        return builder.toString();
    }
}