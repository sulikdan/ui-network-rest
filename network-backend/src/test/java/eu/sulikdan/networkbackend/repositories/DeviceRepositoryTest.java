package eu.sulikdan.networkbackend.repositories;

import eu.sulikdan.networkbackend.entities.SimplifiedTreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class DeviceRepositoryTest {


    @Autowired
    DeviceRepository deviceRepository;

    @BeforeEach
    void setUp() {
    }


    @Test
    void findSimplifiedNodeTreeFromRoot() {
        final String ROOT_MAC_1 = "11-c0-f3-d4-ac-e0";

        List<SimplifiedTreeNode> foundRes = deviceRepository.findSimplifiedNodeTreeFromRoot(ROOT_MAC_1);

        assertNotNull(foundRes);
        assertEquals(6, foundRes.size());
        assertEquals(ROOT_MAC_1, foundRes.getFirst().getMacAddress());
        assertNull(foundRes.getFirst().getUplinkMacAddress());
        assertEquals(ROOT_MAC_1, foundRes.get(1).getUplinkMacAddress());
        assertEquals(ROOT_MAC_1, foundRes.get(2).getUplinkMacAddress());
    }
}
