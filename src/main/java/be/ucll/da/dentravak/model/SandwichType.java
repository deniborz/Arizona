package be.ucll.da.dentravak.model;

public enum SandwichType {

    TURKISH_BREAD("Turkish bread"),
    WRAP("Wrap"),
    BOTERHAMMEKES("Boterhammekes");

    private String name;

    SandwichType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
