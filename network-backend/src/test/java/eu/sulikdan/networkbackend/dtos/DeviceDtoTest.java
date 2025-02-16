package eu.sulikdan.networkbackend.dtos;

import eu.sulikdan.networkbackend.entities.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDtoTest {

    String macAddress;

    DeviceType deviceType;

    String uplinkMacAddress;
}
