package Model;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Exceptions.AutoException;
import Exceptions.FileNameException;
import Exceptions.MissingAutoPrice;
import Exceptions.NegativeAutoPrice;
import Util.FileIO;


public class Main //This is old Assignment 1 test class, not needed anymore
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
    public static void main(String[] args) throws IOException, FileNameException, ClassNotFoundException, NegativeAutoPrice, AutoException, MissingAutoPrice {
        //My testing that I was doing during the code writing, includes in itself more detailed testing of
        //Option/OptionSet/Auto class methods

//        myTestingMethod(); //UNCOMMENT ME if you wanna see more detailed testing


        Scanner sc = new Scanner(System.in);
        System.out.println("Method test");
        System.out.println();
        Automobile car_1 = null;
        FileIO fileIO = new FileIO();

        //Build Automobile Object from a file.
        System.out.println("Print B4 serialization: ");

        car_1 = fileIO.buildAutoObject("Car.txt");
        car_1.printAuto();
        System.out.println();

        //Serialize the object
        System.out.println("Serialization process: ");
        String serializationFile = "CarSerialization.txt";
        fileIO.serialize(serializationFile, car_1);
        System.out.println();

        //Deserialize the object and read it into memory.
        System.out.println("Deserialization process");
        Automobile ford3 = fileIO.deserialize(serializationFile);

        //Print new attributes.
        System.out.println("\nAfter Serialization/Deserialization Print:");
        ford3.printAuto();
    }


    public static void myTestingMethod() throws IOException, ClassNotFoundException
    {

        System.out.println("Thank you for deciding to buy Ford's Focus Wagon ZTW \nYou have following options " +
                "for your car:");
        OptionSet color = new OptionSet("Colors", 0);
        ArrayList<OptionSet.Options> colors_arr = new ArrayList<>(3);
        colors_arr.set(0, color.new Options("Blue", 0.0));
        colors_arr.set(1, color.new Options("Red", 0.0));
        colors_arr.set(2, color.new Options("Green", 0.0));
        color.setOpt(colors_arr);

        OptionSet Transmission = new OptionSet("Transmission", 0);
        ArrayList<OptionSet.Options> trans_arr = new ArrayList<>(2);
        trans_arr.set(0, Transmission.new Options("Automatic", 0.0));
        trans_arr.set(1, Transmission.new Options("Standard", -815.0));
        Transmission.setOpt(trans_arr);

        OptionSet Brakes = new OptionSet("Brakes", 0);
        ArrayList<OptionSet.Options> braked_arr = new ArrayList<>(3);
        braked_arr.set(0, Brakes.new Options("Standard", 0));
        braked_arr.set(1, Brakes.new Options("ABS", 400.0));
        braked_arr.set(2, Brakes.new Options("ABS with Advance Trac", 1625.0));
        Brakes.setOpt(braked_arr);

        OptionSet AirBags = new OptionSet("Air Bags", 0);
        ArrayList<OptionSet.Options> bags_arr = new ArrayList<>(2);
        bags_arr.set(0, AirBags.new Options("None", 0.0));
        bags_arr.set(1, AirBags.new Options("Selected", 350.0));
        AirBags.setOpt(bags_arr);

        OptionSet Moonroof = new OptionSet("Moonroof", 0);
        ArrayList<OptionSet.Options> roof_arr = new ArrayList<>(2);
        roof_arr.set(0, Moonroof.new Options("None", 0.0));
        roof_arr.set(1, Moonroof.new Options("Selected", 595.0));
        Moonroof.setOpt(roof_arr);

        Automobile Ford = new Automobile("Focus Fagon ZTW", 0, 5);
        ArrayList<OptionSet> Arr_OptSets = new ArrayList<>();
        Arr_OptSets.set(0, color);
        Arr_OptSets.set(1, Transmission);
        Arr_OptSets.set(2, Brakes);
        Arr_OptSets.set(3, AirBags);
        Arr_OptSets.set(4, Moonroof);
        Ford.setOptS(Arr_OptSets);






        // Testing find()
        OptionSet a1;
        OptionSet.Options b;

        a1 = Ford.FindSet("Colors");
        b = Ford.FindOption("Red");

//        System.out.println("Checkpoint");
//        Ford.DeleteSet("Brakes");
        OptionSet colorOptionSetOfFord = Ford.FindSet("Colors");



        // Testing delete()
//        Ford.DeleteOption("Red");
//        b = Ford.FindOption("Red");
//
//        Ford.UpdateOption("Deleted", "Red", 0);
//        Ford.FindOption("Red");

        // Testing add and delete for OptionSet
        OptionSet.Options orange = color.new Options("Orange", 0.0);
        System.out.println("Before");
        System.out.println((color.getOpt()));

        System.out.println("Adds orange");
        color.addOption(orange);
        System.out.println((color.getOpt()));

        color.deleteOption("Blue");
        System.out.println((color.getOpt()));

        color.deleteOption("Red");
        System.out.println((color.getOpt()));

        color.deleteOption("Black");
        System.out.println((color.getOpt()));

        Ford.DeleteOption("Orange", "Colors");
        System.out.println((color.getOpt()));

        System.out.println("Adds orange");
        color.addOption(orange);

        System.out.println("Deletes Green");
        Ford.DeleteOption("Green", "Colors");
        System.out.println((color.getOpt()));

        //Testing OptionSet Delete
        System.out.println("Deleting OptionSet");
        System.out.println(Ford.getOptS());
        //Works perfectly
//        Ford.DeleteSet("Colors");
//        System.out.println(Arrays.toString(Ford.getOptS()));

        System.out.println("To get a "+(color.getOpt()));
        OptionSet.Options Ora = color.getOneOpt(1);
        System.out.println(Ora);
        System.out.println((color.getOpt()));
        System.out.println("Check");
        System.out.println(Ford.getOptS());

//        setOneOptionSet might have some bugs in it in Auto class
//        Also setOneOptionSetOption might have some bugs in Auto class
    }

}