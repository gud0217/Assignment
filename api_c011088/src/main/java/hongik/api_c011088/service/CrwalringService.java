package hongik.api_c011088.service;

import hongik.api_c011088.domain.Students;
import hongik.api_c011088.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrwalringService {

    private final StudentRepository studentRepository;

    public void crawling() {
        String url = "https://apl.hongik.ac.kr/lecture/dbms";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println("크롤링 문제 발생");
            throw new RuntimeException(e);
        }

        // 각 섹션의 컨테이너를 선택
        Element phdSection = doc.selectFirst("[id=h.cwxa41cyxn28]");
        Element masterSection = doc.selectFirst("[id=h.xrleu3h82rn1]");
        Element undergradSection = doc.selectFirst("[id=h.kfl1x21a81ct]");

        // 각 섹션에서 학생 정보 추출
        List<Students> phdStudents = extractStudents(phdSection, "PhD");
        List<Students> masterStudents = extractStudents(masterSection, "Master");
        List<Students> undergradStudents = extractStudents(undergradSection, "Undergraduate");

        // 모든 학생 정보 저장
        studentRepository.saveAll(phdStudents);
        studentRepository.saveAll(masterStudents);
        studentRepository.saveAll(undergradStudents);
    }

    private List<Students> extractStudents(Element sectionHeader, String degree) {
        List<Students> students = new ArrayList<>();

        // 섹션 헤더 다음의 첫 번째 <ul> 요소 선택
        Element ul = sectionHeader.nextElementSibling();
        while (ul != null && !ul.tagName().equals("ul")) {
            ul = ul.nextElementSibling();
        }

        // <ul> 내의 모든 <li> 요소에서 학생 정보 추출
        if (ul != null) {
            for (Element li : ul.select("li")) {
                Students student = parseStudentInfo(li, degree);
                students.add(student);
            }
        }

        return students;
    }

    private Students parseStudentInfo(Element li, String degree) {
        String[] parts = li.text().split(",");
        if (parts.length < 3) {
            return null;
        }

        String name = parts[0].trim();
        String email = extractEmailFromElement(li);
        String graduation = parts[parts.length - 1].trim();

        return new Students(name, email, degree, graduation);
    }

    private String extractEmailFromElement(Element li) {
        Element aTag = li.selectFirst("a[href^=mailto:]");
        if (aTag != null) {
            return aTag.attr("href").replace("mailto:", "").trim();
        } else {
            // a 태그가 없을 경우 텍스트에서 이메일 추출
            String[] parts = li.text().split(",");
            if (parts.length > 1) {
                return parts[1].trim();
            } else {
                return "";
            }
        }
    }
}
