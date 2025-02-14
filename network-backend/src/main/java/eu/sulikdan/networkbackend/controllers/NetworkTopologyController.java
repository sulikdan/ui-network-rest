package eu.sulikdan.networkbackend.controllers;

import eu.sulikdan.networkbackend.services.NetworkTopologyService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("topologies")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NetworkTopologyController {

    NetworkTopologyService networkTopologyService;





}
