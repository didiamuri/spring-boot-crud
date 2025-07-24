package cd.vodacom.springbootcrud;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Person>>> findPersons() {
        List<Person> persons = personRepository.findAll();
        ApiResponse<List<Person>> response = new ApiResponse<>(HttpStatus.OK.value(), "success", "", persons);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Person>> createPerson(@RequestBody Person person) {
        Person createdPerson = personRepository.save(person);
        ApiResponse<Person> response = new ApiResponse<>(HttpStatus.CREATED.value(), "success", "Person Created Successfully!", createdPerson);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Person>> findPersonById(@PathVariable UUID id) {
        return personRepository.findById(id)
                .map(p -> new ResponseEntity<>(new ApiResponse<>(200, "success", "", p), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new ApiResponse<>(404, "error", "Not found", null), HttpStatus.NOT_FOUND));
    }

}
