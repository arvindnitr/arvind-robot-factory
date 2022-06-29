package com.arvind.robotfactory.parts;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Material implements ComponentType {

    BIOPLASTIC("I", 90.12f, "Material Bioplastic"),
    METALIC("J", 82.31f, "Material Metallic");

    String code;
    float price;
    String displayName;

    Material(String code, float price, String displayName){
        this.code = code;
        this.price = price;
        this.displayName = displayName;
    }

    public static ComponentType getComponentTypeFromCode(String code) {
        return Arrays.stream(Material.values()).filter(material -> material.code == code).findFirst().get();
    }

    public static List<String> getAllCodes() {
        return Arrays.stream(Material.values()).map(material -> material.getCode()).collect(Collectors.toList());
    }

    public String getCode() {
        return code;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String getComponentType() {
        return "MATERIAL"+"_"+this.name();
    }

    public String getDisplayName() {
        return displayName;
    }
}
