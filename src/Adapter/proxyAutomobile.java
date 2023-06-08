package Adapter;
import Exceptions.AutoException;
import Exceptions.Error;
import Exceptions.FileNameException;
import Exceptions.MissingAutoPrice;
import Exceptions.NegativeAutoPrice;
import Model.*;
import Scale.EditOptions;
import Util.FileIO;
import java.util.HashMap;
import Scale.EditAuto;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public abstract class proxyAutomobile
{
    private Automobile a1;
    private AutoException a2;
//    private HashMap<Automobile, Integer> setOfModels;
    private static LinkedHashMap<String, Automobile> setOfModels = new LinkedHashMap<>();

    FileIO fileIO = new FileIO();

    public static HashMap<String, Automobile> getSetOfModels() {
        return setOfModels;
    }

    public static void setSetOfModels(LinkedHashMap<String, Automobile> setOfModels) {
        proxyAutomobile.setOfModels = setOfModels;
    }

//    public static void putSetOfModels(String modelName, Automobile a) {
//        proxyAutomobile.setOfModels.put(modelName, a);
//    }

    public proxyAutomobile()
    {
        this.a1 = new Automobile();
    }

    public void printAuto(String ModelName)
    {
//        for(Map.Entry<Automobile, Integer> i : setOfModels.entrySet())
//        {
//            if(i.getKey().getName().equals(ModelName))
//            {
//                this.a1.printAuto();
//            }
//        }
        Automobile automobile = setOfModels.get(ModelName);
        if(automobile == null)
        {
            System.out.println("No such automobile with the name " + ModelName);
        }
        else
        {
            automobile.printAuto();
        }
    }
    public void BuildAutoObject(String fileName) throws FileNameException, NegativeAutoPrice, IOException, AutoException, MissingAutoPrice
    {
        a1 = fileIO.buildAutoObject(fileName);
        setOfModels.put(a1.getName(),a1);
//        return a1;
    }
    public void UpdateOptionSetName(String ModelName, String OptSN, String name)
    {
        Automobile automobile = setOfModels.get(ModelName);
        if(automobile == null)
        {
            System.out.println("No such automobile with the name " + ModelName);
        }
        else
        {
            automobile.UpdateOptSetName(OptSN,name);
        }
    }

    public void UpdateOptionPrice(String ModelName, String OptSN, String OptN, int price)
    {
        Automobile automobile = setOfModels.get(ModelName);
        if(automobile == null)
        {
            System.out.println("No such automobile with the name " + ModelName);
        }
        else
        {
            automobile.UpdateOptionPrice(OptSN, OptN, price);
        }
    }

    public void fix(Error ErNo) throws IOException, AutoException {
        a2.fix(ErNo);
    }

    public void putSetOfModels(String ModelName, BuildAuto a)
    {
        Automobile automobile = setOfModels.get(ModelName);
//        setOfModels.put(ModelName, a);

    }
    public void removeSetOfModels(String ModelName)
    {

    }
//    public static Automobile getCarSetOfModels(String ModelName)
//    {
//        return setOfModels.get(ModelName);
//    }

    protected void printAllModels() {
        System.out.println("We have following cars: ");
        for(String modelName : setOfModels.keySet()) {
            Automobile automobile = setOfModels.get(modelName);
            automobile.printAuto();
        }
    }


    public Automobile getCarSetOfModels(String ModeName)
    {

        Automobile a = setOfModels.get(ModeName);
        return a;
    }
    public void editThread(String ModelName, int operation, String[] args)
    {
        EditOptions editOptions = new EditOptions(ModelName, operation, args);
//        editOptions.getAuto().printAuto();
        Thread one = editOptions.getT();
        one.start();
    }

    public void addCarToLHM(Automobile car)
    {
        setOfModels.put(car.getName(),car);
    }

    public void PrintCarsSet()
    {
        System.out.println("We have following cars: ");
        for (String key : setOfModels.keySet())
        {
            System.out.println(key);
        }
    }
}
