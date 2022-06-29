package com.arvind.robotfactory;

import com.arvind.robotfactory.model.OrderInput;
import com.arvind.robotfactory.dto.OrderComponents;
import com.arvind.robotfactory.model.OrderStatus;
import com.arvind.robotfactory.model.ValidationOutput;
import com.arvind.robotfactory.parts.Robot;
import com.arvind.robotfactory.service.OrderService;
import com.arvind.robotfactory.service.ValidationService;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class RobotFactoryTest {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private OrderService orderService;

    @Test
    public void testCase1_InvalidInput() {
        String [] input = {"A", "C", "D", "F"};
        OrderInput orderInput = new OrderInput(Arrays.asList(input));
        ValidationOutput output = validationService.validateInput( new OrderComponents(orderInput));
        assertThat(output.getStatus()).isEqualTo(OrderStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void testCase2_BadRequest() {
        String [] input = {"BENDER"};
        OrderInput orderInput = new OrderInput(Arrays.asList(input));
        ValidationOutput output = validationService.validateInput( new OrderComponents(orderInput));
        assertThat(output.getStatus()).isEqualTo(OrderStatus.BAD_REQUEST);
    }

    @Test
    public void testCase3_CorrectOrderCreation1() {
        String [] input = {"A", "I", "D", "F"};
        OrderInput orderInput = new OrderInput(Arrays.asList(input));
        OrderComponents components = new OrderComponents(orderInput);
        ValidationOutput output = validationService.validateInput(components);
        assertThat(output.getStatus()).isEqualTo(OrderStatus.VALIDATION_SUCCESSFUL);
        Robot robot = orderService.createRobot(components);
        assertThat(robot.getOrderNumber()).isEqualTo(1);
        assertThat(robot.getCost()).isEqualTo(160.11f);
    }

    @Test
    public void testCase4_SuccessfulOrderCreation2() {
        String [] input = {"I", "A", "E", "F"};
        OrderInput orderInput = new OrderInput(Arrays.asList(input));
        OrderComponents components = new OrderComponents(orderInput);
        ValidationOutput output = validationService.validateInput(components);
        assertThat(output.getStatus()).isEqualTo(OrderStatus.VALIDATION_SUCCESSFUL);

        Robot robot = orderService.createRobot(components);
        assertThat(robot.getOrderNumber()).isEqualTo(2);
        assertThat(robot.getCost()).isEqualTo(143.56f);
    }

    @Test
    public void testCase5_SuccessfulOrderCreation3() {
        String [] input = {"I", "A", "E", "H"};
        OrderInput orderInput = new OrderInput(Arrays.asList(input));
        OrderComponents components = new OrderComponents(orderInput);
        ValidationOutput output = validationService.validateInput(components);
        assertThat(output.getStatus()).isEqualTo(OrderStatus.VALIDATION_SUCCESSFUL);
        Robot robot = orderService.createRobot(components);
        assertThat(robot.getOrderNumber()).isEqualTo(3);
        assertThat(robot.getCost()).isEqualTo(162.79f);
    }

    @Test
    public void testCase6_OrderCreationFailure1() {
        String [] input = {"I", "A", "E", "F"};
        OrderInput orderInput = new OrderInput(Arrays.asList(input));
        OrderComponents components = new OrderComponents(orderInput);
        ValidationOutput output = validationService.validateInput(components);
        assertThat(output.getStatus()).isEqualTo(OrderStatus.VALIDATION_SUCCESSFUL);
        Robot robot = orderService.createRobot(components);
        assertThat(robot).isEqualTo(null);
    }

}
