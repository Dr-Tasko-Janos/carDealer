package vehicles;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdditionalServiceForNumberOfSeats {

    private final CarsRepository carsRepository;

    private final ModelMapper modelMapper;

    private Type targetListType = new TypeToken<List<CarDto>>() {
    }.getType();

    public List<CarDto> listAllCars() {
        List<Car> allCarsInStock = carsRepository.findAll();

        return modelMapper.map(allCarsInStock, targetListType);
    }

    public List<CarDto> listByNumberOfSeats(Optional<String> numberOfSeats) {
        return listAllCars().stream().filter(e -> numberOfSeats.isEmpty() || e.getNumberOfSeats().toString()
                .toUpperCase().equals(numberOfSeats.get().toUpperCase())).collect(Collectors.toList());
    }
}
