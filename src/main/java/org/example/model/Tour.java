package org.example.model;

import org.springframework.stereotype.Component;


import javax.persistence.*;

import javax.validation.constraints.Min;

@Entity
@Component
public class Tour  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String destination;

    @Min(value = 0)
    private Double price;
    @OneToOne
    @JoinColumn(name = "type_id")
    private Type type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Tour() {
    }

    public Tour(Long id, String code, String destination, Double price, Type type) {
        this.id = id;
        this.code = code;
        this.destination = destination;
        this.price = price;
        this.type = type;
    }
//@Override
//public boolean supports(Class<?> clazz) {
//    return Tour.class.isAssignableFrom(clazz);
//}
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        Tour tour = (Tour) target;
//        Double price = tour.getPrice();
//        ValidationUtils.rejectIfEmpty(errors, "price", "number.empty");
//
//        if (price < 20){
//            errors.rejectValue("price", "number.matches");
//        }
//    }


}
