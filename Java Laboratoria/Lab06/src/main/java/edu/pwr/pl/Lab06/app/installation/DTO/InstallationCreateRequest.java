package edu.pwr.pl.Lab06.app.installation.DTO;

public class InstallationCreateRequest {

    private String address;
    private Long routerNumber;
    private String tariffType;
    private Long clientNr;

    public InstallationCreateRequest(String address, Long routerNumber, String serviceType, String tariffType, Long clientNr) {
        this.address = address;
        this.routerNumber = routerNumber;
        this.tariffType = tariffType;
        this.clientNr = clientNr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getRouterNumber() {
        return routerNumber;
    }

    public void setRouterNumber(Long routerNumber) {
        this.routerNumber = routerNumber;
    }


    public String getTariffType() {
        return tariffType;
    }

    public void setTariffType(String tariffType) {
        this.tariffType = tariffType;
    }

    public Long getClientNr() {
        return clientNr;
    }

    public void setClientNr(Long clientNr) {
        this.clientNr = clientNr;
    }

    @Override
    public String toString() {
        return "InstallationCreateRequest{" +
                "address='" + address + '\'' +
                ", routerNumber=" + routerNumber +
                ", tariffType='" + tariffType + '\'' +
                ", clientNr=" + clientNr +
                '}';
    }
}
