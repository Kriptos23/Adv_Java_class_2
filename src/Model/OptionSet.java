package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class OptionSet implements Serializable
{
    private String OptionSetName;
    private ArrayList<Options> opt;
    private Options OptionChoice;

    //////// Constructors ////////////
    public OptionSet(){
        this.OptionSetName = "";
        this.opt = new ArrayList<>();
        //        this.opt = new Options[0];
    }
    public OptionSet(String OptionSetName, int size)
    {
        this.OptionSetName = OptionSetName;
        this.opt = new ArrayList<>(size);
        //        this.opt = new Options[size];

    }
    public OptionSet(String OptionSetName)
    {
        this(OptionSetName, 0);
        this.opt = new ArrayList<>();
    }
    public OptionSet(int size)
    {
        this("",size);
        this.opt = new ArrayList<>();
    }

    /////////////////Methods/////////
    protected void addOption(Options NewOpt)//aka buildOption
    {
        opt.add(NewOpt);
    }
    //OverLoad
    protected void addOption(String SetName, String Oname, double Oprice)//aka build option
    {
        Options NewOpt = new Options(Oname, Oprice);//New Option
        opt.add(NewOpt);
    }

    protected void deleteOption(String name)
    {
        for (Options i : opt)
        {
            if (i.getOptName().equals(name))
            {
                opt.remove(i);
            }
        }
    }

    protected void UpdateOption(String oldName, String NewName, int NewPrice)
    {
        boolean found = false;
        for (Options j : this.getOpt())
        {
            if (j.getOptName().equals(oldName))
            {
                j.setOptName(NewName);
                j.setOptPrice(NewPrice);
                System.out.println(oldName + " Option updated successfully");
                found = true;
            }
        }

        if (!found)
        {
            System.out.println("No such Option with that name");
        }
    }
    protected void UpdateOptionWithoutMessage(String oldName, String NewName, int NewPrice)
    {
        boolean found = false;
        for (Options j : this.getOpt())
        {
            if (j.getOptName().equals(oldName))
            {
                j.setOptName(NewName);
                j.setOptPrice(NewPrice);
//                System.out.println(oldName + " Option updated successfully"); //Commented this since I don;t want this line
                found = true;
            }
        }

        if (!found)
        {
            System.out.println("No such Option with that name");
        }
    }

    // We assume that user uses index starting with 1 not 0:
    protected void printOneOption(int x)
    {
        int count = 1;
        for(Options i : opt)
        {
            if(count == x){
                System.out.println(i);
                return;
            }
            count++;
        }
        System.out.println("No such Option, printOneOption failed");
    }
    //Overload
    protected void printOneOption(String Oname)
    {
        boolean found = false;// To indicate when Option is found
        int length = this.opt.size();
        for (int i = 0; i < length; i++)
        {
            if (opt.get(i).getOptName().equals(Oname))//To find
            {
                System.out.println(opt.get(i));
                return;
            }
        }
        System.out.println("Option with name " + Oname + " No such Option, printOneOption failed");
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("\nOptionSet name: ");
        sb.append(getOptionSetName());
        if(this.OptionChoice != null) {
            // print only option choice. as array, or " [ + OptionChoice + ],
            sb.append("Option Choice: ");
            sb.append(getOptionChoice());
        }
        sb.append(", \tOptionSet's Options(Array): ");
        sb.append((getOpt()));

        return sb.toString();
    }

    ////////New Methods///

    protected void UpdateOptionPrice(String name, int price)
    {
        for (Options i : opt)
        {
            if (i.getOptName().equals(name))//To find
            {
                i.setOptPrice(price);
            }
        }
    }

    /****************************************************************
     ******** OptionChoice GETTERS AND SETTERS *************** ******
     ***************************************************************/

    protected Options getOptionChoice()
    {
        return OptionChoice;
    }
    protected void setOptionChoice(String OptionName)
    {
        for (Options i : getOpt())
        {
            if (i.getOptName().equals(OptionName))
            {
                OptionChoice = i;
            }
        }
    }

    /****************************************************************
     ******** OptionSet GETTERS AND SETTERS *************** ********
     ***************************************************************/
    protected ArrayList<Options> getOpt() {
        return opt;
    }

    protected void setOpt(ArrayList<Options> opt) {
        this.opt = opt;
    }
    protected String getOptionSetName() {
        return OptionSetName;
    }

    protected void setOptionSetName(String optionName) {
        OptionSetName = optionName;
    }

    // We assume that user uses index starting with 1 not 0:
    protected String getOptionName(int x){
        int count = 1;
        for(Options i : opt)
        {
            if(count == x){
                return i.getOptName();
            }
            count++;
        }
        System.out.println("No such Option, getOptionName failed");
        String o = null;
        return o;
    }
    // We assume that user uses index starting with 1 not 0:
    protected void setOptionName(int x, String OName){
        int count = 1;
        for(Options i : opt)
        {
            if(count == x){
                i.setOptName(OName);
                return;
            }
            count++;
        }
        System.out.println("No such Option, setOptionName failed");
    }
    // We assume that user uses index starting with 1 not 0:
    protected double getOptionPrice(int x){
        int count = 1;
        for(Options i : opt)
        {
            if(count == x){
                return i.getOptPrice();
            }
            count++;
        }
        System.out.println("No such Option, getOptionPrice failed");
        return 0;
    }
    //Overloaded
    protected double getOptionPrice(String Oname){
        boolean found = false;// To indicate when Option is found
        int length = this.opt.size();
        for (int i = 0; i < length; i++)
        {
            if (opt.get(i).getOptName().equals(Oname))//To find
            {
                System.out.println(opt.get(i).getOptPrice());
                return opt.get(i).getOptPrice();
            }
        }
        System.out.println("Option with name " + Oname + " No such Option, getOptionPrice failed");
        return 0;
    }
    protected void setOptionPrice(int x, double OPrice){
        int count = 1;
        for(Options i : opt)
        {
            if(count == x){
                i.setOptPrice(OPrice);
            }
            count++;
        }
        System.out.println("No such Option, setOptionPrice failed");
    }

    protected Options getOneOpt(String Oname){
        boolean found = false;// To indicate when Option is found
        int length = this.opt.size();
        for (int i = 0; i < length; i++)
        {
            if (opt.get(i).getOptName().equals(Oname))//To find
            {
                System.out.println(Oname + " Option with name found successfully");
                return opt.get(i);
            }
        }
        System.out.println("Option with name " + Oname + " didn't found, getOneOption failed");
        return null;
    }
    //Overload
    protected Options getOneOpt(int x){
        int count = 1;
        for(Options i : opt)
        {
            if(count == x){
                return i;
            }
            count++;
        }
        System.out.println("No such Option, getOneOption failed");
        return null;
    }
    protected void setOneOpt(String Oname, Options option){
        String nameToSet = option.getOptName();
        double priceToSet = option.getOptPrice();

        boolean found = false;// To indicate when Option is found
        int length = this.opt.size();
        for (int i = 0; i < length; i++)
        {
            if (opt.get(i).getOptName().equals(Oname))//To find
            {
                System.out.println(Oname + " Option with name found successfully");
                opt.get(i).setOptName(nameToSet);
                opt.get(i).setOptPrice(priceToSet);
                found = true;
            }
        }
        if(!found){
            System.out.println("Option with name " + Oname + " didn't found, getOneOption failed");
        }
    }
    protected void setOneOpt(int x, Options option){
        boolean found = false;
        String nameToSet = option.getOptName();
        double priceToSet = option.getOptPrice();
        int count = 1;
        for(Options i : opt)
        {
            if(count == x){
                System.out.println(x + " Option found successfully");
                i.setOptName(nameToSet);
                i.setOptPrice(priceToSet);
                found = true;
            }
            count++;
        }
        if(!found){
            System.out.println("No such Option, setOneOption failed");
        }
    }

    protected int getOptionsLength(){
        return opt.size();
    }

///////////////////////////////////////New Inner Class/////////////////////////////////////////////////////////////////

    public class Options implements Serializable
    {
        private String OptName;
        private double OptPrice;
        private static int size; //not used

        //////// Constructors ////////////
        public Options (String OptName, double OptPrice)
        {
            this.OptName = OptName;
            this.OptPrice = OptPrice;
            size++;

        }
        public Options()
        {
            this("",0.0);
        }
        public Options (String OptName)
        {
            this(OptName, 0.0);
        }
        public Options (double OptPrice)
        {
            this("", OptPrice);
        }
        //Methods
        @Override
        public String toString()
        {
            StringBuffer sb1 = new StringBuffer();
            sb1.append("Name: ");
            sb1.append(getOptName());
            sb1.append(", Price: ");
            sb1.append(getOptPrice());

            return sb1.toString();
        }

////////////////// GETTERS AND SETTERS ////////////////////////////////////////////////////////////////////////////////
        protected String getOptName() {
            return OptName;
        }

        protected void setOptName(String optName) {
            OptName = optName;
        }

        protected double getOptPrice() {
            return OptPrice;
        }

        protected void setOptPrice(double optPrice) {
            this.OptPrice = optPrice;
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }





}
