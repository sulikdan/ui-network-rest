package eu.sulikdan.networkbackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.sulikdan.networkbackend.entities.DeviceType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class DeviceDto {

    @NotNull
    String macAddress;

    @NotNull
    DeviceType deviceType;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String uplinkMacAddress;
}
