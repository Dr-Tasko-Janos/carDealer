package vehicles;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarCommand {

    @Schema(description = "The name of the Car Brand which produced the car", example = "Audi")
    private String make;

    @Schema(description = "The Type Name of the car, given by the manufacturer", example = "S6 Avant")
    private String model;

    @Positive
    @Digits(integer = 4, fraction = 0)
    @Schema(description = "The year when the car was assembled", example = "2001")
    private int year;

    @PositiveOrZero
    @Schema(description = "'Kilometerage' of the car (aka Mileage of the car given in kilometers)", example = "239713")
    private int kilometers;

    @NotNull
    @Schema(description = "The condition of the car", example = "EXCELLENT")
    private ConditionOfTheVehicle conditionOfTheVehicle;

    @NotNull
    @Schema(description = "The kind of the engine based on the resource that use (gasoline-, diesel fuel or electricity)",
            example = "gasoline")
    private EngineType engineType;

    @NotNull
    @Schema(description = "For how many passenger have room in the car", example = "FIVE_SEATER")
    private NumberOfSeats numberOfSeats;

    @Schema(description = "The price tag for the car in HUF", example = "2790000")
    @Price
    private int price;

    @NotBlank
    @Schema(description = "The first name of the vehicle's owner", example = "John")
    private String ownerFirstName;

    @NotBlank
    @Schema(description = "The last name of the vehicle's owner", example = "Doe")
    private String ownerLastName;

    @NotBlank
    @Schema(description = "The phone number given by the owner to get in touch with him/her in case of something", example = "+3630/123-4567")
    private String ownerPhoneNumber;

    @NotBlank
    @Vin
    @Schema(description = "The unique identification number of the car known as in abbreviated form: \'VIN\'", example = "ABC1234567890DEFH")
    private String vehicleIdentificationNumber;

}
