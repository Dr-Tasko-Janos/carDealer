package vehicles;

import org.junit.jupiter.api.BeforeAll;
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
        void testListCars() {

            carsService.deleteAll();

            CarDto carDto1 = template.postForObject("/api/cars/", new CreateCarCommand("AUDI", "S6", 2001, 239713, ConditionOfTheVehicle.USED, EngineType.DIESEL, NumberOfSeats.FIVE_SEATER, 279000, "John", "Doe", "+3630/123-4567", "AUD1463787890DEFH"), CarDto.class);
            CarDto carDto2 = template.postForObject("/api/cars/", new CreateCarCommand("BMW", "I3", 2016, 84682, ConditionOfTheVehicle.EXCELLENT, EngineType.ELECTRIC, NumberOfSeats.FOUR_SEATER, 6100000, "Jane", "Doe", "+3630/323-4567", "BMW1463787890DEFH"), CarDto.class);
            CarDto carDto3 = template.postForObject("/api/cars/", new CreateCarCommand("Chevrolet", "Captiva", 2013, 214571, ConditionOfTheVehicle.EXCELLENT, EngineType.GASOLINE, NumberOfSeats.SEVEN_SEATER, 214571, "Jack", "Smith", "+3630/423-4567", "Chevrolet7890DEFH"), CarDto.class);
            CarDto carDto4 = template.postForObject("/api/cars/", new CreateCarCommand("Mazda", "MX-5", 2006, 119626, ConditionOfTheVehicle.USED, EngineType.GASOLINE, NumberOfSeats.TWO_SEATER, 2900000, "Bill", "Smith", "+3630/523-4567", "MAZDAABC5234567890DEFH"), CarDto.class);


            List<CarDto> allCars = template.exchange("/api/cars/", HttpMethod.GET, null, new ParameterizedTypeReference<List<CarDto>>() {}).getBody();

           assertThat(allCars).extracting(CarDto::getMake).contains("AUDI","BMW", "Chevrolet", "Mazda");
           assertThat(allCars).size().isEqualTo(4);
        }

        @Test
        void testCreateCarByOwnerSight() {
            carsService.deleteAll();

            CarDto carDto2 = template.postForObject("/api/cars/", new CreateCarCommand("BMW", "I3", 2016, 84682, ConditionOfTheVehicle.EXCELLENT, EngineType.ELECTRIC, NumberOfSeats.FOUR_SEATER, 6100000, "Jane", "Doe", "+3630/323-4567", "BMW1463787890DEFH"), CarDto.class);


            List<SensitiveDataOfOwnerAndVehicleDto> sensitiveData = template.exchange("/api/owners/", HttpMethod.GET, null, new ParameterizedTypeReference<List<SensitiveDataOfOwnerAndVehicleDto>>() {}).getBody();
            String vin = sensitiveData.get(0).getVehicleIdentificationNumber();
            assertThat(vin).startsWith("BM");
        }





    }

