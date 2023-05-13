package Exceptions;

import java.io.File;
import java.util.Scanner;

public class FileNameException extends Exception
{
    int errorCode;
    public FileNameException(){}

    public FileNameException(String message, int errorCode)
    {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public static String fix1() {
        Scanner sc = new Scanner(System.in);
        boolean flag = false;
        String newName = "";
        while (!flag) {
            System.out.println("Please type filename correctly: ");
            newName = sc.nextLine();
            File file = new File(newName);
            if (file.exists()) {
                flag = true;
                return newName;
            }
        }
        return newName;
    }

}
