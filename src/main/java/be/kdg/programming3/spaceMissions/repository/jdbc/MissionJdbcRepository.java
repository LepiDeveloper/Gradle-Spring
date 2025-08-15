package be.kdg.programming3.spaceMissions.repository.jdbc;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.domain.MissionType;
import be.kdg.programming3.spaceMissions.domain.Rocket;
import be.kdg.programming3.spaceMissions.repository.MissionRepository;
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
public class MissionJdbcRepository implements MissionRepository {

    private final JdbcTemplate jdbcTemplate;

    public MissionJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<Rocket> findRocketsForMission(int missionId) {
        String sql = """
        SELECT r.* FROM rockets r
        JOIN mission_rocket mr ON r.rocket_id = mr.rocket_id
        WHERE mr.mission_id = ?
        """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Rocket rocket = new Rocket();
            rocket.setRocketId(rs.getInt("rocket_id"));
            rocket.setRocketName(rs.getString("rocket_name"));
            rocket.setLaunchCapacity(rs.getDouble("payload_capacity"));
            rocket.setManufacturer(rs.getString("manufacturer"));
            rocket.setImageFileName(rs.getString("image_file_name"));
            return rocket;
        }, missionId);
    }

    private Mission mapMission(ResultSet rs, int rowId) throws SQLException {
        Mission mission = new Mission();
        mission.setMissionId(rs.getInt("mission_id"));
        mission.setMissionName(rs.getString("mission_name"));
        mission.setMissionObjective(rs.getString("mission_objective"));
        mission.setLaunchDate(rs.getDate("launch_date").toLocalDate());
        mission.setMissionType(MissionType.valueOf(rs.getString("mission_type")));

        Integer crewOnboard = (Integer) rs.getObject("crew_onboard");
        mission.setCrewOnboard(Optional.ofNullable(crewOnboard));

        mission.setSuccess(rs.getBoolean("is_success"));
        mission.setImageFileName(rs.getString("image_file_name"));

        LaunchSite site = new LaunchSite(
                rs.getInt("launch_site_id"),
                rs.getString("site_name"),
                rs.getString("location"),
                rs.getString("image_file_name")
        );
        mission.setLaunchSite(site);
        mission.setRockets(findRocketsForMission(mission.getMissionId()));
        return mission;
    }

    @Override
    public List<Mission> findAllMissions() {
        String sql = """
            SELECT m.mission_id, m.mission_name, m.mission_objective, m.launch_date,
                   m.mission_type, m.crew_onboard, m.is_success, m.image_file_name,
                   m.launch_site_id,
                   ls.site_name, ls.location, ls.image_file_name AS ls_image
            FROM missions m
            JOIN launch_sites ls ON m.launch_site_id = ls.launch_site_id
        """;
        return jdbcTemplate.query(sql, this::mapMission);
    }

    @Override
    public Optional<Mission> findMissionById(int id) {
        String sql = """
            SELECT m.mission_id, m.mission_name, m.mission_objective, m.launch_date,
                   m.mission_type, m.crew_onboard, m.is_success, m.image_file_name,
                   m.launch_site_id,
                   ls.site_name, ls.location, ls.image_file_name AS ls_image
            FROM missions m
            JOIN launch_sites ls ON m.launch_site_id = ls.launch_site_id
            WHERE m.mission_id = ?
        """;
        try {
            Mission mission = jdbcTemplate.queryForObject(sql, this::mapMission, id);
            return Optional.ofNullable(mission);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Mission saveMission(Mission mission) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("missions")
                .usingGeneratedKeyColumns("mission_id");

        Map<String, Object> params = Map.of(
                "mission_name", mission.getMissionName(),
                "mission_objective", mission.getMissionObjective(),
                "launch_date", mission.getLaunchDate(),
                "mission_type", mission.getMissionType().name(),
                "crew_onboard", mission.getCrewOnboard().orElse(null),
                "is_success", mission.isSuccess(),
                "image_file_name", mission.getImageFileName(),
                "launch_site_id", mission.getLaunchSite().getSiteId()
        );

        int id = insert.executeAndReturnKey(params).intValue();
        mission.setMissionId(id);

        for (Rocket rocket : mission.getRockets()) {
            jdbcTemplate.update("INSERT INTO mission_rocket (mission_id, rocket_id) VALUES (?, ?)",
                    mission.getMissionId(), rocket.getRocketId());
        }

        return mission;
    }

    @Override
    public Mission updateMission(Mission mission) {
        String sql = """
                UPDATE missions SET mission_name=?, mission_objective=?, launch_date=?, mission_type=?,
                crew_onboard=?, is_success=?, image_file_name=?, launch_site_id=? WHERE mission_id=?
                """;
        jdbcTemplate.update(sql,
                mission.getMissionName(),
                mission.getMissionObjective(),
                mission.getLaunchDate(),
                mission.getMissionType().name(),
                mission.getCrewOnboard().orElse(null),
                mission.isSuccess(),
                mission.getImageFileName(),
                mission.getLaunchSite().getSiteId(),
                mission.getMissionId()
        );
        jdbcTemplate.update("DELETE FROM mission_rocket WHERE mission_id = ?", mission.getMissionId());
        for (Rocket rocket : mission.getRockets()) {
            jdbcTemplate.update("INSERT INTO mission_rocket (mission_id, rocket_id) VALUES (?, ?)",
                    mission.getMissionId(), rocket.getRocketId());
        }

        return mission;
    }

    @Override
    public void deleteMissionById(int id) {
        jdbcTemplate.update("DELETE FROM mission_rocket WHERE mission_id = ?", id);
        jdbcTemplate.update("DELETE FROM missions WHERE mission_id = ?", id);
    }

    @Override
    public List<Mission> findMissionByMissionType(String missionType) {
        String sql = """
            SELECT m.mission_id, m.mission_name, m.mission_objective, m.launch_date,
                   m.mission_type, m.crew_onboard, m.is_success, m.image_file_name,
                   m.launch_site_id,
                   ls.site_name, ls.location, ls.image_file_name AS ls_image
            FROM missions m
            JOIN launch_sites ls ON m.launch_site_id = ls.launch_site_id
            WHERE LOWER(m.mission_type) = LOWER(?)
        """;
        return jdbcTemplate.query(sql, this::mapMission, missionType);
    }

    @Override
    public List<Mission> findMissionByLaunchSiteName(String siteName) {
        String sql = """
            SELECT m.mission_id, m.mission_name, m.mission_objective, m.launch_date,
                   m.mission_type, m.crew_onboard, m.is_success, m.image_file_name,
                   m.launch_site_id,
                   ls.site_name, ls.location, ls.image_file_name AS ls_image
            FROM missions m
            JOIN launch_sites ls ON m.launch_site_id = ls.launch_site_id
            WHERE LOWER(ls.site_name) LIKE LOWER(?)
        """;
        return jdbcTemplate.query(sql, this::mapMission, "%" + siteName + "%");
    }

}
