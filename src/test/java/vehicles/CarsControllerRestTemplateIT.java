package vehicles;

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
        TestRestTemplate restTemplate;

        @Autowired
        CarsService carsService;

        @Test
        void testListCars() {

           CarDto carDto = restTemplate.postForObject("/api/cars/", new CreateCarCommand("Opel", "Astra", 2010, 1, ConditionOfTheVehicle.EXCELLENT, EngineType.ELECTRIC, NumberOfSeats.SEVEN_SEATER, 1000000, "John", "Doe", "1234", "11111111111111117"), CarDto.class);
            assertEquals("Opel",carDto.getMake());


        }



    }

