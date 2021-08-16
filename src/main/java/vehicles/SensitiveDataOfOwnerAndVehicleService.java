package vehicles;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SensitiveDataOfOwnerAndVehicleService {

    private final ModelMapper modelMapper1;

    private final SensitiveDataOfOwnerAndVehicleRepository sensitiveDataOfOwnerAndVehicleRepository;

    private final Type targetListTypeForSensitive = new TypeToken<List<SensitiveDataOfOwnerAndVehicleDto>>(){}.getType();


    public void createNewOwner(SensitiveDataOfOwnerAndVehicle sensitiveDataOfOwnerAndVehicle) {
        sensitiveDataOfOwnerAndVehicleRepository.saveNewSensitiveDataOfOwnerAndVehicle(sensitiveDataOfOwnerAndVehicle);
    }

    public List<SensitiveDataOfOwnerAndVehicleDto> selectAllSensitive() {
       List<SensitiveDataOfOwnerAndVehicle> allSelectedData = sensitiveDataOfOwnerAndVehicleRepository.selectAllSensitive();
       return modelMapper1.map(allSelectedData, targetListTypeForSensitive);
    }
}
