package com.arvind.robotfactory.service;

import com.arvind.robotfactory.dto.OrderComponents;
import com.arvind.robotfactory.parts.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        this.orderNumber = 1;
    }

    private InventoryService inventoryService;

    private volatile int orderNumber;

    public Robot createRobot(OrderComponents components) {
        Robot robot = null;
        Arms arms = components.getArms().get(0);
        Face face = components.getFaces().get(0);
        Material material = components.getMaterials().get(0);
        Mobility mobility = components.getMobilities().get(0);
        robot = checkAvailabilityAndCreateRobot(arms, face, material, mobility);
        if(robot != null){
            logger.info("Created an robot with UUID {} for orderId -> {}", robot.getUuid() , robot.getOrderNumber());
        } else{
            logger.error("Required components are not available in the inventory, cannot create robot now");
        }
        return robot;
    }

    private synchronized Robot checkAvailabilityAndCreateRobot(Arms arms, Face face,
                                Material material, Mobility mobility) {

        EnumMap<Component, Integer> componentAvailability = inventoryService.getComponentAvailability();
        Component armsType = Component.valueOf(arms.getComponentType());
        Component faceType = Component.valueOf(face.getComponentType());
        Component materialType = Component.valueOf(material.getComponentType());
        Component mobilityType = Component.valueOf(mobility.getComponentType());
        Robot robot = null;
        if(componentAvailability.get(armsType) >0 && componentAvailability.get(faceType)>0 &&
                componentAvailability.get(materialType)>0 && componentAvailability.get(mobilityType)>0) {
            robot = new Robot(this.orderNumber, arms, face, material, mobility);
            componentAvailability.put(armsType, componentAvailability.get(armsType) -1);
            componentAvailability.put(faceType, componentAvailability.get(faceType) - 1);
            componentAvailability.put(materialType, componentAvailability.get(materialType) - 1);
            componentAvailability.put(mobilityType, componentAvailability.get(mobilityType) - 1);
            this.orderNumber++;
        }
        return robot;
    }

}
