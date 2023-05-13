package Adapter;

import Exceptions.AutoException;

import java.io.IOException;

public interface FixAuto
{
    void fix(int ErNo) throws IOException, AutoException;
}
