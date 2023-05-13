package Adapter;
import Exceptions.AutoException;
import Exceptions.FileNameException;
import Exceptions.MissingAutoPrice;
import Exceptions.NegativeAutoPrice;
import Model.*;
import Util.FileIO;

import java.io.IOException;

public abstract class proxyAutomobile implements CreateAuto, UpdateAuto, FixAuto
{
    private Auto a1;
    private AutoException a2;
    FileIO test = new FileIO();

    public proxyAutomobile() throws IOException {
        this.a1 = new Auto();
    }

    public void printAuto() {
        this.a1.printAuto();
    }
    public Auto BuildAutoObject(String fileName) throws FileNameException, NegativeAutoPrice, IOException, AutoException, MissingAutoPrice {
        a1 = test.buildAutoObject(fileName);
        return a1;
    }
    public void UpdateOptionSetName(String OptSN, String name)
    {
        a1.UpdateOptSetName(OptSN,name);
    }

    @Override public void UpdateOptionPrice(String OptSN, String OptN, int price)
    {
        a1.UpdateOptionPrice(OptSN, OptN, price);
    }

    @Override
    public void fix(int ErNo) throws IOException, AutoException {
        a2.fix(ErNo);
    }
}
