package hongik.api_c011088.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String degree;
    private String graduation;

    public Students(String name, String email, String degree, String graduation) {
        this.name = name;
        this.email = email;
        this.degree = degree;
        this.graduation = graduation;
    }
}
