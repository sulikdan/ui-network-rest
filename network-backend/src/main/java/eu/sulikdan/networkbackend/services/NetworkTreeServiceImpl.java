package eu.sulikdan.networkbackend.services;

import eu.sulikdan.networkbackend.entities.SimplifiedTreeNode;
import eu.sulikdan.networkbackend.entities.TreeNode;
import eu.sulikdan.networkbackend.repositories.DeviceRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class NetworkTreeServiceImpl implements NetworkTreeService {

    DeviceRepository deviceRepository;

    @Override
    public List<TreeNode> findAllNetworkTrees() {

        List<TreeNode> foundTopologies = new ArrayList<>();

        // TODO possible parallel stream - if needed
        deviceRepository.findAllByUplinkMacAddressIsNull()
                .forEach(rootDevice -> foundTopologies.add(
                        this.findTreeStartingByMacAddress(rootDevice.getMacAddress()
                        )
                ));


        return foundTopologies;
    }

    @Override
    public TreeNode findTreeStartingByMacAddress(String macAddress) {

        List<SimplifiedTreeNode> simplifiedTreeNodes = deviceRepository.findSimplifiedNodeTopologyFromRoot(macAddress);

        return convertToTreeNode(simplifiedTreeNodes, macAddress);

    }


    private TreeNode convertToTreeNode(List<SimplifiedTreeNode> list, String rootMacAddress) {
        Map<String, TreeNode> nodeMap = new HashMap<>();

        list.forEach(simpleNode -> {
            TreeNode aTreeNode = getTreeNodeOrCreateAndAddItToMap(simpleNode.getMacAddress(), nodeMap);

            if (simpleNode.getUplinkMacAddress() != null) {
                TreeNode parentTreeNode = getTreeNodeOrCreateAndAddItToMap(simpleNode.getUplinkMacAddress(), nodeMap);
                parentTreeNode.getDeviceList().add(aTreeNode);
            }

        });

        return nodeMap.get(rootMacAddress);
    }

    private TreeNode getTreeNodeOrCreateAndAddItToMap(String macAddress, Map<String, TreeNode> nodeMap) {
        if (nodeMap.containsKey(macAddress)) {
            return nodeMap.get(macAddress);
        } else {
            TreeNode aTreeNode = new TreeNode(macAddress);
            nodeMap.put(macAddress, aTreeNode);
            return aTreeNode;
        }
    }
}
