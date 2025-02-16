package eu.sulikdan.networkbackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Device {

//    TODO maybe use UUID instead of macAddress as identifier
//    UUID id;

    @Id
    @NotNull
    String macAddress;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    DeviceType deviceType;

    String uplinkMacAddress;
}
