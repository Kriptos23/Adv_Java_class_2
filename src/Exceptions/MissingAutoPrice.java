package Exceptions;

public class MissingAutoPrice extends Exception
{
    int errorCode;

    public MissingAutoPrice(){}

    public MissingAutoPrice(String message, int errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }
    public int getErrorCode()
    {
        return errorCode;
    }

    public static void fix2()
    {
        System.out.println("You are missing base price in the txt file, please input a price :~|");
    }
}
