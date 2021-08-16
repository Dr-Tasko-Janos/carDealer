package vehicles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private long id;

    private String make;

    private String model;

    private int year;

    private int kilometers;

    private ConditionOfTheVehicle conditionOfTheVehicle;

    private EngineType engineType;

    private NumberOfSeats numberOfSeats;

    private int price;
}
