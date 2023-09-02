package edu.pwr.pl.Lab06.app.client.DTO;

public class ClientRequest {


    public ClientRequest(Long id, Long clientNr, String name, String surname) {
        this.id = id;
        this.clientNr = clientNr;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private Long clientNr;
    private String name;
    private String surname;

    public Long getClientNr() {
        return clientNr;
    }

    public void setClientNr(Long clientNr) {
        this.clientNr = clientNr;
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

    @Override
    public String toString() {
        return "ClientUpdateRequest{" +
                "id=" + id +
                ", clientNr=" + clientNr +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
