package eu.sulikdan.networkbackend.controllers;

import eu.sulikdan.networkbackend.entities.TreeNode;
import eu.sulikdan.networkbackend.exceptions.NetworkTreeException;
import eu.sulikdan.networkbackend.services.DeviceService;
import eu.sulikdan.networkbackend.services.NetworkTreeService;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/trees")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NetworkTreeController {

    NetworkTreeService networkTreeService;
    DeviceService deviceService;

    @GetMapping("/{macAddress}")
    @ResponseStatus(HttpStatus.OK)
    TreeNode findTreeNode(@PathVariable @NotBlank String macAddress) {
        log.info("Getting trees from root mac address {}", macAddress);
        deviceService.findById(macAddress).orElseThrow(() -> new NetworkTreeException("Device with MacAddress " + macAddress + " not found."));
        return networkTreeService.findTreeStartingByMacAddress(macAddress);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<TreeNode> findAllTreeNodes() {
        log.info("Getting all trees from root");
        return networkTreeService.findAllNetworkTrees();
    }


}
