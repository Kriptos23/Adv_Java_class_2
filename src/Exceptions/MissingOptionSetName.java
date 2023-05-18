package Exceptions;

public class MissingOptionSetName extends AutoException
{

    public MissingOptionSetName(){}

    public MissingOptionSetName(String message, Error errorCode)
    {
        super(message, errorCode);
    }
    public static void fix4()
    {
        System.out.println("Option Set Name is blank, please name it in txt file!");
    }
}
