package be.ucll.da.dentravak.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SandwichType {

    @JsonProperty("Turkish bread")
    TURKISH_BREAD("Turkish bread"),
    @JsonProperty("Wrap")
    WRAP("Wrap"),
    @JsonProperty("Boterhammekes")
    BOTERHAMMEKES("Boterhammekes");

    private String name;

    SandwichType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
