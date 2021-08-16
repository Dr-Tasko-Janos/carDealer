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

    public List<SensitiveDataOfOwnerAndVehicle> selectAllSensitive() {
        EntityManager em1 = factorySensitive.createEntityManager();
        em1.getTransaction().begin();
        List<SensitiveDataOfOwnerAndVehicle> allSensitiveData = em1.createQuery("select e from SensitiveDataOfOwnerAndVehicle e order by e.ownerFirstName", SensitiveDataOfOwnerAndVehicle.class).getResultList();
        em1.close();
        return allSensitiveData;
    }
}