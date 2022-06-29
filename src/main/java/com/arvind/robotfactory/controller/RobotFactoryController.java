package com.arvind.robotfactory.controller;

import com.arvind.robotfactory.model.OrderInput;
import com.arvind.robotfactory.dto.OrderComponents;
import com.arvind.robotfactory.model.RobotFactoryOrderResponse;
import com.arvind.robotfactory.model.ValidationOutput;
import com.arvind.robotfactory.model.OrderStatus;
import com.arvind.robotfactory.parts.Component;
import com.arvind.robotfactory.parts.Robot;
import com.arvind.robotfactory.service.InventoryService;
import com.arvind.robotfactory.service.OrderService;
import com.arvind.robotfactory.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EnumMap;

@RestController
public class RobotFactoryController {

    private static final Logger logger = LoggerFactory.getLogger(RobotFactoryController.class);

    @Autowired
    private ValidationService validationService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private InventoryService inventoryService;


    @GetMapping("/health")
    public String health() {
        return "Server health check successful";
    }


    @GetMapping(value = "/available/components", produces = MediaType.APPLICATION_JSON_VALUE)
    public EnumMap<Component, Integer> getAvailableComponents() {
        return inventoryService.getComponentAvailability();
    }


    @PostMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RobotFactoryOrderResponse> createOrder(@RequestBody OrderInput orderInput) {
        logger.info("OrderInput: {}", orderInput.getParts());
        OrderComponents orderComponents = new OrderComponents(orderInput);
        ValidationOutput validationOutput = validationService.validateInput(orderComponents);
        if(validationOutput.getStatus() == OrderStatus.BAD_REQUEST) {
            return new ResponseEntity<>(new RobotFactoryOrderResponse(validationOutput), HttpStatus.BAD_REQUEST);
        }else if(validationOutput.getStatus() == OrderStatus.UNPROCESSABLE_ENTITY) {
            return new ResponseEntity<>(new RobotFactoryOrderResponse(validationOutput), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        logger.error("Input is valid, checking availability for creating order");
        Robot robot = orderService.createRobot(orderComponents);
        if(robot != null){
            return new ResponseEntity<>(
                    new RobotFactoryOrderResponse("Required components are not present, cannot create robot",
                            OrderStatus.ORDER_CREATION_FAILED), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(new RobotFactoryOrderResponse(robot.getOrderNumber(), robot.getCost(), OrderStatus.ORDER_CREATED), HttpStatus.CREATED);
    }

}
