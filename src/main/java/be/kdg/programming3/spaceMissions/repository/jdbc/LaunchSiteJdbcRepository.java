package be.kdg.programming3.spaceMissions.repository.jdbc;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import be.kdg.programming3.spaceMissions.domain.Mission;
import be.kdg.programming3.spaceMissions.domain.MissionType;
import be.kdg.programming3.spaceMissions.domain.Rocket;
import be.kdg.programming3.spaceMissions.repository.LaunchSiteRepository;
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
public class LaunchSiteJdbcRepository implements LaunchSiteRepository {

    private final JdbcTemplate jdbcTemplate;

    public LaunchSiteJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<Mission> findMissionsForLaunchSite(int siteId) {
        String sql = """
              SELECT m.mission_id, m.mission_name, m.mission_objective, m.launch_date,
                         m.mission_type, m.crew_onboard, m.is_success, m.image_file_name,
                         m.launch_site_id,
                         ls.site_name, ls.location, ls.image_file_name AS ls_image
                  FROM missions m
                  JOIN launch_sites ls ON m.launch_site_id = ls.launch_site_id
                  WHERE m.launch_site_id = ?
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
            return mission;
        }, siteId);
    }

    private LaunchSite mapLaunchSite(ResultSet rs, int rowNum) throws SQLException {
        LaunchSite launchSite = new LaunchSite();
        launchSite.setSiteId(rs.getInt("launch_site_id"));
        launchSite.setSiteName(rs.getString("site_name"));
        launchSite.setLocation(rs.getString("location"));
        launchSite.setImageFileName(rs.getString("image_file_name"));
        launchSite.setMissions(findMissionsForLaunchSite(launchSite.getSiteId()));
        return launchSite;
    }

    @Override
    public List<LaunchSite> findAllLaunchSites() {
        String sql = "SELECT * FROM launch_sites";
        return jdbcTemplate.query(sql, this::mapLaunchSite);
    }

    @Override
    public Optional<LaunchSite> findLaunchSiteById(int id) {
        String sql = "SELECT * FROM launch_sites WHERE launch_site_id = ?";
        try {
            LaunchSite launchSite = jdbcTemplate.queryForObject(sql, this::mapLaunchSite, id);
            return Optional.ofNullable(launchSite);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

    @Override
    public LaunchSite saveLaunchSite(LaunchSite launchSite) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("launch_sites")
                .usingGeneratedKeyColumns("launch_site_id");

        Map<String, Object> params = Map.of(
                "site_name", launchSite.getSiteName(),
                "location", launchSite.getLocation(),
                "image_file_name", launchSite.getImageFileName() != null ? launchSite.getImageFileName() : "launchSitePlaceholder.png"
        );

        int id = insert.executeAndReturnKey(params).intValue();
        launchSite.setSiteId(id);
        return launchSite;
    }

    @Override
    public LaunchSite updateLaunchSite(LaunchSite launchSite) {
        String sql = """
                UPDATE launch_sites SET site_name=?, location=?
                WHERE launch_site_id=?
                """;
        jdbcTemplate.update(sql,
                launchSite.getSiteName(),
                launchSite.getLocation()
        );
        return launchSite;
    }

    @Override
    public void deleteLaunchSiteById(int id) {
        jdbcTemplate.update("DELETE FROM mission_rocket WHERE mission_id IN (SELECT mission_id FROM missions WHERE launch_site_id = ?)", id);
        jdbcTemplate.update("DELETE FROM missions WHERE launch_site_id = ?", id);
        jdbcTemplate.update("DELETE FROM launch_sites WHERE launch_site_id = ?", id);
    }
}
