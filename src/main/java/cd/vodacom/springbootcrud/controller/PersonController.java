package cd.vodacom.springbootcrud.controller;

import cd.vodacom.springbootcrud.util.ApiResponse;
import cd.vodacom.springbootcrud.entity.Person;
import cd.vodacom.springbootcrud.service.PersonService;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "success", "List of people successfully recovered", persons));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Person>> create(@RequestBody Person person) {
        Person created = personService.create(person);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "success", "Person Created Successfully!", created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Person>> findById(@PathVariable UUID id) {
        return personService.findById(id)
                .map(p -> ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK.value(), "success", "", p)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "error", "Person not found", null)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Person>> update(@PathVariable UUID id, @RequestBody Person personData) {
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
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(HttpStatus.OK.value(), "success", "Person Updated Successfully!", updated));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "error", "Person not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Person>> delete(@PathVariable UUID id) {
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            personService.delete(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponse<>(HttpStatus.OK.value(), "success", "Person Deleted Successfully!", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "error", "Person not found", null));
    }

}
