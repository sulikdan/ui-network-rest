package eu.sulikdan.networkbackend.entities;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Node {

    @NotNull
    String macAddress;
    List<Node> nodeList;

}
