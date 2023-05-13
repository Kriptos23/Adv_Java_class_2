package Adapter;

import Exceptions.AutoException;
import Exceptions.FileNameException;
import Exceptions.MissingAutoPrice;
import Exceptions.NegativeAutoPrice;

import java.io.IOException;

public class Driver
{
    public static void main(String[] args) throws IOException, FileNameException, NegativeAutoPrice, AutoException, MissingAutoPrice {
        BuildAuto car_interface = new BuildAuto();
        try
        {
            car_interface.BuildAutoObject("Car.txt");
        }
        catch (AutoException e)
        {
            System.out.println("A problem occurred!" + e.getCause());
            AutoException.fix(e.getErrorCode());
            car_interface.BuildAutoObject(AutoException.getCorrectFileName());
        }
//        catch (FileNameException e)
//        {
//            System.out.println(e.getMessage());
//            String a = FileNameException.fix1();
//            car_interface.BuildAutoObject(a);
//        }

        car_interface.UpdateOptionPrice("Color","Fort Knox Gold ClearCoat Metallic", 10);
        car_interface.UpdateOptionSetName("Color", "Colour");
        car_interface.UpdateOptionPrice("Colour","Fort Knox Gold ClearCoat Metallic", 23);
        car_interface.printAuto();
    }
}
