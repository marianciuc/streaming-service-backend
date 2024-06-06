package zut.wi.streamingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zut.wi.streamingservice.model.UserSettings;

import java.util.UUID;

public interface SettingsRepository extends JpaRepository<UserSettings, UUID> {

}
