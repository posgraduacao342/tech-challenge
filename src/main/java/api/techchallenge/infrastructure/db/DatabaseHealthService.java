package api.techchallenge.infrastructure.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DatabaseHealthService {

    private final JdbcTemplate jdbcTemplate;

    public boolean isDatabaseUp() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
