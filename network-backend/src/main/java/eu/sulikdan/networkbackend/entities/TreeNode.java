package eu.sulikdan.networkbackend.entities;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TreeNode {

    @NotNull
    String macAddress;
//
//    // TODO delete not needed
//    @NotNull
//    @Enumerated(EnumType.ORDINAL)
//    DeviceType deviceType;
//
//    // TODO delete not needed
//    String uplink;

    List<TreeNode> deviceList;

    public TreeNode(String macAddress) {
        this.macAddress = macAddress;
        deviceList = new ArrayList<>();
    }
}
