package Exceptions;

public class MissingOptionNameLine extends AutoException
{

    public MissingOptionNameLine(){}

    public MissingOptionNameLine(String message, Error errorCode)
    {
        super(message, errorCode);
    }

    public static void fix6()
    {
        System.out.println("Missing Option Name line in txt file!");
    }
}
