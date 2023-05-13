package Adapter;

import Exceptions.AutoException;
import Exceptions.FileNameException;
import Exceptions.MissingAutoPrice;
import Exceptions.NegativeAutoPrice;
import Model.*;

import java.io.IOException;

public interface CreateAuto
{
    Auto BuildAutoObject(String fileName) throws FileNameException, NegativeAutoPrice, IOException, AutoException, MissingAutoPrice;
    void printAuto();
}
