package eu.sulikdan.networkbackend.entities;


import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimplifiedTreeNode {

    @NotNull
    String macAddress;

    String uplinkMacAddress;

}
