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
public class AdditionalServiceForPrice {

    private final CarsRepository carsRepository;

    private final ModelMapper modelMapper;

    private Type targetListType = new TypeToken<List<CarDto>>() {
    }.getType();

    public List<CarDto> listAllCars() {
        List<Car> allCarsInStock = carsRepository.findAll();

        return modelMapper.map(allCarsInStock, targetListType);
    }

    public List<CarDto> listByPrice(Optional<String> price) {

        String[] parameters = new String[2];
        Integer[] parametersInInt = new Integer[2];
        if (price.isPresent()) {
            parameters = price.get().split("-");

            parametersInInt[0] = Integer.parseInt(parameters[0]);
            parametersInInt[1] = Integer.parseInt(parameters[1]);
        }

        return listAllCars().stream().filter(e -> price.isEmpty() || (e.getPrice()
                >= parametersInInt[0] && e.getPrice() <= parametersInInt[1])).collect(Collectors.toList());
    }
}
