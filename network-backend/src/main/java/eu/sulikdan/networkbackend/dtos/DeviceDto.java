package eu.sulikdan.networkbackend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.sulikdan.networkbackend.entities.DeviceType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {

    @NotNull
    String macAddress;

    @NotNull
    DeviceType deviceType;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String uplinkMacAddress;
}
