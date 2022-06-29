package com.arvind.robotfactory.dto;

import com.arvind.robotfactory.model.OrderInput;
import com.arvind.robotfactory.parts.Arms;
import com.arvind.robotfactory.parts.Face;
import com.arvind.robotfactory.parts.Material;
import com.arvind.robotfactory.parts.Mobility;

import java.util.List;
import java.util.stream.Collectors;

public class OrderComponents {

    private List<Arms> arms;
    private List<Face> faces;
    private List<Material> materials;
    private List<Mobility> mobilities;
    private int numberOfParts;
    private int numberOfComponents;

    public OrderComponents(OrderInput orderInput) {
        List<String> parts = orderInput.getParts();
        this.numberOfParts = parts.size();
        setArms(parts);
        setFaces(parts);
        setMaterials(parts);
        setMobility(parts);
    }

    private void setMobility(List<String> parts) {
        List<String> inputMobility = Mobility.getAllCodes().stream()
                .filter(parts::contains)
                .collect(Collectors.toList());

        this.mobilities = inputMobility.stream().
                map(mobility -> (Mobility) Mobility.getComponentTypeFromCode(mobility)).
                collect(Collectors.toList());
        this.numberOfComponents += this.mobilities.size();
    }

    private void setMaterials(List<String> parts) {
        List<String> inputMaterials = Material.getAllCodes().stream()
                .filter(parts::contains)
                .collect(Collectors.toList());
        this.materials = inputMaterials.stream().
                map(material -> (Material) Material.getComponentTypeFromCode(material)).
                collect(Collectors.toList());
        this.numberOfComponents += this.materials.size();
    }

    private void setFaces(List<String> parts) {
        List<String> inputFaces = Face.getAllCodes().stream()
                .filter(parts::contains)
                .collect(Collectors.toList());
        this.faces = inputFaces.stream().
                map(face -> (Face) Face.getComponentTypeFromCode(face)).
                collect(Collectors.toList());
        this.numberOfComponents += this.faces.size();
    }

    private void setArms(List<String> parts) {
        List<String> inputArms= Arms.getAllCodes().stream()
                .filter(parts::contains)
                .collect(Collectors.toList());
        this.arms = inputArms.stream().
                map(arms -> (Arms) Arms.getComponentTypeFromCode(arms)).
                collect(Collectors.toList());
        this.numberOfComponents += this.arms.size();
    }

    public List<Arms> getArms() {
        return arms;
    }

    public List<Face> getFaces() {
        return faces;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public List<Mobility> getMobilities() {
        return mobilities;
    }

    public int getNumberOfParts() {
        return numberOfParts;
    }

    public int getNumberOfComponents() {
        return numberOfComponents;
    }
}
