package vehicles;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SensitiveDataOfOwnerAndVehicleDto findSensitiveDataById(@PathVariable("id") long id) {
        return sensitiveDataOfOwnerAndVehicleService.findSensitiveDataById(id);
    }
}
