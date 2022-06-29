package com.arvind.robotfactory.parts;

public enum Component {

    FACE_HUMANOID(Face.HUMANOID),
    FACE_LCD(Face.LCD),
    FACE_STEAMPUNK(Face.STEAMPUNK),
    ARMS_HANDS(Arms.HANDS),
    ARMS_GRIPPERS(Arms.GRIPPERS),
    MOBILITY_WHEELS(Mobility.WHEELS),
    MOBILITY_LEGS(Mobility.LEGS),
    MOBILITY_TRACKS(Mobility.TRACKS),
    MATERIAL_BIOPLASTIC(Material.BIOPLASTIC),
    MATERIAL_METALIC(Material.METALIC);

    Component(ComponentType type){
        this.type = type;
    }

    ComponentType type;



}
