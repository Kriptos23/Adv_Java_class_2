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

public abstract class proxyAutomobile implements CreateAuto, UpdateAuto, FixAuto
{
    private Automobile a1;
    private AutoException a2;
    private HashMap<Automobile, Integer> setOfModels;

    FileIO fileIO = new FileIO();

    public HashMap<Automobile, Integer> getSetOfModels() {
        return setOfModels;
    }

    public void setSetOfModels(HashMap<Automobile, Integer> setOfModels) {
        this.setOfModels = setOfModels;
    }

    public proxyAutomobile()  {
        this.a1 = new Automobile();
        setOfModels = new HashMap<>();
    }

    @Override public void printAuto(String ModelName)
    {
        for(Map.Entry<Automobile, Integer> i : setOfModels.entrySet())
        {
            if(i.getKey().getName().equals(ModelName))
            {
                this.a1.printAuto();
            }
        }
    }
    public Automobile BuildAutoObject(String fileName) throws FileNameException, NegativeAutoPrice, IOException, AutoException, MissingAutoPrice {
        a1 = fileIO.buildAutoObject(fileName);
        return a1;
    }
    @Override public void UpdateOptionSetName(String ModelName, String OptSN, String name)
    {
        for(Map.Entry<Automobile, Integer> i : setOfModels.entrySet())
        {
            if(i.getKey().getName().equals(ModelName))
            {
                a1.UpdateOptSetName(OptSN,name);
            }
        }
    }

    @Override public void UpdateOptionPrice(String ModelName, String OptSN, String OptN, int price)
    {
        for(Map.Entry<Automobile, Integer> i : setOfModels.entrySet())
        {
            if(i.getKey().getName().equals(ModelName))
            {
                a1.UpdateOptionPrice(OptSN, OptN, price);
            }
        }
    }

    @Override
    public void fix(Error ErNo) throws IOException, AutoException {
        a2.fix(ErNo);
    }
}
