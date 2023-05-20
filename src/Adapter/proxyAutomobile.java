package Adapter;
import Exceptions.AutoException;
import Exceptions.Error;
import Exceptions.FileNameException;
import Exceptions.MissingAutoPrice;
import Exceptions.NegativeAutoPrice;
import Model.*;
import Util.FileIO;
import java.util.HashMap;

import java.io.IOException;
import java.util.Map;

public abstract class proxyAutomobile
{
    private static Automobile a1;
    private AutoException a2;
//    private HashMap<Automobile, Integer> setOfModels;
    private static HashMap<String, Automobile> setOfModels = new HashMap<>();;

    FileIO fileIO = new FileIO();

    public static HashMap<String, Automobile> getSetOfModels() {
        return setOfModels;
    }

    public static void setSetOfModels(HashMap<String, Automobile> setOfModels) {
        proxyAutomobile.setOfModels = setOfModels;
    }

    public proxyAutomobile()  {
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
        automobile.printAuto();
    }
    public void BuildAutoObject(String fileName) throws FileNameException, NegativeAutoPrice, IOException, AutoException, MissingAutoPrice {
        a1 = fileIO.buildAutoObject(fileName);
        System.out.println("flag: " + a1.getName());
        setOfModels.put(a1.getName(),a1);
//        return a1;
    }
    public void UpdateOptionSetName(String ModelName, String OptSN, String name)
    {
        Automobile automobile = setOfModels.get(ModelName);
        automobile.UpdateOptSetName(OptSN,name);
    }

    public void UpdateOptionPrice(String ModelName, String OptSN, String OptN, int price)
    {
        Automobile automobile = setOfModels.get(ModelName);
        automobile.UpdateOptionPrice(OptSN, OptN, price);
    }

    public void fix(Error ErNo) throws IOException, AutoException {
        a2.fix(ErNo);
    }
}
