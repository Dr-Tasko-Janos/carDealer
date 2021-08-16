package vehicles;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> listAllCars() {
        return carsService.listAllCars();
    }

    @GetMapping("/make")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> listByMake(@RequestParam Optional<String> make) {
        return additionalServiceForMake.listByMake(make);
    }

    @GetMapping("/model")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> listByModel(@RequestParam Optional<String> model) {
        return additionalServiceForModel.listByModel(model);
    }

    @GetMapping("/numberOfSeats")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> listByNumberOfSeats(@RequestParam Optional<String> numberOfSeats) {
        return additionalServiceForNumberOfSeats.listByNumberOfSeats(numberOfSeats);
    }

    @GetMapping("/condition")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> listByCondition(@RequestParam Optional<String> condition) {
        return additionalServiceForCondition.listByCondition(condition);
    }

    @GetMapping("/engine")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> listByEngine(@RequestParam Optional<String> engine) {
        return additionalServiceForEngine.listByEngine(engine);
    }

    @GetMapping("/kilometer")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> listByKilometer(@RequestParam Optional<String> kilometer) {
        return additionalServiceForKilometer.listByKilometer(kilometer);
    }

    @GetMapping("/year")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> listByYear(@RequestParam Optional<String> year) {
        return additionalServiceForYear.listByYear(year);
    }

    @GetMapping("/price")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> listByPrice(@RequestParam Optional<String> price) {
        return additionalServiceForPrice.listByPrice(price);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDto findCarById(@PathVariable("id") long id) {
        return carsService.findCarById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto saveACar(@RequestBody CreateCarCommand command) {
        return carsService.saveACar(command);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDto updateCarPrice(@PathVariable("id") long id, @RequestBody UpdateCarPriceCommand command) {
        return carsService.updateCarPrice(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable("id") long id) {
        carsService.deleteCar(id);
    }
}
