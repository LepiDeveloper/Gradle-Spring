package be.kdg.programming3.spaceMissions.repository.jdbc;

import be.kdg.programming3.spaceMissions.domain.LaunchSite;
import be.kdg.programming3.spaceMissions.domain.Rocket;
import be.kdg.programming3.spaceMissions.repository.LaunchSiteRepository;
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
public class LaunchSiteJdbcRepository implements LaunchSiteRepository {

    private final JdbcTemplate jdbcTemplate;

    public LaunchSiteJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private LaunchSite mapLaunchSite(ResultSet rs, int rowNum) throws SQLException {
        LaunchSite launchSite = new LaunchSite();
        launchSite.setSiteId(rs.getInt("launch_site_id"));
        launchSite.setSiteName(rs.getString("site_name"));
        launchSite.setLocation(rs.getString("location"));
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
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::mapLaunchSite, id));

    }

    @Override
    public LaunchSite saveLaunchSite(LaunchSite launchSite) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("launch_sites")
                .usingGeneratedKeyColumns("launch_site_id");

        Map<String, Object> params = Map.of(
                "site_name", launchSite.getSiteName(),
                "location", launchSite.getLocation()
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
        jdbcTemplate.update("DELETE FROM launch_sites WHERE launch_site_id = ?", id);
    }
}
