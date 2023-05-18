package Exceptions;

public class MissingOptionName extends AutoException
{

    public MissingOptionName(){}

    public MissingOptionName(String message, Error errorCode)
    {
        super(message, errorCode);
    }

    public static void fix5()
    {
        System.out.println("Option Name is blank, please name it in txt file!");
    }
}
