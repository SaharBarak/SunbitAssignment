package customerapi.sunbitassignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.address.city = ?1")
    List<Customer> findByCity(String city);
    @Query("SELECT c FROM Customer c WHERE c.firstName = ?1")
    List<Customer> findByName(String city);


}
