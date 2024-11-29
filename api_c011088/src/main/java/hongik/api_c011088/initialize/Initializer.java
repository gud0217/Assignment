package hongik.api_c011088.initialize;

import hongik.api_c011088.service.CrwalringService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Initializer {
    private final CrwalringService crwalringService;

    @PostConstruct
    public void init() {
        crwalringService.crawling();
    }
}
