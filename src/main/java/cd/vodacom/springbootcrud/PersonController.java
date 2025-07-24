package cd.vodacom.springbootcrud;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
