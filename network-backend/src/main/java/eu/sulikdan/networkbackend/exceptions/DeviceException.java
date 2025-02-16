package eu.sulikdan.networkbackend.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeviceException extends RuntimeException {

    public DeviceException(String message) {
        super(message);
        log.error("Handler error:\n{}", message);
    }

}
