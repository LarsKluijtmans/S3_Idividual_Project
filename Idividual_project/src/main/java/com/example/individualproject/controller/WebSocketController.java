package com.example.individualproject.controller;

import com.example.individualproject.dto.products.NewProductDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/NewApp")
    @SendTo("/topic/newApps")
    public NewProductDTO newApplication(@Payload final NewProductDTO newProductDTO){
        return newProductDTO;
    }
}
