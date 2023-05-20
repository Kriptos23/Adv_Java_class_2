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


        BuildAuto a = new BuildAuto();
        BuildAuto b = new BuildAuto();
//        Automobile a = null;
//        Automobile b = null;
        try
        {
            a.BuildAutoObject("Car.txt");
            b.BuildAutoObject("Car.txt");
        }
        catch (AutoException e)
        {

            System.out.println("A problem occurred! " + e.getClass().getName());
            AutoException.fix(e.getErrorCode());
            if(e.getErrorCode() == Error.FILE_NAME_ERROR)
            {
                System.out.println("Hello there");
                a.BuildAutoObject(AutoException.getCorrectFileName());
            }
        }
//        catch (FileNameException e)
//        {
//            System.out.println(e.getMessage());
//            String a = FileNameException.fix1();
//            car_interface.BuildAutoObject(a);
//        }



        b.UpdateOptionPrice("Ford's Focus Wagon ZTW" ,"Color","Fort Knox Gold ClearCoat Metallic", 10);
        b.UpdateOptionSetName("Ford's Focus Wagon ZTW","Color", "Colour");
        b.UpdateOptionPrice("Ford's Focus Wagon ZTW" , "Colour","Fort Knox Gold ClearCoat Metallic", 23);

//        System.out.println("*** Printing BuildAuto a: ***");
//        a.printAuto("Ford's Focus Wagon ZTW");
//        System.out.println("*** Printing BuildAuto b: ***");
//        b.printAuto("Ford's Focus Wagon ZTW");

        System.out.println("Set of Models size: ");
        System.out.println(proxyAutomobile.getSetOfModels().size());

    }
}
