package com.arvind.robotfactory.service;

import com.arvind.robotfactory.parts.Component;
import org.springframework.stereotype.Service;

import java.util.EnumMap;

@Service
public class InventoryService {

    private EnumMap<Component, Integer> componentAvailability;

    public InventoryService() {
        componentAvailability = new EnumMap<>(Component.class);
        componentAvailability.put(Component.ARMS_GRIPPERS, 3);
        componentAvailability.put(Component.ARMS_HANDS, 1);
        componentAvailability.put(Component.FACE_HUMANOID, 9);
        componentAvailability.put(Component.FACE_LCD, 7);
        componentAvailability.put(Component.FACE_STEAMPUNK, 0);
        componentAvailability.put(Component.MOBILITY_LEGS, 15);
        componentAvailability.put(Component.MOBILITY_TRACKS, 7);
        componentAvailability.put(Component.MOBILITY_WHEELS, 2);
        componentAvailability.put(Component.MATERIAL_BIOPLASTIC, 92);
        componentAvailability.put(Component.MATERIAL_METALIC, 15);
    }

    public EnumMap<Component, Integer> getComponentAvailability() {
        return componentAvailability;
    }

    public void setComponentAvailability(EnumMap<Component, Integer> componentAvailability) {
        this.componentAvailability = componentAvailability;
    }
}
