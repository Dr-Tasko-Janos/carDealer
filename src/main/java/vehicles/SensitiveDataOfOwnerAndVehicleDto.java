package vehicles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensitiveDataOfOwnerAndVehicleDto {

    private long id;

    private String ownerFirstName;

    private String ownerLastName;

    private String ownerPhoneNumber;

    private String vehicleIdentificationNumber;

}
