package edu.pwr.pl.Lab06.app.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.pwr.pl.Lab06.app.installation.Installation;
import jakarta.persistence.*;

import java.util.Set;






@Entity
@Table
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String surname;
    private Long clientNr;

    @OneToMany(mappedBy = "client")
    @JsonIgnoreProperties("client")
    private Set<Installation> installationSet;

    public Client(Long id, String name, String surname, Long clientNr) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.clientNr = clientNr;
    }

    public Client() {
    }

    public Client(String name, String surname, Long clientNr) {
        this.name = name;
        this.surname = surname;
        this.clientNr = clientNr;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", clientNr=" + clientNr +
                '}';
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getClientNr() {
        return clientNr;
    }

    public void setClientNr(Long clientNr) {
        this.clientNr = clientNr;
    }
}
