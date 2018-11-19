package be.ucll.da.dentravak.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import be.ucll.da.dentravak.model.Sandwich;

@Entity
@Table(name = "order_table")
public class Order {
    //TODO broodtype hier of in sandwich?
    //https://github.com/rdehuyss/ucll-dentravak/projects/1
    @Id
    @GeneratedValue
    private UUID id;
    private String phoneNumber;
    private LocalDateTime date;
    @OneToMany
    private Map<UUID, Sandwich> sandwiches;

    private Order(){}

    public UUID getId() {
        return id;
    }

    public String getPhoneNumber() { return phoneNumber; }

    public LocalDateTime getDate() { return date; }

    public Map<UUID, Sandwich> getSandwiches() {
        return sandwiches;
    }

    private Order(OrderBuilder builder){
        this.date = LocalDateTime.now();
        this.phoneNumber = builder.phoneNumber;
        this.sandwiches = builder.sandwiches;
    }

    public static class OrderBuilder{
        private String phoneNumber;
        private HashMap<UUID, Sandwich> sandwiches;

        public OrderBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;return this;
        }

        public OrderBuilder setSandwiches(HashMap<UUID, Sandwich> sandwiches) {
            this.sandwiches = sandwiches;return this;
        }

        public OrderBuilder withSandwich(Sandwich sandwich){
            if (sandwiches == null){
                sandwiches = new HashMap<>();
            }
            sandwiches.put(sandwich.getId(), sandwich);
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }
}
