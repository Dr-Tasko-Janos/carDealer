package vehicles;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Repository
public class CarsRepository {

private EntityManagerFactory factoryCar;

    public CarsRepository(EntityManagerFactory entityManagerFactory) {
        this.factoryCar = Persistence.createEntityManagerFactory("pu");
    }

    public void saveNewCar(Car newCar, SensitiveDataOfOwnerAndVehicle sensitiveDataOfOwnerAndVehicle) {
        EntityManager emCar = factoryCar.createEntityManager();
        emCar.getTransaction().begin();
        emCar.persist(newCar);
        sensitiveDataOfOwnerAndVehicle.setCar(newCar);
        emCar.persist(sensitiveDataOfOwnerAndVehicle);
        emCar.getTransaction().commit();
        emCar.close();
    }

    public List<Car> findAll() {
        EntityManager em = factoryCar.createEntityManager();
        List<Car> foundCars = em.createQuery("select e from Car e order by e.model", Car.class).getResultList();
        em.close();
        return foundCars;
    }

    public Car updatePrice(Long id, int newPrice) {
        EntityManager em = factoryCar.createEntityManager();
        em.getTransaction().begin();
        Car foundCarForPriceUpdate = em.createQuery("select e from Car e where e.id = :id", Car.class)
                .setParameter("id", id)
                .getSingleResult();
        foundCarForPriceUpdate.setPrice(newPrice);
        em.getTransaction().commit();
        em.close();
        return foundCarForPriceUpdate;
    }

}
