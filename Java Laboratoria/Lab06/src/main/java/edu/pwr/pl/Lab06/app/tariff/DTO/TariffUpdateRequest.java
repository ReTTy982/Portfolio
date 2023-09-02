package edu.pwr.pl.Lab06.app.tariff.DTO;

public class TariffUpdateRequest {
    private Long id;
    private String tariffType;
    private Float price;

    public TariffUpdateRequest(Long id, String tariffType, Float price) {
        this.id = id;
        this.tariffType = tariffType;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTariffType() {
        return tariffType;
    }

    public void setTariffType(String tariffType) {
        this.tariffType = tariffType;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TariffUpdateRequest{" +
                "id=" + id +
                ", tariffType='" + tariffType + '\'' +
                ", price=" + price +
                '}';
    }
}
