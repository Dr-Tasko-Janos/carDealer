package vehicles;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/owners/")
public class SensitiveDataOfOwnerAndVehicleController {

    SensitiveDataOfOwnerAndVehicleService sensitiveDataOfOwnerAndVehicleService;

    public SensitiveDataOfOwnerAndVehicleController(SensitiveDataOfOwnerAndVehicleService sensitiveDataOfOwnerAndVehicleService) {
        this.sensitiveDataOfOwnerAndVehicleService = sensitiveDataOfOwnerAndVehicleService;
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<SensitiveDataOfOwnerAndVehicleDto> selectAllSensitiveData() {
        return sensitiveDataOfOwnerAndVehicleService.selectAllSensitiveData();
    }
}
