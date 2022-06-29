package com.arvind.robotfactory.parts;

import java.text.DecimalFormat;
import java.util.UUID;

public class Robot {

    private static final DecimalFormat df = new DecimalFormat("0.00");


    public Robot(int orderNumber, Arms armsType, Face faceType, Material materialType, Mobility mobilityType) {
        this.uuid = UUID.randomUUID().toString();
        this.orderNumber = orderNumber;
        this.armsType = armsType;
        this.faceType = faceType;
        this.materialType = materialType;
        this.mobilityType = mobilityType;
        this.cost = calculateRobotCost(armsType, faceType, materialType,mobilityType);
    }

    private String uuid;
    private int orderNumber;
    private Arms armsType;
    private Face faceType;
    private Material materialType;
    private Mobility mobilityType;
    private float cost;

    public Arms getArmsType() {
        return armsType;
    }

    public Face getFaceType() {
        return faceType;
    }

    public Material getMaterialType() {
        return materialType;
    }

    public Mobility getMobilityType() {
        return mobilityType;
    }

    public float getCost() {
        return cost;
    }

    public String getUuid() {
        return uuid;
    }


    public int getOrderNumber() {
        return orderNumber;
    }

    private float calculateRobotCost(Arms arms, Face face, Material material, Mobility mobility) {
        return Float.parseFloat(df.format(arms.getPrice() + face.getPrice() + material.getPrice() + mobility.getPrice()));
    }
}
