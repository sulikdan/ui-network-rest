package eu.sulikdan.networkbackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne
    Device uplink;
}
