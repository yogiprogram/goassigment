package com.goeuro.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;


/**
 * Created by admin on 7/27/2016.
 */

@JsonPropertyOrder({"_id", "name", "type", "latitude", "longitude"})
public class CSVRow {
    @JsonProperty("_id")
    long id;

    String name;

    @JsonUnwrapped()
    GeoLocation position;

    String type;

    public CSVRow(long id, String name, GeoLocation position, String type) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.type = type;
    }

    public CSVRow() {

    }

    @Override
    public String toString() {
        return "CSVRow{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CSVRow csvRow = (CSVRow) o;

        if (id != csvRow.id) return false;
        if (name != null ? !name.equals(csvRow.name) : csvRow.name != null) return false;
        if (position != null ? !position.equals(csvRow.position) : csvRow.position != null) return false;
        return type != null ? type.equals(csvRow.type) : csvRow.type == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GeoLocation getPosition() {
        return position;
    }

    public void setPosition(GeoLocation position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
