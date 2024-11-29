package hongik.api_c011088.controller;

import hongik.api_c011088.repository.StudentRepository;
import hongik.api_c011088.service.StudentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/degree")
    public ResponseEntity<String> getDegreeByName(@RequestParam("name") String name) {
        String result = studentService.getDegreeByName(name);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/email")
    public ResponseEntity<String> getEmailByName(@RequestParam("name") String name) {
        String result = studentService.getEmailByName(name);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/stat")
    public ResponseEntity<String> getCountByDegree(@RequestParam("degree") String degree) {
        String result = studentService.getCountByDegree(degree);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/register")
    public ResponseEntity<String> registerStudent(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("graduation") String graduation,
            @RequestParam("degree") String degree
    ) {
        String result = studentService.registerStudent(name, email, degree, graduation);
        return ResponseEntity.ok(result);
    }
}