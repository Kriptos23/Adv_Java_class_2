package Exceptions;

import java.io.IOException;

public class AutoException extends  Exception
{
    int errorCode;
    public AutoException(){}
    static String CorrectFileName = "";

    public static String getCorrectFileName() {
        return CorrectFileName;
    }

    public static void setCorrectFileName(String correctFileName) {
        CorrectFileName = correctFileName;
    }

    public AutoException(String message, int errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode()
    {
        return errorCode;
    }
    public static void fix(){}
    public static void fix(int errNumber) throws AutoException, IOException {

        switch (errNumber)
        {
            case 1:
                CorrectFileName = FileNameException.fix1();
                break;
            case 2:
                MissingAutoPrice.fix2();
                break;
            case 3:
                NegativeAutoPrice.fix3();
                break;

        }
    }
}
