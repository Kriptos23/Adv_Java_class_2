package Exceptions;

public class MissingAutoPrice extends AutoException
{

    public MissingAutoPrice(){}

    public MissingAutoPrice(String message, Error errorCode)
    {
        super(message, errorCode);
    }

    public static void fix2()
    {
        System.out.println("You are missing base price in the txt file, please input a price :~|");
    }
}
