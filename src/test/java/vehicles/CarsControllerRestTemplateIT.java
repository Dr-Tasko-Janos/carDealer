package vehicles;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarsControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    CarsService carsService;


    @Test
    void testListAllCars() {

        carsService.deleteAll();

        CarDto carDto1 = template.postForObject("/api/cars/", new CreateCarCommand("AUDI", "S6", 2001, 239713, ConditionOfTheVehicle.USED, EngineType.DIESEL, NumberOfSeats.FIVE_SEATER, 279000, "John", "Doe", "+3630/123-4567", "AUD1463787890DEFH"), CarDto.class);
        CarDto carDto2 = template.postForObject("/api/cars/", new CreateCarCommand("BMW", "I3", 2016, 84682, ConditionOfTheVehicle.EXCELLENT, EngineType.ELECTRIC, NumberOfSeats.FOUR_SEATER, 6100000, "Jane", "Doe", "+3630/323-4567", "BMW1463787890DEFH"), CarDto.class);
        CarDto carDto3 = template.postForObject("/api/cars/", new CreateCarCommand("Chevrolet", "Captiva", 2013, 214571, ConditionOfTheVehicle.EXCELLENT, EngineType.GASOLINE, NumberOfSeats.SEVEN_SEATER, 214571, "Jack", "Smith", "+3630/423-4567", "Chevrolet7890DEFH"), CarDto.class);
        CarDto carDto4 = template.postForObject("/api/cars/", new CreateCarCommand("Mazda", "MX-5", 2006, 119626, ConditionOfTheVehicle.USED, EngineType.GASOLINE, NumberOfSeats.TWO_SEATER, 2900000, "Bill", "Smith", "+3630/523-4567", "MAZDAABC5234567890DEFH"), CarDto.class);


        List<CarDto> allCars = template.exchange("/api/cars/", HttpMethod.GET, null, new ParameterizedTypeReference<List<CarDto>>() {
        }).getBody();

        assertThat(allCars).extracting(CarDto::getMake).contains("AUDI", "BMW", "Chevrolet", "Mazda");
        assertThat(allCars).size().isEqualTo(4);
    }

    @Test
    void testCreateCarByOwnerSight() {
        carsService.deleteAll();

        CarDto carDto2 = template.postForObject("/api/cars/", new CreateCarCommand("BMW", "I3", 2016, 84682, ConditionOfTheVehicle.EXCELLENT, EngineType.ELECTRIC, NumberOfSeats.FOUR_SEATER, 6100000, "Jane", "Doe", "+3630/323-4567", "BMW1463787890DEFH"), CarDto.class);


        List<SensitiveDataOfOwnerAndVehicleDto> sensitiveData = template.exchange("/api/owners/", HttpMethod.GET, null, new ParameterizedTypeReference<List<SensitiveDataOfOwnerAndVehicleDto>>() {
        }).getBody();
        String vin = sensitiveData.get(0).getVehicleIdentificationNumber();
        assertThat(vin).startsWith("BM");
    }

    @Test
    void testSelectById() {
        carsService.deleteAll();

        CarDto carDto3 = template.postForObject("/api/cars/", new CreateCarCommand("Chevrolet", "Captiva", 2013, 214571, ConditionOfTheVehicle.EXCELLENT, EngineType.GASOLINE, NumberOfSeats.SEVEN_SEATER, 214571, "Jack", "Smith", "+3630/423-4567", "Chevrolet7890DEFH"), CarDto.class);
        CarDto carDto4 = template.postForObject("/api/cars/", new CreateCarCommand("Mazda", "MX-5", 2006, 119626, ConditionOfTheVehicle.USED, EngineType.GASOLINE, NumberOfSeats.TWO_SEATER, 2900000, "Bill", "Smith", "+3630/523-4567", "MAZDAABC5234567890DEFH"), CarDto.class);

        CarDto foundCar = template.exchange("/api/cars/2", HttpMethod.GET, null, CarDto.class).getBody();
        assertThat(foundCar).extracting(CarDto::getMake).isEqualTo("Mazda");
    }

    @Test
    void testFindOwnerById() {
        carsService.deleteAll();

        CarDto carDto1 = template.postForObject("/api/cars/", new CreateCarCommand("AUDI", "S6", 2001, 239713, ConditionOfTheVehicle.USED, EngineType.DIESEL, NumberOfSeats.FIVE_SEATER, 279000, "John", "Doe", "+3630/123-4567", "AUD1463787890DEFH"), CarDto.class);
        CarDto carDto2 = template.postForObject("/api/cars/", new CreateCarCommand("BMW", "I3", 2016, 84682, ConditionOfTheVehicle.EXCELLENT, EngineType.ELECTRIC, NumberOfSeats.FOUR_SEATER, 6100000, "Jane", "Doe", "+3630/323-4567", "BMW1463787890DEFH"), CarDto.class);
        CarDto carDto3 = template.postForObject("/api/cars/", new CreateCarCommand("Chevrolet", "Captiva", 2013, 214571, ConditionOfTheVehicle.EXCELLENT, EngineType.GASOLINE, NumberOfSeats.SEVEN_SEATER, 214571, "Jack", "Smith", "+3630/423-4567", "Chevrolet7890DEFH"), CarDto.class);
        CarDto carDto4 = template.postForObject("/api/cars/", new CreateCarCommand("Mazda", "MX-5", 2006, 119626, ConditionOfTheVehicle.USED, EngineType.GASOLINE, NumberOfSeats.TWO_SEATER, 2900000, "Bill", "Smith", "+3630/523-4567", "MAZDAABC5234567890DEFH"), CarDto.class);


        SensitiveDataOfOwnerAndVehicleDto foundOwner = template.exchange("/api/owners/3", HttpMethod.GET, null, SensitiveDataOfOwnerAndVehicleDto.class).getBody();
        assertThat(foundOwner).extracting(SensitiveDataOfOwnerAndVehicleDto::getOwnerFirstName).isEqualTo("Jack");
    }

    @Test
    void testSelectByMake() {
        carsService.deleteAll();

        CarDto carDto3 = template.postForObject("/api/cars/", new CreateCarCommand("Chevrolet", "Captiva", 2013, 214571, ConditionOfTheVehicle.EXCELLENT, EngineType.GASOLINE, NumberOfSeats.SEVEN_SEATER, 214571, "Jack", "Smith", "+3630/423-4567", "Chevrolet7890DEFH"), CarDto.class);
        CarDto carDto4 = template.postForObject("/api/cars/", new CreateCarCommand("Mazda", "MX-5", 2006, 119626, ConditionOfTheVehicle.USED, EngineType.GASOLINE, NumberOfSeats.TWO_SEATER, 2900000, "Bill", "Smith", "+3630/523-4567", "MAZDAABC5234567890DEFH"), CarDto.class);

        List<CarDto> foundCarByMake = template.exchange("/api/cars/make?make=CheVrolET", HttpMethod.GET, null, new ParameterizedTypeReference<List<CarDto>>() {
        }).getBody();
        String make = foundCarByMake.get(0).getMake();
        assertThat(make).isEqualTo("Chevrolet");
    }

    @Test
    void testSelectByModel() {
        carsService.deleteAll();

        CarDto carDto3 = template.postForObject("/api/cars/", new CreateCarCommand("Chevrolet", "Captiva", 2013, 214571, ConditionOfTheVehicle.EXCELLENT, EngineType.GASOLINE, NumberOfSeats.SEVEN_SEATER, 214571, "Jack", "Smith", "+3630/423-4567", "Chevrolet7890DEFH"), CarDto.class);
        CarDto carDto4 = template.postForObject("/api/cars/", new CreateCarCommand("Mazda", "MX-5", 2006, 119626, ConditionOfTheVehicle.USED, EngineType.GASOLINE, NumberOfSeats.TWO_SEATER, 2900000, "Bill", "Smith", "+3630/523-4567", "MAZDAABC5234567890DEFH"), CarDto.class);

        List<CarDto> foundCarByModel = template.exchange("/api/cars/model?model=MX-5", HttpMethod.GET, null, new ParameterizedTypeReference<List<CarDto>>() {
        }).getBody();
        String model = foundCarByModel.get(0).getModel();
        assertThat(model).isEqualTo("MX-5");
    }

    @Test
    void testSelectByCondition() {
        carsService.deleteAll();

        CarDto carDto3 = template.postForObject("/api/cars/", new CreateCarCommand("Chevrolet", "Captiva", 2013, 214571, ConditionOfTheVehicle.EXCELLENT, EngineType.GASOLINE, NumberOfSeats.SEVEN_SEATER, 214571, "Jack", "Smith", "+3630/423-4567", "Chevrolet7890DEFH"), CarDto.class);
        CarDto carDto4 = template.postForObject("/api/cars/", new CreateCarCommand("Mazda", "MX-5", 2006, 119626, ConditionOfTheVehicle.USED, EngineType.GASOLINE, NumberOfSeats.TWO_SEATER, 2900000, "Bill", "Smith", "+3630/523-4567", "MAZDAABC5234567890DEFH"), CarDto.class);

        List<CarDto> foundCarByModel = template.exchange("/api/cars/condition?condition=USED", HttpMethod.GET, null, new ParameterizedTypeReference<List<CarDto>>() {
        }).getBody();
        String foundCondition = foundCarByModel.get(0).getConditionOfTheVehicle().toString();
        assertThat(foundCondition).isEqualTo("USED");
    }

    @Test
    void testSelectByEngineType() {

        carsService.deleteAll();

        CarDto carDto3 = template.postForObject("/api/cars/", new CreateCarCommand("Chevrolet", "Captiva", 2013, 214571, ConditionOfTheVehicle.EXCELLENT, EngineType.GASOLINE, NumberOfSeats.SEVEN_SEATER, 214571, "Jack", "Smith", "+3630/423-4567", "Chevrolet7890DEFH"), CarDto.class);
        CarDto carDto1 = template.postForObject("/api/cars/", new CreateCarCommand("AUDI", "S6", 2001, 239713, ConditionOfTheVehicle.USED, EngineType.DIESEL, NumberOfSeats.FIVE_SEATER, 279000, "John", "Doe", "+3630/123-4567", "AUD1463787890DEFH"), CarDto.class);
        CarDto carDto4 = template.postForObject("/api/cars/", new CreateCarCommand("Mazda", "MX-5", 2006, 119626, ConditionOfTheVehicle.USED, EngineType.GASOLINE, NumberOfSeats.TWO_SEATER, 2900000, "Bill", "Smith", "+3630/523-4567", "MAZDAABC5234567890DEFH"), CarDto.class);

        List<CarDto> foundCarByEngine = template.exchange("/api/cars/engine?engine=DIESEL", HttpMethod.GET, null, new ParameterizedTypeReference<List<CarDto>>() {
        }).getBody();
        String foundEngine = foundCarByEngine.get(0).getEngineType().toString();
        assertThat(foundEngine).isEqualTo("DIESEL");
        assertThat(foundCarByEngine.size()).isEqualTo(1);
    }

    @Test
    void testSelectByEngineNumberOfSeats() {

        carsService.deleteAll();

        CarDto carDto3 = template.postForObject("/api/cars/", new CreateCarCommand("Chevrolet", "Captiva", 2013, 214571, ConditionOfTheVehicle.EXCELLENT, EngineType.GASOLINE, NumberOfSeats.SEVEN_SEATER, 214571, "Jack", "Smith", "+3630/423-4567", "Chevrolet7890DEFH"), CarDto.class);
        CarDto carDto1 = template.postForObject("/api/cars/", new CreateCarCommand("AUDI", "S6", 2001, 239713, ConditionOfTheVehicle.USED, EngineType.DIESEL, NumberOfSeats.FIVE_SEATER, 279000, "John", "Doe", "+3630/123-4567", "AUD1463787890DEFH"), CarDto.class);
        CarDto carDto4 = template.postForObject("/api/cars/", new CreateCarCommand("Mazda", "MX-5", 2006, 119626, ConditionOfTheVehicle.USED, EngineType.GASOLINE, NumberOfSeats.TWO_SEATER, 2900000, "Bill", "Smith", "+3630/523-4567", "MAZDAABC5234567890DEFH"), CarDto.class);

        List<CarDto> foundCarByNumberOfSeats = template.exchange("/api/cars/numberOfSeats?numberOfSeats=SEVEN_SEATER", HttpMethod.GET, null, new ParameterizedTypeReference<List<CarDto>>() {
        }).getBody();
        String foundSeats = foundCarByNumberOfSeats.get(0).getNumberOfSeats().toString();
        assertThat(foundSeats).isEqualTo("SEVEN_SEATER");
        assertThat(foundCarByNumberOfSeats.size()).isEqualTo(1);
        assertThat(foundCarByNumberOfSeats.get(0).getModel()).isEqualTo("Captiva");
    }


}

