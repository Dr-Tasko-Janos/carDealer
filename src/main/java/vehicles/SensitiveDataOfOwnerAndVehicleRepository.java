package vehicles;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class SensitiveDataOfOwnerAndVehicleRepository {

    private final EntityManagerFactory factorySensitive;

    public SensitiveDataOfOwnerAndVehicleRepository(EntityManagerFactory factorySensitive) {
        this.factorySensitive = factorySensitive;
    }

    public void saveNewSensitiveDataOfOwnerAndVehicle(SensitiveDataOfOwnerAndVehicle newSensitiveDataOfOwnerAndVehicle) {
        EntityManager emCar = factorySensitive.createEntityManager();
        emCar.getTransaction().begin();
        emCar.persist(newSensitiveDataOfOwnerAndVehicle);
        emCar.getTransaction().commit();
        emCar.close();
    }
}