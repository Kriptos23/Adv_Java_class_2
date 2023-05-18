

package Exceptions;

import java.io.IOException;

public class AutoException extends  Exception
{

    public Error errorCode;
    public AutoException(){}
    static String CorrectFileName = "";

    public static String getCorrectFileName() {
        return CorrectFileName;
    }

    public static void setCorrectFileName(String correctFileName) {
        CorrectFileName = correctFileName;
    }

    public AutoException(String message, Error errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public Error getErrorCode()
    {
        return errorCode;
    }
    public static void fix(){}
    public static void fix(Error errNumber) throws AutoException, IOException {

        switch (errNumber)
        {
            case FILE_NAME_ERROR:
                CorrectFileName = FileNameException.fix1();
                break;
            case MISSING_AUTO_PRICE:
                MissingAutoPrice.fix2();
                break;
            case NEGATIVE_AUTO_PRICE:
                NegativeAutoPrice.fix3();
                break;
            case MISSING_OPTION_SET_NAME:
                MissingOptionSetName.fix4();
                break;
            case MISSING_OPTION_NAME:
                MissingOptionName.fix5();
                break;
            case MISSING_OPTION_NAME_LINE:
                MissingOptionNameLine.fix6();
                break;

        }
    }
}
