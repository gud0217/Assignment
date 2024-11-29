package hongik.api_c011088.service;

import hongik.api_c011088.domain.Students;
import hongik.api_c011088.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public String getDegreeByName(String name) {
        List<Students> students = studentRepository.findByName(name);

        if (students.isEmpty()) {
            return "No such student";
        }

        if (students.size() > 1) {
            return "There are multiple students with the same name. Please provide an email address instead.";


        }

        return students.get(0).getName() + " : " + students.get(0).getDegree();
    }

    @Transactional
    public String getEmailByName(String name) {
        List<Students> students = studentRepository.findByName(name);

        if (students.isEmpty()) {
            return "No such student";
        }

        if (students.size() > 1) {
            return "There are multiple students with the same name. Please contact the administrator by phone.";
        }

        return students.get(0).getName() + " : " + students.get(0).getEmail();
    }

    @Transactional
    public String getCountByDegree(String degree) {
        Long count = studentRepository.countByDegree(degree);

        return "Number of " + degree + "'s student : " + count;
    }

    @Transactional
    public String registerStudent(String name, String email, String graduation, String degree) {
        if (studentRepository.existsByEmail(email)) {
            return "Already registered";
        }

        Students newStudents = new Students(name, email, graduation, degree);

        studentRepository.save(newStudents);

        return "Registration successful";
    }
}