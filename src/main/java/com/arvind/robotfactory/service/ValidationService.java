package com.arvind.robotfactory.service;

import com.arvind.robotfactory.dto.OrderComponents;
import com.arvind.robotfactory.model.ValidationOutput;
import com.arvind.robotfactory.model.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    private static final Logger logger = LoggerFactory.getLogger(ValidationService.class);

    private final int NUMBER_OF_COMPONENTS = 4;

    public ValidationOutput validateInput(OrderComponents components){
        ValidationOutput output;

        if(components.getNumberOfParts() != NUMBER_OF_COMPONENTS ||
                components.getNumberOfComponents() != NUMBER_OF_COMPONENTS) {
            output = new ValidationOutput(OrderStatus.BAD_REQUEST,
                    "Number of parts in the input -> " + components.getNumberOfParts() +
                    "Number of valid components in the input -> " + components.getNumberOfComponents());
        }
        else if (components.getArms().size() != 1 || components.getFaces().size() != 1 ||
                components.getMaterials().size() != 1 || components.getMobilities().size() != 1){
            output = new ValidationOutput(OrderStatus.UNPROCESSABLE_ENTITY,
                    "Received " + components.getArms().size() +
                            " Arms components, " + components.getFaces().size() + " Face components, " + components.getMaterials().size() +
                            " Material components, " + components.getMobilities().size() + " Mobility components");
        }
        else {
            output = new ValidationOutput(OrderStatus.VALIDATION_SUCCESSFUL);
        }
        return output;
    }

}
