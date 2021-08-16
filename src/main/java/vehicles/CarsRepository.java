package vehicles;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CarsRepository {

private EntityManagerFactory factoryCar;

    public CarsRepository(EntityManagerFactory entityManagerFactory) {
        this.factoryCar = Persistence.createEntityManagerFactory("pu");
    }

    public void saveNewCar(Car newCar, SensitiveDataOfOwnerAndVehicle sensitiveDataOfOwnerAndVehicle) {
        EntityManager emNewCar = factoryCar.createEntityManager();
        emNewCar.getTransaction().begin();
        emNewCar.persist(newCar);
        sensitiveDataOfOwnerAndVehicle.setCar(newCar);
        emNewCar.persist(sensitiveDataOfOwnerAndVehicle);
        emNewCar.getTransaction().commit();
        emNewCar.close();
    }

    public List<Car> findAll() {
        EntityManager emFindAll = factoryCar.createEntityManager();
        List<Car> foundCars = emFindAll.createQuery("select e from Car e order by e.model", Car.class).getResultList();
        emFindAll.close();
        return foundCars;
    }

    public Car updatePrice(Long id, int newPrice) {
        EntityManager emUpdatePrice = factoryCar.createEntityManager();
        emUpdatePrice.getTransaction().begin();
        Car foundCarForPriceUpdate = emUpdatePrice.createQuery("select e from Car e where e.id = :id", Car.class)
                .setParameter("id", id)
                .getSingleResult();
        foundCarForPriceUpdate.setPrice(newPrice);
        emUpdatePrice.getTransaction().commit();
        emUpdatePrice.close();
        return foundCarForPriceUpdate;
    }

    public List<SensitiveDataOfOwnerAndVehicle> selectAllSensitive() {
        EntityManager emSensitive = factoryCar.createEntityManager();
        emSensitive.getTransaction().begin();
        List<SensitiveDataOfOwnerAndVehicle> allSensitiveData = emSensitive.createQuery("select e from SensitiveDataOfOwnerAndVehicle e join fetch e.car fetch all properties order by e.id", SensitiveDataOfOwnerAndVehicle.class).getResultList();
        emSensitive.close();
        return allSensitiveData;
    }

    public void deleteCar(long id) {
        EntityManager emDeleteCar = factoryCar.createEntityManager();
        emDeleteCar.getTransaction().begin();
        SensitiveDataOfOwnerAndVehicle ownerToDelete = emDeleteCar.createQuery("select e from SensitiveDataOfOwnerAndVehicle e where e.id = :id", SensitiveDataOfOwnerAndVehicle.class)
                .setParameter("id", id)
                .getSingleResult();
        emDeleteCar.remove(ownerToDelete);
        emDeleteCar.getTransaction().commit();
        emDeleteCar.getTransaction().begin();
        Car carToDelete = emDeleteCar.createQuery("select c from Car c where c.id = :id", Car.class)
                .setParameter("id", id)
                .getSingleResult();
        emDeleteCar.remove(carToDelete);
        emDeleteCar.getTransaction().commit();
        emDeleteCar.close();
    }

    public void deleteAll() {
        EntityManager emDeleteAll = factoryCar.createEntityManager();
        emDeleteAll.getTransaction().begin();
        Query query = emDeleteAll.createQuery("DELETE from SensitiveDataOfOwnerAndVehicle e where e.id > 0");
        query.executeUpdate();
        emDeleteAll.getTransaction().commit();
        emDeleteAll.getTransaction().begin();
        query = emDeleteAll.createQuery("DELETE from Car c where c.id > 0");
        query.executeUpdate();
        emDeleteAll.getTransaction().commit();
        emDeleteAll.close();
    }
}
