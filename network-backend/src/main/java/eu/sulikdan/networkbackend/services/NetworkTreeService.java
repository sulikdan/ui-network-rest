package eu.sulikdan.networkbackend.services;

import eu.sulikdan.networkbackend.entities.TreeNode;

import java.util.List;

public interface NetworkTreeService {

    List<TreeNode> getAllNetworkTrees();

    TreeNode getTreeStartingByMacAddress(String macAddress);

}
