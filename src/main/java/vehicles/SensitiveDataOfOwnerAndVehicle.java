package vehicles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensitiveDataOfOwnerAndVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "owner_first_name")
    private String ownerFirstName;

    @Column(name = "owner_last_name")
    private String ownerLastName;

    @Column(name = "owner_phone_number")
    private String ownerPhoneNumber;

    @Column(name = "vehicle_identification_number")
    private String vehicleIdentificationNumber;

    @OneToOne
    private Car car;

    public SensitiveDataOfOwnerAndVehicle(String ownerFirstName, String ownerLastName, String ownerPhoneNumber, String vehicleIdentificationNumber) {
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
    }
}
