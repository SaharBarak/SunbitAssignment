package customerapi.sunbitassignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository)
    {
        return args -> {
            log.info("Preloading " + repository.save(
                    new Customer("Sahar", "Barak",
                            new Address("Qiryat Tivon", "Harimonim", "18", "303030"),
                            new CreditCardDetails("123412341234", "03/33", "333"),
                            new DateOfBirth(1997, 9, 18), "3333333")));
            log.info("Preloading " + repository.save(
                    new Customer("John", "Hopkins",
                            new Address("Denver", "11", "11", "111111"),
                            new CreditCardDetails("123412341235", "03/34", "334"),
                            new DateOfBirth(1999, 9, 9), "3333335")));
            log.info("Preloading " + repository.save(
                    new Customer("Amy", "Hopkins",
                            new Address("Denver", "11", "11", "111111"),
                            new CreditCardDetails("123412341236", "03/34", "336"),
                            new DateOfBirth(1997, 9, 9), "3333339")));

            repository.findAll().forEach(customer -> log.info("Preloaded " + customer));
        };
    }
}