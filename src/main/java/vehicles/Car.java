package vehicles;

import io.swagger.v3.oas.annotations.media.Schema;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "car_brand")
    private String make;

    @Column(name = "car_model")
    private String model;

    @Column(name = "production_year")
    private int year;

    @Column(name = "kilometerage")
    private int kilometers;

    @Column(name = "condition_ot_the_vehicle")
    @Enumerated(EnumType.STRING)
    private ConditionOfTheVehicle conditionOfTheVehicle;

    @Column(name = "engine_type")
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @Column(name = "number_of_seats")
    @Enumerated(EnumType.STRING)
    private NumberOfSeats numberOfSeats;

    @Column(name = "price_tag")
    private int price;

    public Car(String make, String model, int year, int kilometers, ConditionOfTheVehicle conditionOfTheVehicle, EngineType engineType, NumberOfSeats numberOfSeats, int price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.kilometers = kilometers;
        this.conditionOfTheVehicle = conditionOfTheVehicle;
        this.engineType = engineType;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
    }

    public Car(String make) {
        this.make = make;
    }
}
