package vehicles;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/owners/")
public class SensitiveDataOfOwnerAndVehicleController {

    SensitiveDataOfOwnerAndVehicleService sensitiveDataOfOwnerAndVehicleService;

    public SensitiveDataOfOwnerAndVehicleController(SensitiveDataOfOwnerAndVehicleService sensitiveDataOfOwnerAndVehicleService) {
        this.sensitiveDataOfOwnerAndVehicleService = sensitiveDataOfOwnerAndVehicleService;
    }

    public void createNewOwner(SensitiveDataOfOwnerAndVehicle sensitiveDataOfOwnerAndVehicle) {
        sensitiveDataOfOwnerAndVehicleService.createNewOwner(sensitiveDataOfOwnerAndVehicle);
    }

    @GetMapping()
    public List<SensitiveDataOfOwnerAndVehicleDto> selectAllSensitiveData() {
        return sensitiveDataOfOwnerAndVehicleService.selectAllSensitive();
    }
}
