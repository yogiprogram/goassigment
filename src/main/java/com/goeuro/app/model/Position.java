package com.goeuro.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Position {

    @JsonProperty("_id")
    long id;

    String name;

    String type;

    @JsonProperty("geo_position")
    GeoLocation position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GeoLocation getPosition() {
        return position;
    }

    public void setPosition(GeoLocation position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position1 = (Position) o;

        if (id != position1.id) return false;
        if (name != null ? !name.equals(position1.name) : position1.name != null) return false;
        if (type != null ? !type.equals(position1.type) : position1.type != null) return false;
        return position != null ? position.equals(position1.position) : position1.position == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }

    public Position(long id, String name, String type, GeoLocation position) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.position = position;
    }

    public Position() {
    }
}
