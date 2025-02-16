package eu.sulikdan.networkbackend.repositories;

import eu.sulikdan.networkbackend.entities.Device;
import eu.sulikdan.networkbackend.entities.SimplifiedTreeNode;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceRepository extends CrudRepository<Device, String> {

    boolean existsDeviceByMacAddress(@NotNull String macAddress);

    List<Device> findAllByOrderByDeviceTypeAsc();

    /**
     * Generate root list of deployment network
     *
     * @return list of root devices
     */
    List<Device> findAllByUplinkMacAddressIsNull();

    /**
     * Query method that gets whole tree of specified root-node
     * @param rootMacAddress mac-address of root, from where it searches children
     * @return List of {@link SimplifiedTreeNode} that contains self and link to parent
     */
    @Query(value = """
            WITH RECURSIVE topology_tree(mac_address, uplink_mac_address ) AS ( 
                 ( SELECT d.mac_address, d.uplink_mac_address FROM device d WHERE d.mac_Address = ?1 ) 
                UNION ALL 
                 (SELECT d.mac_address, d.uplink_mac_address 
                FROM device d INNER JOIN topology_tree t ON d.uplink_mac_address = t.mac_Address) 
            )
             SELECT mac_address as macAddress, uplink_mac_address as uplinkMacAddress 
            FROM topology_tree
            """
            , nativeQuery = true)
    List<SimplifiedTreeNode> findSimplifiedNodeTreeFromRoot(@NotNull String rootMacAddress);
}
