package Util;

import java.io.*;
import java.util.Scanner;

import Exceptions.*;
import Exceptions.Error;
import Model.Automobile;

public class FileIO
{
    public FileIO(){}

    public static void fix(int ErrorCode)
    {
        Scanner sc = new Scanner(System.in);


        if(ErrorCode==1)
        {
            FileNameException.fix1();
        }

//        boolean flag = true;
//        String newName = "";
//        File file = new File(FileName);
//        Scanner sc = new Scanner(System.in);
//        if (file.exists())
//        {
//            flag = true;
//        }
//        while(!flag)
//        {
//            System.out.println("Please type filename correctly: ");
//            newName = sc.nextLine();
//            if (file.exists())
//            {
//                flag = true;
//            }
//        }
//        return newName;
    }
    public Automobile buildAutoObject(String fileName) throws FileNameException, IOException, NegativeAutoPrice, AutoException, MissingAutoPrice {
        boolean AutoFlag = false;
        boolean OptionSetFlag = false;
        boolean OptionFlag = false;
        boolean MOSDException = false;

        Automobile new_car = new Automobile("",0,0);

        String last_OptionSetName = "";
        String tempOptionName = "";



        File file = new File(fileName);
        if (!(file.exists()))
        {
            throw new FileNameException(Error.FILE_NAME_ERROR.message, Error.FILE_NAME_ERROR);
//            throw new FileNameException("\nFile doesn't exist, or you made a typo, dummie",1);
        }
        FileReader read = new FileReader(file);
        BufferedReader bread = new BufferedReader(read);
        String line;
        int line_number = 1;

        while ((line = bread.readLine()) != null) {
            int tempOptSetSize;
            int tempOptionSize;

            line = line.replaceAll("\\s+", "");
            line = line.replaceAll("_", " ");
            String[] word_in_line = line.split(":"); // ex: line = "name Toyota Dan iel" => array of Strings word_in_line = ["name", "Toyota", "Dan", "iel"]
            if (word_in_line.length == 1)
            {
                if (word_in_line[0].equals("Auto"))
                {
                    if (!AutoFlag) {
                        AutoFlag = true;
                    } else {
                        AutoFlag = false;
                    }
                }
                else if (word_in_line[0].equals("OptionSet"))
                {
                    if (!OptionSetFlag) {
                        OptionSetFlag = true;
                        MOSDException = false;
                    } else {
                        last_OptionSetName = "";
                        OptionSetFlag = false;
                    }
                }
                else if (word_in_line[0].equals("Option"))
                {
                    if (!OptionFlag) {
                        OptionFlag = true;
                    } else {
                        tempOptionName = "";
                        OptionFlag = false;
                    }
                }
                else if (word_in_line[0].equals("basePrice"))
                {
                    throw new MissingAutoPrice(Error.MISSING_AUTO_PRICE.message, Error.MISSING_AUTO_PRICE);
                }
                else if (word_in_line[0].equals("name") && !OptionFlag && OptionSetFlag)
                {
                    throw new MissingOptionSetName(Error.MISSING_OPTION_SET_NAME.message, Error.MISSING_OPTION_SET_NAME);
                }
                else if (word_in_line[0].equals("name") && OptionFlag)
                {
                    throw new MissingOptionName(Error.MISSING_OPTION_NAME.message, Error.MISSING_OPTION_NAME);
                }
                continue;
            }
            else if (word_in_line.length == 2) {
                int count = 0;
                if (OptionFlag) {
                    if (word_in_line[0].equals("name")) {
                        tempOptionName = word_in_line[1];
                        new_car.addOption(last_OptionSetName, word_in_line[1], 0);
                    } else if (word_in_line[0].equals("price")) {
                        if (tempOptionName.isEmpty())
                        {
                            throw new MissingOptionNameLine(Error.MISSING_OPTION_NAME_LINE.message, Error.MISSING_OPTION_NAME_LINE);
                        }
                        new_car.UpdateOptionWithoutMessage(last_OptionSetName, tempOptionName, tempOptionName, Integer.parseInt(word_in_line[1]));
                        count++;
                    }
                } else if (OptionSetFlag) {
                    if (word_in_line[0].equals("name")) {
                        last_OptionSetName = word_in_line[1];
                        new_car.addOptionSet(word_in_line[1]);
                    } else if (word_in_line[0].equals("numberOfOptions")) {
                        tempOptSetSize = Integer.parseInt(word_in_line[1]);
                    }
                } else if (AutoFlag) {
                    if (word_in_line[0].equals("name")) {
                        new_car.setName(word_in_line[1]);
                    } else if (word_in_line[0].equals("basePrice"))
                    {
                        if ((Double.parseDouble(word_in_line[1])<0))
                        {
                            throw new NegativeAutoPrice(Error.NEGATIVE_AUTO_PRICE.message, Error.NEGATIVE_AUTO_PRICE);
                        }
                        else
                        {
                            new_car.setBasePrice(Double.parseDouble(word_in_line[1]));
//                            new_car.setBasePrice(23);
                        }
                    }
                    else if (word_in_line[0].equals("numberOfOptionSets"))
                    {
                        //Nothing here
                    }
                }
            }
            line_number++;
        }
        //System.out.println("Method BuildAuto CheckPoint");
        System.out.println(new_car);
        return new_car;
    }


