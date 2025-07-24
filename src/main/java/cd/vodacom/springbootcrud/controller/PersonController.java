package cd.vodacom.springbootcrud.controller;

import cd.vodacom.springbootcrud.util.ApiResponse;
import cd.vodacom.springbootcrud.entity.Person;
import cd.vodacom.springbootcrud.service.PersonService;
import cd.vodacom.springbootcrud.util.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Person>>> findAll() {
        List<Person> persons = personService.findAll();
        return ResponseUtil.ok("List of people successfully recovered", persons);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Person>> create(@Valid @RequestBody Person person) {
        Person created = personService.create(person);
        return ResponseUtil.created("Person Created Successfully", created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Person>> findById(@PathVariable UUID id) {
        return personService.findById(id)
                .map(p -> ResponseUtil.ok("success", p))
                .orElse(ResponseUtil.notFound("Person not found"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Person>> update(@PathVariable UUID id, @Valid @RequestBody Person personData) {
        Optional<Person> person = personService.findById(id);

        if (person.isPresent()) {
            Person existing = person.get();
            existing.setName(personData.getName());
            existing.setEmail(personData.getEmail());
            existing.setPhoneNumber(personData.getPhoneNumber());
            existing.setCompany(personData.getCompany());
            existing.setCity(personData.getCity());
            existing.setCountry(personData.getCountry());
            existing.setStatus(personData.getStatus());

            Person updated = personService.update(existing);
            return ResponseUtil.ok("Person Updated Successfully!", updated);
        }
        return ResponseUtil.notFound("Person not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Person>> delete(@PathVariable UUID id) {
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            personService.delete(id);
            return ResponseUtil.ok("Person Deleted Successfully!", null);
        }
        return ResponseUtil.notFound("Person not found");
    }

}
