package fr.avalonlab.warp10.lowlevel.model;

import java.time.Instant;
import java.util.Objects;

public class GTSFullText {
    Instant ts;
    String selector;
    private Long msTimestamp;
    private Double latitude;
    private Double longitude;
    private Long elevation;
    private String value;

    public GTSFullText(Instant ts, String selector, Long msTimestamp, Double latitude, Double longitude, Long elevation, String value) {
        this.ts = ts;
        this.selector = selector;
        this.msTimestamp = msTimestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
        this.value = value;
    }

    public Instant getTs() {
        return ts;
    }

    public String getSelector() {
        return selector;
    }

    public Long getMsTimestamp() {
        return msTimestamp;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Long getElevation() {
        return elevation;
    }

    public String getValue() {
        return value;
    }

    public double getDouble() {
        return Double.parseDouble(value);
    }



    @Override
    public String toString() {
        return "GTSFullText{" +
                "ts=" + ts +
                ", selector='" + selector + '\'' +
                ", msTimestamp=" + msTimestamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", elevation=" + elevation +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GTSFullText that = (GTSFullText) o;
        return Objects.equals(ts, that.ts) && Objects.equals(selector, that.selector) && Objects.equals(msTimestamp, that.msTimestamp) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude) && Objects.equals(elevation, that.elevation) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ts, selector, msTimestamp, latitude, longitude, elevation, value);
    }
}
