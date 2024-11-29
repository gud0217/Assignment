package hongik.api_c011088.repository;

import hongik.api_c011088.domain.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Students, Long> {

    //이름으로 학생 조회
    List<Students> findByName(String name);

    //학위 별 학생 수 조회
    Long countByDegree(String degree);

    //이메일로 중복 확인
    boolean existsByEmail(String email);
}
