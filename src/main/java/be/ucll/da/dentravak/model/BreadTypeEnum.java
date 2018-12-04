package be.ucll.da.dentravak.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum BreadTypeEnum {

    @JsonProperty("Turkish bread")
    TURKISH_BREAD("Turkish bread"),
    @JsonProperty("Wrap")
    WRAP("Wrap"),
    @JsonProperty("Boterhammekes")
    BOTERHAMMEKES("Boterhammekes");

    private String name;

    BreadTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
