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
         /** How the txt file should look like?
         * First of all, auto should start and end with "Auto"
         * OptionSet should start and end with "OptionSet"
         * Option should start and end with "Option"
         * Spaces between name should be replaced with underline "_"
         * names and other properties of Option should be inside of Option
         * names and other properties of OptionSet should be inside OptionSet
         * names and other properties of Auto should be inside of Auto
         * txt file is user-friendly and easily can be understood by human
         * **/

        BuildAuto a = new BuildAuto();
        BuildAuto b = new BuildAuto();
//        Automobile a = null;
//        Automobile b = null;
        try
        {
            a.BuildAutoObject("Car.txt");
            b.BuildAutoObject("Toyota.txt");
        }
        catch (AutoException e)
        {
            System.out.println("A problem occurred! " + e.getClass().getName());
            AutoException.fix(e.getErrorCode());
            if(e.getErrorCode() == Error.FILE_NAME_ERROR)
            {
                System.out.println("Restart file read after fixing FILE_NAME_ERROR");
                a.BuildAutoObject(AutoException.getCorrectFileName());
            }
        }
        System.out.println("Print before the update methods");
        a.printAuto("Ford's Focus Wagon ZTW");
        System.out.println();

        a.UpdateOptionPrice("Ford's Focus Wagon ZTW" ,"Color","Fort Knox Gold ClearCoat Metallic", 10);
        a.UpdateOptionSetName("Ford's Focus Wagon ZTW","Color", "Colour");
        a.UpdateOptionPrice("Ford's Focus Wagon ZTW" , "Colour","Fort Knox Gold ClearCoat Metallic", 23);
        a.UpdateOptionPrice("Mercedes" , "Colour","Fort Knox Gold ClearCoat Metallic", 23);

        System.out.println("After update methods using: ");
        System.out.println("*** Printing BuildAuto a: ***");
        a.printAuto("Toyota Camry");
        System.out.println("*** Printing BuildAuto b: ***");
        b.printAuto("Ford's Focus Wagon ZTW");

        System.out.println("Set of Models size: ");
        System.out.println(proxyAutomobile.getSetOfModels().size());
        System.out.println("Set of Models: ");
        System.out.println(proxyAutomobile.getSetOfModels());

//        String[] x = {"Color", "Fort Knox Gold ClearCoat Metallic", "dark-blue", "23"};//input for operation 0
//        a.editThread("FordWagonZTW", 0, x);

        System.out.println("Here: ");
        a.PrintCarsSet();

//        String[] x = {"Color", "Fort Knox Gold ClearCoat Metallic", "dark-blue", "23"};//input for operation 0
//        a.editThread("FordWagonZTW", 0, x);

    }
}
