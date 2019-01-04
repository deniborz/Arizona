package be.ucll.da.dentravak.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.time.ZoneId;

@Entity
@Table(name = "order_table")
public class Order {    
    @Id
    @GeneratedValue
    private UUID id;
    private String mobilePhoneNumber;
    private BreadTypeEnum breadType;
    private UUID sandwichId;
    private BigDecimal price;
    private LocalDateTime creationDate;
    private String name;
    private boolean printed;

    public Order(){
        this.creationDate = LocalDateTime.now(ZoneId.systemDefault());
        this.printed = false;
    }

    public UUID getId() {
        return id;
    }

    public void printed(){
        this.printed = true;
    }
    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public Enum getBreadType() {
        return breadType;
    }

    public UUID getSandwichId() {
        return sandwichId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getName() {
        return name;
    }

    public boolean getPrinted(){
        return printed;
    }

    private Order(OrderBuilder builder){
        this.creationDate = LocalDateTime.now(ZoneId.systemDefault());
        this.mobilePhoneNumber = builder.mobilePhoneNumber;
        this.breadType = builder.breadType;
        this.sandwichId = builder.sandwichId;
        this.price = builder.price;
        this.name = builder.name;
    }

    public static class OrderBuilder{
        private String mobilePhoneNumber;
        private BreadTypeEnum breadType;
        private UUID sandwichId;
        private BigDecimal price;
        private String name;


        public OrderBuilder setMobilePhoneNumber(String mobilePhoneNumber) {
            this.mobilePhoneNumber = mobilePhoneNumber;
            return this;
        }

        public OrderBuilder setBreadType(String breadType) {
            this.breadType = BreadTypeEnum.valueOf(breadType);
            return this;
        }

        public OrderBuilder setSandwichId(UUID sandwichId) {
            this.sandwichId = sandwichId;
            return this;
        }

        public OrderBuilder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public OrderBuilder setName(String name){
            this.name = name;
            return this;
        }
        public Order build(){
            return new Order(this);
        }
    }
}
