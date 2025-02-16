package eu.sulikdan.networkbackend.entities;


import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TreeNode {

    @NotNull
    String macAddress;

    List<TreeNode> deviceList;

    public TreeNode(String macAddress) {
        this.macAddress = macAddress;
        deviceList = new ArrayList<>();
    }
}
