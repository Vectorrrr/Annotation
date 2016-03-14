package model;

/**
 * Created by igladush on 14.03.16.
 */

import model.annotation.ConsoleSetup;
import model.annotation.InitDefualtValue;


public class FirstAnnotationClass {
    @InitDefualtValue(defualtValue = "123")
    private Integer firstField;


    @InitDefualtValue(defualtValue = "1111")
    @ConsoleSetup()
    private long secondField;

    @ConsoleSetup
    private String thirdField;
    @Override
    public String toString() {
        return "First field\t" + firstField + " \nSecond field\t" + secondField+
                "\nhirdField\t"+thirdField;
    }
}
