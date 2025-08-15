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
import org.springframework.dao.EmptyResultDataAccessException;

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

    private List<Mission> findMissionsForRocket(int rocketId) {
        String sql = """
            SELECT m.mission_id, m.mission_name, m.mission_objective, m.launch_date,
                   m.mission_type, m.crew_onboard, m.is_success, m.image_file_name,
                   m.launch_site_id,
                   ls.site_name, ls.location, ls.image_file_name AS ls_image
            FROM missions m
            JOIN mission_rocket mr ON m.mission_id = mr.mission_id
            JOIN launch_sites ls ON m.launch_site_id = ls.launch_site_id
            WHERE mr.rocket_id = ?
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Mission mission = new Mission();
            mission.setMissionId(rs.getInt("mission_id"));
            mission.setMissionName(rs.getString("mission_name"));
            mission.setMissionObjective(rs.getString("mission_objective"));
            mission.setLaunchDate(rs.getDate("launch_date").toLocalDate());
            mission.setMissionType(MissionType.valueOf(rs.getString("mission_type")));

            // Handle nullable crew_onboard properly
            Integer crewOnboard = (Integer) rs.getObject("crew_onboard");
            mission.setCrewOnboard(Optional.ofNullable(crewOnboard));

            mission.setSuccess(rs.getBoolean("is_success"));
            mission.setImageFileName(rs.getString("image_file_name"));

            LaunchSite site = new LaunchSite(
                    rs.getInt("launch_site_id"),
                    rs.getString("site_name"),
                    rs.getString("location"),
                    rs.getString("ls_image")
            );
            mission.setLaunchSite(site);
            return mission;
        }, rocketId);
    }

    private Rocket mapRocket(ResultSet rs, int rowNum) throws SQLException {
        Rocket rocket = new Rocket();
        rocket.setRocketId(rs.getInt("rocket_id"));
        rocket.setRocketName(rs.getString("rocket_name"));
        rocket.setLaunchCapacity(rs.getDouble("payload_capacity")); // Changed to getDouble
        rocket.setManufacturer(rs.getString("manufacturer"));
        rocket.setImageFileName(rs.getString("image_file_name"));
        rocket.setMissions(findMissionsForRocket(rocket.getRocketId()));
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
        try {
            Rocket rocket = jdbcTemplate.queryForObject(sql, this::mapRocket, id);
            return Optional.ofNullable(rocket);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

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
        jdbcTemplate.update("DELETE FROM mission_rocket WHERE rocket_id = ?", id);
        jdbcTemplate.update("DELETE FROM rockets WHERE rocket_id = ?", id);
    }

    @Override
    public List<Rocket> findRocketsByLaunchCapacityGreaterThan(double capacity) {
        String sql = "SELECT * FROM rockets WHERE payload_capacity > ?";
        return jdbcTemplate.query(sql, this::mapRocket, capacity);
    }

    @Override
    public List<Rocket> findRocketsByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }
        String placeholders = String.join(",", ids.stream().map(id -> "?").toList());
        String sql = "SELECT * FROM rockets WHERE rocket_id IN (" + placeholders + ")";
        return jdbcTemplate.query(sql, this::mapRocket, ids.toArray());
    }
}
