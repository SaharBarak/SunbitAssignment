package customerapi.sunbitassignment;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class CustomerController
{
    private CustomerRepository repository;
    private CustomerModelAssembler assembler;

    CustomerController(CustomerRepository repository, CustomerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/customers")
    CollectionModel<EntityModel<Customer>> all()
    {
        List<EntityModel<Customer>> customers = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());
        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());


        /*List<EntityModel<Customer>> customers = repository.findAll().stream()
                .map(customer -> EntityModel.of(customer,
                        linkTo(methodOn(CustomerController.class).one(customer.getId())).withSelfRel(),
                        linkTo(methodOn(CustomerController.class).all()).withRel("customers")))
                .collect(Collectors.toList());

        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());*/
    }
    // end::get-aggregate-root[]
    @GetMapping("/customers/byAge/{minAge}-{maxAge}")
    CollectionModel<EntityModel<Customer>> byAgeRange(@PathVariable int minAge, @PathVariable int maxAge)
    {
        int currentYear = LocalDate.now().getYear();
        int minYear = currentYear - maxAge;
        int maxYear = currentYear - minAge;
        List<EntityModel<Customer>> customers = repository.findByAgeGroup(minYear, maxYear).stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class)
                .all()).withSelfRel());
    }
    @GetMapping("/customers/city={city}")
    CollectionModel<EntityModel<Customer>> byCity(@PathVariable String city)
    {
        List<EntityModel<Customer>> customers = repository.findByCity(city).stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class)
                .all()).withSelfRel());
    }
    @GetMapping("/customers/name")
    CollectionModel<EntityModel<Customer>> byName(@RequestParam String firstName, @RequestParam String lastName )
    {
        List<EntityModel<Customer>> customers = repository.findByName(firstName, lastName).stream()
                .map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class)
                .all()).withSelfRel());
    }
    @PostMapping("/customers")
    ResponseEntity<?> newCustomer(@RequestBody Customer newCustomer) {
        EntityModel<Customer> entityModel = assembler.toModel(repository.save(newCustomer));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }
    @GetMapping("/customers/{id}")
    EntityModel<Customer> one(@PathVariable Long id) {

        Customer customer = repository.findById(id) //
                .orElseThrow(() -> new CustomerNotFoundException(id));
        return assembler.toModel(customer);
    }
    @PutMapping("/customers/{id}")
    ResponseEntity<?> replaceCustomers(@RequestBody Customer newCustomer, @PathVariable Long id)
    {
        if(repository.existsById(id)) {
            Customer updatedCustomer = repository.findById(id)
                    .map(customer -> {
                        customer.setFirstName(newCustomer.getFirstName());
                        customer.setLastName(newCustomer.getLastName());
                        customer.setAddress(newCustomer.getAddress());
                        customer.setCreditCardDetails(newCustomer.getCreditCardDetails());
                        customer.setLicenseNumber(newCustomer.getLicenseNumber());
                        customer.setDateOfBirth(newCustomer.getDateOfBirth().getYear(),
                                newCustomer.getDateOfBirth().getMonth(),
                                newCustomer.getDateOfBirth().getDay());
                        return repository.save(customer);
                    })
                    .orElseGet(() -> {
                        newCustomer.setId(id);
                        return repository.save(newCustomer);
                    });
            EntityModel<Customer> entityModel = assembler.toModel(updatedCustomer);

            return ResponseEntity //
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                    .body(entityModel);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }
    @DeleteMapping("/customers/{id}")
    ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