    //This code could be useful in future, almost the same as the buildAutoObject() method
    /*
    boolean AutoFlag = false;
        boolean OptionSetFlag = false;
        boolean OptionFlag = false;

        Auto new_car = new Auto("",0,0);
        String last_OptionSetName = "";
        String tempOptionName = "";


        try {
            FileReader read = new FileReader("Car.txt");

            BufferedReader bread = new BufferedReader(read);
            String line;
            int line_number = 1;


            while ((line = bread.readLine()) != null)
            {
                int tempOptSetSize;
                int tempOptionSize;

//                System.out.println("Line number: "  + line_number + ", line: " + line) ;
                line = line.replaceAll("\\s+", "");
                String[] word_in_line = line.split(":"); // ex: line = "name Toyota Dan iel" => array of Strings word_in_line = ["name", "Toyota", "Dan", "iel"]
//                System.out.println("\tword_in_line.length: " + word_in_line.length);
                System.out.println("\tword_in_line: " + Arrays.toString(word_in_line));

                if(word_in_line.length == 1)
                {
                    if(word_in_line[0].equals("Auto")){
                        if(!AutoFlag){
                            AutoFlag = true;
                        }else{
                            AutoFlag = false;
                        }
                    }
                    else if(word_in_line[0].equals("OptionSet"))
                    {
                        if(!OptionSetFlag){
                            OptionSetFlag = true;
                        }else{
                            OptionSetFlag = false;
                        }
                    }else if (word_in_line[0].equals("Option"))
                    {
                        if(!OptionFlag){
                            OptionFlag = true;
                        }else{
                            OptionFlag = false;
                        }
                    }
                    continue;
                }

                else if(word_in_line.length == 2)
                {
                    int count = 0;
                    if(OptionFlag)
                    {
                        System.out.println("Inside OptionFlag");
                        if(word_in_line[0].equals("name"))
                        {
                            tempOptionName = word_in_line[1];
                            new_car.addOption(last_OptionSetName, word_in_line[1],0);
                        }
                        else if(word_in_line[0].equals("price"))
                        {
//                            tempOptionSize = Integer.parseInt(word_in_line[1]);
                            new_car.UpdateOption(last_OptionSetName, tempOptionName, tempOptionName, Integer.parseInt(word_in_line[1]));
                            count++;
                        }
                    }
                    else if (OptionSetFlag)
                    {
                        System.out.println("inside OptionSetFlag");
                        if (word_in_line[0].equals("name"))
                        {
//                                tempOptSetName = word_in_line[1];
                            last_OptionSetName = word_in_line[1];
                            new_car.addOptionSet(word_in_line[1]);
                            System.out.println("Changed optionSet name to " + word_in_line[1]);
                        }
                        else if(word_in_line[0].equals("numberOfOptions"))
                        {
                            tempOptSetSize = Integer.parseInt(word_in_line[1]);
                        }
                    }
                    else if (AutoFlag)
                    {
                        System.out.println("inside AutoFlag");
                        if(word_in_line[0].equals("name"))
                        {
                            new_car.setName(word_in_line[1]);
                            System.out.println("Changed auto name to " + word_in_line[1]);
                        }
                        else if (word_in_line[0].equals("basePrice"))
                        {
//                            car_price = Double.parseDouble(word_in_line[1]);
                            new_car.setBasePrice(Double.parseDouble(word_in_line[1]));
                        }
                        else if (word_in_line[0].equals("numberOfOptionSets"))
                        {
//                            FordOptSets = new OptionSet[Integer.parseInt(word_in_line[1])];
                        }
                    }
                }
                line_number++;
            }

            System.out.println("CheckPoint");
            System.out.println(new_car);
//            System.out.println(Arrays.toString(colors_arr));



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
     */

}
