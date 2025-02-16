package eu.sulikdan.networkbackend.mappers;

import eu.sulikdan.networkbackend.configs.MapstructConfig;
import eu.sulikdan.networkbackend.dtos.DeviceDto;
import eu.sulikdan.networkbackend.entities.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface DeviceMapper {

    @Mapping(source = "uplinkMacAddress", target = "uplinkMacAddress")
    DeviceDto toDeviceDto(Device device);
}
