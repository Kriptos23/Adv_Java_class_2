package Adapter;

import Exceptions.AutoException;
import Exceptions.Error;

import java.io.IOException;

public interface FixAuto
{
    void fix(Error ErNo) throws IOException, AutoException;
}
