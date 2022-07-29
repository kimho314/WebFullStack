package com.example.responsebodyadvice.service;

import com.example.responsebodyadvice.constants.ResponseCode;
import com.example.responsebodyadvice.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HomeService {

    public String getHomeString(Boolean flag) {
        if (!flag) {
            throw new ServiceException(ResponseCode.COMMON_BAD_REQUEST, ResponseCode.COMMON_BAD_REQUEST.name());
        }

        return "welcome home";
    }
}
