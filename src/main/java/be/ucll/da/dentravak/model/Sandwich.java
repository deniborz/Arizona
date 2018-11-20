package be.ucll.da.dentravak.model;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sandwich {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String ingredients;
    private BigDecimal price;

    private Sandwich(){}

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    private Sandwich(SandwichBuilder builder){
        this.name = builder.name;
        this.ingredients = builder.ingredients;
        this.price = builder.price;
    }

    public static class SandwichBuilder{
        private String name;
        private String ingredients;
        private BigDecimal price;

        public SandwichBuilder setName(String name) {
            this.name = name; return this;
        }

        public SandwichBuilder setIngredients(String ingredient) {
            this.ingredients = ingredient; return this;
        }

        public SandwichBuilder setPrice(BigDecimal price) {
            this.price = price; return this;
        }

        public Sandwich build(){
            return new Sandwich(this);
        }
    }
}
