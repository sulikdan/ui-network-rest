package eu.sulikdan.networkbackend.controllers;

import eu.sulikdan.networkbackend.entities.TreeNode;
import eu.sulikdan.networkbackend.repositories.DeviceRepository;
import eu.sulikdan.networkbackend.services.NetworkTreeService;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/topologies")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NetworkTopologyController {

    NetworkTreeService networkTreeService;
    DeviceRepository deviceRepository;

    @GetMapping("/{macAddress}")
    TreeNode findTopologyNode(@PathVariable @NotBlank String macAddress) {
        log.info("Getting topologies from root mac address {}", macAddress);
        // TODO verify that macAddress is correct
        return networkTreeService.getTreeStartingByMacAddress(macAddress);
    }

    @GetMapping
    List<TreeNode> findAllTopologyNodes() {
        log.info("Getting all topologies from root");
        return null;
    }



}
