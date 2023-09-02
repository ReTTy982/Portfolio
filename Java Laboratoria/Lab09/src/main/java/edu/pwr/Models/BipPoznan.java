package edu.pwr.Models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@XmlRootElement(name = "bip.poznan.pl")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class BipPoznan {
    @XmlElement
    protected Data data;

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement
    @lombok.Data
    public static class Data{
        @XmlElement(name = "karty_informacyjne")
        protected InformationCards informationCards;
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlRootElement(name = "karty_informacyjne")
        @lombok.Data
        public static class InformationCards{
            @XmlElement
            protected int start;
            @XmlElement
            protected int stop;
            @XmlElement
            protected int size;
            @XmlElement(name = "total_size")
            protected int totalSize;
            @XmlElement
            protected Items items;
            @XmlAccessorType(XmlAccessType.FIELD)
            @lombok.Data
            public static class Items{
                @XmlElement(name = "karta_informacyjna")
                protected List<InformationCard> informationCardList;
            }


        }

    }


}
