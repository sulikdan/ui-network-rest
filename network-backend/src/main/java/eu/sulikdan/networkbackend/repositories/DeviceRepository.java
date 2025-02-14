package eu.sulikdan.networkbackend.repositories;

import eu.sulikdan.networkbackend.entities.Device;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceRepository extends CrudRepository<Device, String> {

    boolean existsDeviceByMacAddress(@NotNull String macAddress);

    Device getDeviceByMacAddress(@NotNull String macAddress);

    List<Device> getAllDevicesByOrderByDeviceTypeAsc();
}
