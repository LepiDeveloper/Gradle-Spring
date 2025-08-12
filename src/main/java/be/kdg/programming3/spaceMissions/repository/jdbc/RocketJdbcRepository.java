package be.kdg.programming3.spaceMissions.repository.jdbc;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.domain.MissionType;
import be.kdg.programming3.spaceMissions.domain.Rocket;
import be.kdg.programming3.spaceMissions.repository.RocketRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class RocketJdbcRepository implements RocketRepository {

    private final JdbcTemplate jdbcTemplate;

    public RocketJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Rocket mapRocket(ResultSet rs, int rowNum) throws SQLException {
        Rocket rocket = new Rocket();
        rocket.setRocketId(rs.getInt("rocket_id"));
        rocket.setRocketName(rs.getString("rocket_name"));
        rocket.setLaunchCapacity(rs.getInt("payload_capacity"));
        rocket.setManufacturer(rs.getString("manufacturer"));
        rocket.setImageFileName(rs.getString("image_file_name"));
        return rocket;
    }

    @Override
    public List<Rocket> findAllRockets() {
        String sql = "SELECT * FROM rockets";
        return jdbcTemplate.query(sql, this::mapRocket);
    }

    @Override
    public Optional<Rocket> findRocketById(int id) {
        String sql = "SELECT * FROM rockets WHERE rocket_id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::mapRocket, id));

    }

    @Override
    public Rocket saveRocket(Rocket rocket) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("rockets")
                .usingGeneratedKeyColumns("rocket_id");

        Map<String, Object> params = Map.of(
                "rocket_name", rocket.getRocketName(),
                "payload_capacity", rocket.getLaunchCapacity(),
                "manufacturer", rocket.getManufacturer(),
                "image_file_name", rocket.getImageFileName()
        );

        int id = insert.executeAndReturnKey(params).intValue();
        rocket.setRocketId(id);
        return rocket;
    }

    @Override
    public Rocket updateRocket(Rocket rocket) {
        String sql = """
                UPDATE rockets SET rocket_name=?, payload_capacity=?, manufacturer=?, image_file_name=?
                WHERE rocket_id=?
                """;
        jdbcTemplate.update(sql,
                rocket.getRocketName(),
                rocket.getLaunchCapacity(),
                rocket.getManufacturer(),
                rocket.getImageFileName(),
                rocket.getRocketId()
        );
        return rocket;
    }

    @Override
    public void deleteRocketById(int id) {
        jdbcTemplate.update("DELETE FROM rockets WHERE rocket_id = ?", id);
    }

    @Override
    public List<Rocket> findRocketsByLaunchCapacityGreaterThan(double capacity) {
        String sql = "SELECT * FROM rockets WHERE payload_capacity > ?";
        return jdbcTemplate.query(sql, this::mapRocket, capacity);
    }
}
