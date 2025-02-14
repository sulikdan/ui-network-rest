package eu.sulikdan.networkbackend.services;

import eu.sulikdan.networkbackend.entities.Node;
import eu.sulikdan.networkbackend.repositories.DeviceRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NetworkTopologyServiceDummyImpl implements NetworkTopologyService {

    DeviceRepository deviceRepository;

    @Override
    public List<Node> getAllNetworkTopologies() {
        // god saves us from recursive topology
        return List.of();
    }

    @Override
    public Node getTopologyStartingByMacAddress(String macAddress) {

        return null;
    }
}
