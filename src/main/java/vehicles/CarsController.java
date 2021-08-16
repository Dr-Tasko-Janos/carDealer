package vehicles;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars/")
@RequiredArgsConstructor
public class CarsController {

    private final CarsService carsService;

    private final AdditionalServiceForNumberOfSeats additionalServiceForNumberOfSeats;

    private final AdditionalServiceForMake additionalServiceForMake;

    private final AdditionalServiceForModel additionalServiceForModel;

    private final AdditionalServiceForCondition additionalServiceForCondition;

    private final AdditionalServiceForEngine additionalServiceForEngine;

    private final AdditionalServiceForKilometer additionalServiceForKilometer;

    private final AdditionalServiceForYear additionalServiceForYear;

    private final AdditionalServiceForPrice additionalServiceForPrice;


    @GetMapping()
    public List<CarDto> listAllCars() {
        return carsService.listAllCars();
    }

    @GetMapping("/make")
    public List<CarDto> listByMake(@RequestParam Optional<String> make) {
        return additionalServiceForMake.listByMake(make);
    }

    @GetMapping("/model")
    public List<CarDto> listByModel(@RequestParam Optional<String> model) {
        return additionalServiceForModel.listByModel(model);
    }

    @GetMapping("/numberOfSeats")
    public List<CarDto> listByNumberOfSeats(@RequestParam Optional<String> numberOfSeats) {
        return additionalServiceForNumberOfSeats.listByNumberOfSeats(numberOfSeats);
    }

    @GetMapping("/condition")
    public List<CarDto> listByCondition(@RequestParam Optional<String> condition) {
        return additionalServiceForCondition.listByCondition(condition);
    }

    @GetMapping("/engine")
    public List<CarDto> listByEngine(@RequestParam Optional<String> engine) {
        return additionalServiceForEngine.listByEngine(engine);
    }

    @GetMapping("/kilometer")
    public List<CarDto> listByKilometer(@RequestParam Optional<String> kilometer) {
        return additionalServiceForKilometer.listByKilometer(kilometer);
    }

    @GetMapping("/year")
    public List<CarDto> listByYear(@RequestParam Optional<String> year) {
        return additionalServiceForYear.listByYear(year);
    }

    @GetMapping("/price")
    public List<CarDto> listByPrice(@RequestParam Optional<String> price) {
        return additionalServiceForPrice.listByPrice(price);
    }

    @GetMapping("/{id}")
    public CarDto findCarById(@PathVariable("id") long id) {
        return carsService.findCarById(id);
    }

    @PostMapping()
    public CarDto saveACar(@RequestBody CreateCarCommand command) {
        return carsService.saveACar(command);
    }

    @PutMapping("/{id}")
    public CarDto updateCarPrice(@PathVariable("id") long id, @RequestBody UpdateCarPriceCommand command) {
        return carsService.updateCarPrice(id, command);
    }
}
