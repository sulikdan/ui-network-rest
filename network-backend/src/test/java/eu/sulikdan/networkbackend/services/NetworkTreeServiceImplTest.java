package eu.sulikdan.networkbackend.services;

import eu.sulikdan.networkbackend.entities.Device;
import eu.sulikdan.networkbackend.entities.DeviceType;
import eu.sulikdan.networkbackend.entities.SimplifiedTreeNode;
import eu.sulikdan.networkbackend.entities.TreeNode;
import eu.sulikdan.networkbackend.repositories.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NetworkTreeServiceImplTest {

    @Mock
    DeviceRepository deviceRepository;

    @InjectMocks
    NetworkTreeServiceImpl networkTreeService;

    SimplifiedTreeNode simplifiedTreeNode1;
    SimplifiedTreeNode simplifiedTreeNode2;
    SimplifiedTreeNode simplifiedTreeNode3;
    SimplifiedTreeNode simplifiedTreeNode4;

    List<SimplifiedTreeNode> simplifiedTreeNodeList1;

    SimplifiedTreeNode simplifiedTreeNode11;

    TreeNode aTreeNode1;
    TreeNode aTreeNode2;
    TreeNode aTreeNode3;
    TreeNode aTreeNode4;

    TreeNode aTreeNode11;

    @BeforeEach
    void setUp() {
        simplifiedTreeNode1 = new SimplifiedTreeNode("1", null);
        simplifiedTreeNode2 = new SimplifiedTreeNode("2", "1");
        simplifiedTreeNode3 = new SimplifiedTreeNode("3", "2");
        simplifiedTreeNode4 = new SimplifiedTreeNode("4", "2");

        simplifiedTreeNodeList1 = new ArrayList<>();
        simplifiedTreeNodeList1.add(simplifiedTreeNode1);
        simplifiedTreeNodeList1.add(simplifiedTreeNode2);
        simplifiedTreeNodeList1.add(simplifiedTreeNode3);
        simplifiedTreeNodeList1.add(simplifiedTreeNode4);

        simplifiedTreeNode11 = new SimplifiedTreeNode("11", null);

        aTreeNode1 = new TreeNode(simplifiedTreeNode1.getMacAddress());
        aTreeNode2 = new TreeNode(simplifiedTreeNode2.getMacAddress());
        aTreeNode3 = new TreeNode(simplifiedTreeNode3.getMacAddress());
        aTreeNode4 = new TreeNode(simplifiedTreeNode4.getMacAddress());

        aTreeNode1.getDeviceList().add(aTreeNode2);
        aTreeNode2.getDeviceList().add(aTreeNode3);
        aTreeNode2.getDeviceList().add(aTreeNode4);

        aTreeNode11 = new TreeNode(simplifiedTreeNode11.getMacAddress());
    }


    @Test
    void findTreeStartingByMacAddress() {

        when(deviceRepository.findSimplifiedNodeTreeFromRoot("1")).thenReturn(simplifiedTreeNodeList1);


        var result = networkTreeService.findTreeStartingByMacAddress(simplifiedTreeNode1.getMacAddress());


        assertNotNull(result);
        verify(deviceRepository, times(1)).findSimplifiedNodeTreeFromRoot(any());

        assertEquals(aTreeNode1.getDeviceList().size(), result.getDeviceList().size());
        assertEquals(aTreeNode1.getMacAddress(), result.getMacAddress());
        assertEquals(aTreeNode2.getMacAddress(), result.getDeviceList().getFirst().getMacAddress());
        assertEquals(aTreeNode3.getMacAddress(), result.getDeviceList().getFirst().getDeviceList().getFirst().getMacAddress());
    }


    @Test
    void findAllNetworkTrees() {
        Device device1 = new Device("1", DeviceType.SWITCH, null);
        Device device11 = new Device("11", DeviceType.ACCESS_POINT, null);

        when(deviceRepository.findAllByUplinkMacAddressIsNull()).thenReturn(List.of(device1, device11));
        when(deviceRepository.findSimplifiedNodeTreeFromRoot("1")).thenReturn(simplifiedTreeNodeList1);
        when(deviceRepository.findSimplifiedNodeTreeFromRoot("11")).thenReturn(List.of(simplifiedTreeNode11));


        var result = networkTreeService.findAllNetworkTrees();


        assertNotNull(result);
        verify(deviceRepository, times(1)).findAllByUplinkMacAddressIsNull();
        verify(deviceRepository, times(2)).findSimplifiedNodeTreeFromRoot(any());

        assertEquals(2, result.size());
        assertEquals(aTreeNode1.getDeviceList().size(), result.getFirst().getDeviceList().size());
        assertEquals(aTreeNode11.getDeviceList().size(), result.get(1).getDeviceList().size());
    }

}
