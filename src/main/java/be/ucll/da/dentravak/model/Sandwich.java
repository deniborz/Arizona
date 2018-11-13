package be.ucll.da.dentravak.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Sandwich {
    private UUID id;
    private String name;
    private String ingredient;
    private BigDecimal price;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredient() {
        return ingredient;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private Sandwich(SandwichBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.ingredient = builder.ingredient;
        this.price = builder.price;
    }

    public static class SandwichBuilder{
        private UUID id;
        private String name;
        private String ingredient;
        private BigDecimal price;

        public SandwichBuilder setId(UUID id) {
            this.id = id; return this;
        }

        public SandwichBuilder setName(String name) {
            this.name = name; return this;
        }

        public SandwichBuilder setIngredient(String ingredient) {
            this.ingredient = ingredient; return this;
        }

        public SandwichBuilder setPrice(BigDecimal price) {
            this.price = price; return this;
        }

        public Sandwich build(){
            return new Sandwich(this);
        }
    }
}
