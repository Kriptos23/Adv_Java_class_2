package Adapter;

import Exceptions.AutoException;
import Exceptions.Error;
import Exceptions.FileNameException;
import Exceptions.MissingAutoPrice;
import Exceptions.NegativeAutoPrice;
import Model.Automobile;

import java.io.IOException;
import java.util.HashMap;

public class Driver
{
    public static void main(String[] args) throws IOException, FileNameException, NegativeAutoPrice, AutoException, MissingAutoPrice
    {


        BuildAuto car_interface = new BuildAuto();
        Automobile a = null;
        Automobile b = null;
        try
        {
            a = car_interface.BuildAutoObject("Car.txt");
            b = car_interface.BuildAutoObject("Car.txt");
        }
        catch (AutoException e)
        {

            System.out.println("A problem occurred! " + e.getClass().getName());
            AutoException.fix(e.getErrorCode());
            if(e.getErrorCode() == Error.FILE_NAME_ERROR)
            {
                System.out.println("Hello there");
                car_interface.BuildAutoObject(AutoException.getCorrectFileName());
            }
        }
//        catch (FileNameException e)
//        {
//            System.out.println(e.getMessage());
//            String a = FileNameException.fix1();
//            car_interface.BuildAutoObject(a);
//        }

        HashMap<Automobile, Integer> SetOfModels = new HashMap<>();
        SetOfModels.put(a, 1);
        SetOfModels.put(b, 2);
        car_interface.setSetOfModels(SetOfModels);


        car_interface.UpdateOptionPrice("Ford's Focus Wagon ZTW" ,"Color","Fort Knox Gold ClearCoat Metallic", 10);
        car_interface.UpdateOptionSetName("Ford's Focus Wagon ZTW","Color", "Colour");
        car_interface.UpdateOptionPrice("Ford's Focus Wagon ZTW" , "Colour","Fort Knox Gold ClearCoat Metallic", 23);
        car_interface.printAuto("Ford's Focus Wagon ZTW");
        System.out.println("Set of Models: ");
        System.out.println(SetOfModels);

    }
}
