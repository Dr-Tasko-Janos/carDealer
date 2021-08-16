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

    private final ModelMapper modelMapper;

    private final SensitiveDataOfOwnerAndVehicleRepository sensitiveDataOfOwnerAndVehicleRepository;

    private final CarsRepository carsRepository;

    private final Type targetListTypeForSensitive = new TypeToken<List<SensitiveDataOfOwnerAndVehicleDto>>(){}.getType();


    public void createNewOwner(SensitiveDataOfOwnerAndVehicle sensitiveDataOfOwnerAndVehicle) {
        sensitiveDataOfOwnerAndVehicleRepository.saveNewSensitiveDataOfOwnerAndVehicle(sensitiveDataOfOwnerAndVehicle);
    }

    public List<SensitiveDataOfOwnerAndVehicleDto> selectAllSensitiveData() {
        List<SensitiveDataOfOwnerAndVehicle> allSensitiveData = carsRepository.selectAllSensitive();
        return modelMapper.map(allSensitiveData, targetListTypeForSensitive);
    }

    public SensitiveDataOfOwnerAndVehicleDto findSensitiveDataById(long id) {
        return selectAllSensitiveData().stream().filter(e -> e.getId() == id).findFirst().map(e -> modelMapper.map(e, SensitiveDataOfOwnerAndVehicleDto.class)).orElseThrow(() -> new CarNotFoundException(id));
    }
}
