package Exceptions;

import java.io.*;
import java.util.Scanner;

public class NegativeAutoPrice extends AutoException
{
    public NegativeAutoPrice(){}

    public NegativeAutoPrice(String message, Error errorCode)
    {
        super(message, errorCode);
    }

    public static void fix3()
    {
        System.out.println("You have negative price in the txt file please change it to positive :)");


        //I have tried to prompt the user for the new base price and put it in the txt file, but it didn't work
        //        Scanner sc = new Scanner(System.in);
//        boolean flag = false;
//        String newPrice = null;
////        while (!flag)
////        {
////            System.out.println("Please enter new price: ");
////            newPrice = sc.nextLine();
////            if (isInt(newPrice))
////            {
////                flag = true;
////            }
////        }

//        File file = new File(fileName);
//        if (!(file.exists()))
//        {
//            throw new AutoException("\nFile doesn't exist or you made a typo, dummie",1);
////            throw new FileNameException("\nFile doesn't exist, or you made a typo, dummie",1);
////            System.out.println("problem");
//        }
//        FileReader read = new FileReader(file);
//        BufferedReader bread = new BufferedReader(read);
//        String line;
//        int line_number = 1;
//        while ((line = bread.readLine()) != null)
//        {
//            line = line.replaceAll("\\s+", "");
//            line = line.replaceAll("_", " ");
//            String[] word_in_line = line.split(":");
////            if (word_in_line.length==1)
////            {
//                if (word_in_line[0].equals("basePrice"))
//                {
//                    System.out.println("Hr23322323e:");
//                    word_in_line[1] = newPrice;
//                    System.out.println(word_in_line[1]);
//                    return Integer.parseInt(newPrice);
//                }
////            }
//        }
//        return Integer.parseInt(newPrice);
    }

}
