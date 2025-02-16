package eu.sulikdan.networkbackend.controllers;

import eu.sulikdan.networkbackend.exceptions.NetworkTreeException;
import eu.sulikdan.networkbackend.services.DeviceService;
import eu.sulikdan.networkbackend.services.NetworkTreeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NetworkTreeController.class)
class NetworkTreeControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    DeviceService deviceService;

    @MockitoBean
    NetworkTreeService networkTreeService;



    @Test
    void findTreeNode() throws Exception {
        when(deviceService.findById(any())).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/trees/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(NetworkTreeException.class, result.getResolvedException()))
                .andExpect(result -> assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains("Device with MacAddress ")));
    }


}
