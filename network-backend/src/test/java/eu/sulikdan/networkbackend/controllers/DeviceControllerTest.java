package eu.sulikdan.networkbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.sulikdan.networkbackend.dtos.DeviceDto;
import eu.sulikdan.networkbackend.dtos.DeviceDtoTest;
import eu.sulikdan.networkbackend.entities.Device;
import eu.sulikdan.networkbackend.entities.DeviceType;
import eu.sulikdan.networkbackend.exceptions.DeviceException;
import eu.sulikdan.networkbackend.mappers.DeviceMapper;
import eu.sulikdan.networkbackend.services.DeviceService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DeviceController.class)
class DeviceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    DeviceService deviceService;

    @MockitoBean
    DeviceMapper deviceMapper;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void createDevice() throws Exception {
        DeviceDtoTest deviceDtoInput = new DeviceDtoTest("1", DeviceType.SWITCH, null);
        DeviceDto deviceDto = new DeviceDto("1", DeviceType.SWITCH, null);

        when(deviceMapper.toDeviceDto(any())).thenReturn(deviceDto);

        this.mockMvc.perform(post("/devices").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deviceDtoInput)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(deviceDto)));
    }

    @Test
    void createDeviceEmptyBody() throws Exception {
        DeviceDto deviceDto = new DeviceDto("1", DeviceType.SWITCH, null);

        when(deviceMapper.toDeviceDto(any())).thenReturn(deviceDto);

        this.mockMvc.perform(post("/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }


    @Test
    void createDeviceIncorrectUplink() throws Exception {
        DeviceDtoTest deviceDtoInput = new DeviceDtoTest("1", DeviceType.SWITCH, "0");

        when(deviceService.findById(any())).thenReturn(Optional.empty());

        this.mockMvc.perform(post("/devices").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(deviceDtoInput)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(DeviceException.class, result.getResolvedException()))
                .andExpect(result -> assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains("No parent device found for uplink")));
    }


    @Test
    void findDeviceByMacAddressBadRequest() throws Exception {
        when(deviceService.findById(any())).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/devices/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(DeviceException.class, result.getResolvedException()))
                .andExpect(result -> assertTrue(Objects.requireNonNull(result.getResolvedException()).getMessage().contains("Device with MacAddress ")));

    }


    @Test
    void findDeviceByMacAddress() throws Exception {
        DeviceDto deviceDto = new DeviceDto("1", DeviceType.SWITCH, "0");
        Device device = new Device("1", DeviceType.SWITCH, "0");

        when(deviceService.findById(any())).thenReturn(Optional.of(device));
        when(deviceMapper.toDeviceDto(any())).thenReturn(deviceDto);

        this.mockMvc.perform(get("/devices/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(deviceDto)));

    }
}
