package vehicles;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.lang.reflect.Type;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarsService {

    private final CarsRepository carsRepository;

    private final SensitiveDataOfOwnerAndVehicleRepository sensitiveRepository;

    private final ModelMapper modelMapper;

    private Type targetListType = new TypeToken<List<CarDto>>() {
    }.getType();

    public List<CarDto> listAllCars() {
        List<Car> allCarsInStock = carsRepository.findAll();

        return modelMapper.map(allCarsInStock, targetListType);
    }

    public CarDto saveACar(CreateCarCommand command) {
        Car newCar = new Car(command.getMake(),
                command.getModel(),
                command.getYear(),
                command.getKilometers(),
                command.getConditionOfTheVehicle(),
                command.getEngineType(),
                command.getNumberOfSeats(),
                command.getPrice());


        SensitiveDataOfOwnerAndVehicle newSensitiveDataOfOwnerAndVehicle = new SensitiveDataOfOwnerAndVehicle(command.getOwnerFirstName(),
                command.getOwnerLastName(),
                command.getOwnerPhoneNumber(),
                command.getVehicleIdentificationNumber());

        carsRepository.saveNewCar(newCar, newSensitiveDataOfOwnerAndVehicle);

        return modelMapper.map(newCar, CarDto.class);

    }

    public CarDto findCarById(long id) {
        return listAllCars().stream().filter(e -> e.getId() == id).findFirst().map(e -> modelMapper.map(e, CarDto.class)).orElseThrow(() -> new CarNotFoundException(id));
    }

    public CarDto updateCarPrice(long id, UpdateCarPriceCommand command) {
        Car carForPriceUpdate = carsRepository.updatePrice(id, command.getPrice());
        return modelMapper.map(carForPriceUpdate, CarDto.class);
    }

    public void deleteCar(long id) {
        carsRepository.deleteCar(id);
    }

    public void deleteAll() {
        carsRepository.deleteAll();
    }
}
