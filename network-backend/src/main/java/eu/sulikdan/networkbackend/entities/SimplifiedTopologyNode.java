package eu.sulikdan.networkbackend.entities;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimplifiedTopologyNode {

    @NotNull
    String macAddress;

    String uplinkMacAddress;

}
