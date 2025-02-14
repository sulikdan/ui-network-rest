package eu.sulikdan.networkbackend.services;

import eu.sulikdan.networkbackend.entities.Node;

import java.util.List;

public interface NetworkTopologyService {

    List<Node> getAllNetworkTopologies();

    Node getTopologyStartingByMacAddress(String macAddress);

}
