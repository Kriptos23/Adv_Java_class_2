package Util;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

import Exceptions.*;
import Exceptions.Error;
import Model.Automobile;

public class FileIO
{
     /** How the txt file should look like?
     * First of all, auto should start and end with "Auto"
     * OptionSet should start and end with "OptionSet"
     * Option should start and end with "Option"
     * Spaces between name should be replaced with underline "_"
     * names and other properties of Option should be inside of Option
     * names and other properties of OptionSet should be inside OptionSet
     * names and other properties of Auto should be inside of Auto
     * txt file is user-friendly and easily can be understood by human
     * **/
    public FileIO(){}

    public Automobile ReadPropertiesAndCreateAuto(Properties prop) throws IOException
    {
        Automobile car = new Automobile("",0,0);
        String CarName = prop.getProperty("Car");
        car.setName(CarName);
        if(!CarName.equals(null))
        {
            String OptionSet1 = prop.getProperty("OptionSet1");
            String Option1a = prop.getProperty("Option1a");
            String Option1b = prop.getProperty("Option1b");
            String OptionSet2 = prop.getProperty("OptionSet2");
            String Option2a = prop.getProperty("Option2a");
            String Option2b = prop.getProperty("Option2b");

            car.addOptionSet(OptionSet1);
            car.addOption(OptionSet1, Option1a, 0);
            car.addOption(OptionSet1, Option1b, 0);
            car.addOptionSet(OptionSet2);
            car.addOption(OptionSet2, Option2a, 0);
            car.addOption(OptionSet2, Option2b, 0);
        }
        else
        {
            System.out.println("Properties file not Found");
        }

        return car;
    }

    public static void fix(int ErrorCode)
    {
        Scanner sc = new Scanner(System.in);

        if(ErrorCode==1)
        {
            FileNameException.fix1();
        }
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
//        System.out.println(new_car);
        return new_car;
    }

    public void serialize(String filename, Automobile car) throws IOException
    {

        System.out.println("Serialization process: ");
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(car);
        out.close();
        fileOut.close();
        System.out.println("Info saved :)");
        System.out.println();
    }

    public Automobile deserialize(String filename) throws IOException, ClassNotFoundException {
        System.out.println("Deserialization process");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
        Automobile car = (Automobile) in.readObject();
        return car;
    }

}
