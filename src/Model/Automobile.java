package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Automobile implements Serializable
{
    private String name;
    private double basePrice;
    private ArrayList<OptionSet> optS;
    private ArrayList<OptionSet.Options> choices;


    public Automobile(String name, double basePrice, int size)
    {
        this.name = name;
        this.basePrice = basePrice;
        this.optS = new ArrayList<>(size);
        this.choices = new ArrayList<>();
    }

    public Automobile() {this("", 0.0, 0);}
    public Automobile(String name, double basePrice) {
        this(name, basePrice, 0);
    }

    public Automobile(ArrayList<OptionSet> optS)
    {
        this.name = "";
        this.basePrice = 0;
        this.optS = optS;
    }

    public Automobile(double basePrice) {
        this("", basePrice, 0);
    }

    public Automobile(String name) {
        this(name, 0, 0);
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Name: ");
        sb.append(getName());
        sb.append(", Base price: ");
        sb.append(getBasePrice());
        sb.append(", OptionSet (Array): ");
        sb.append((getOptS()));
//        sb.append("End\n");

        return sb.toString();
    }

//    public OptionSet FindColorOptionSet() {
//        return this.FindSet("color");
//    }
//            OptionSet color = Ford.FindSet("color");
//            OptionSet color = Ford.FindColorOptionSet();

    public synchronized void addOptionSet(String name)
    {
        OptionSet newOptionSet = new OptionSet(name, 0);
        optS.add(newOptionSet);
    }

    public synchronized OptionSet FindSet(String name)
    {
        for (OptionSet i : this.getOptS())
        {

            if (i.getOptionSetName().equals(name))
            {
                System.out.println(name + " OptionSet found Successfully");
                return i;
            }
        }
        System.out.println("No such OptionSet with the name "  + name);
        return null;
    }

    public synchronized OptionSet.Options FindOption(String name)
    {
        for (OptionSet i : this.getOptS())
        {
            for (OptionSet.Options j : i.getOpt())
            {
                if (j.getOptName().equals(name))
                {
                    System.out.println(name + " Option found successfully");
                    return j;
                }
            }
        }
        System.out.println("No such Option with that name");
        return null;
    }

    public synchronized OptionSet.Options FindOption(String name, String optionSetName)
    {
        for (OptionSet i : this.getOptS())
        {
            if(i.getOptionSetName().equals(optionSetName))
            {
                for (OptionSet.Options j : i.getOpt())
                {
                    if (j.getOptName().equals(name))
                    {
                        System.out.println(name + " Option found successfully");
                        return j;
                    }
                }
            }
        }
        System.out.println("No such Option with that name");
        return null;
    }


    public synchronized void DeleteSet(String name)
    {
        boolean found = false;//To indicate that we found the Set
        int length = optS.size();
        for (int i = 0; i < length; i++)
        {
            if (optS.get(i).getOptionSetName().equals(name))//To find
            {
                System.out.println(name + " Option with name found successfully to delete");
                optS.remove(i);
                found = true;
                break;
            }
        }

        if (!found){
            System.out.println("No such OptionSet with the name "  + name + " to delete");
        }
    }

    public synchronized void DeleteOption(String nameD, String SetName)
    {
        if (this.FindSet(SetName) != null)
        {
            this.FindSet(SetName).deleteOption(nameD);
        }
        else
        {
            System.out.println("No such OptionSet with the name "  + SetName + " to delete");
        }
    }

    public synchronized void UpdateSet(String name, String NewName, ArrayList<OptionSet.Options> arr)
    {
        for (OptionSet i : this.getOptS())
        {
            if (i.getOptionSetName().equals(name))
            {
                i.setOptionSetName(NewName);
                i.setOpt(arr);
                System.out.println(name + " OptionSet found Successfully");

            }
        }
        System.out.println("No such OptionSet with the name "  + name);
    }

    // updates Option, when no OptionSet name is given. Looks in all OptionSets, and all Options

    public synchronized void UpdateOption(String OptionSetName,String oldName, String NewName, int NewPrice)
    {
        boolean found = false;
        for (OptionSet i : this.getOptS())
        {
            if (i.getOptionSetName().equals(OptionSetName))
            {
                i.UpdateOption(oldName, NewName, NewPrice);
//                notifyAll();
                found = true;
            }
        }
        if (!found)
        {
            System.out.println("No such Option with the name" + oldName);
        }
    }
    public void UpdateOptionNonSynch(String OptionSetName,String oldName, String NewName, int NewPrice)
    {
        boolean found = false;
        for (OptionSet i : this.getOptS())
        {
            if (i.getOptionSetName().equals(OptionSetName))
            {
                    i.UpdateOption(oldName, NewName, NewPrice);
                    found = true;

            }
        }
        if (!found)
        {
            System.out.println("No such Option with the name" + oldName);
        }
    }
    public synchronized void UpdateOptionWithoutMessage(String OptionSetName,String oldName, String NewName, int NewPrice)
    {
        boolean found = false;
        for (OptionSet i : this.getOptS())
        {
            if (i.getOptionSetName().equals(OptionSetName))
            {
                i.UpdateOptionWithoutMessage(oldName, NewName, NewPrice);
                found = true;
            }
        }
        if (!found)
        {
            System.out.println("No such Option with the name" + oldName);
        }
    }

    // Might have bugs
    public synchronized void addOption(String optionSetName, String optionName, double optionPrice)//aka build option
    {
        // 1. find the OptionSet with SetName:
        for(OptionSet i : this.getOptS())
        {
            if (i.getOptionSetName().equals(optionSetName))
            {
                i.addOption(optionSetName, optionName, optionPrice);
            }
        }

        // 2. use addOption() method of OptionSet found in 1. step

    }
    ////////New Methods///////

    public synchronized void UpdateOptSetName(String optSet, String NewName)
    {
        for (OptionSet i : optS)
        {
            if (i.getOptionSetName().equals(optSet))
            {
                i.setOptionSetName(NewName);
            }
        }
    }

    public synchronized void UpdateOptionPrice(String OptSN, String OptN, double price)
    {
        for(OptionSet j : optS)
        {
            if(j.getOptionSetName().equals(OptSN))
            {
                for (OptionSet.Options i : j.getOpt())
                {
                    if (i.getOptName().equals(OptN))//To find
                    {
                        i.setOptPrice(price);
                    }
                }
            }
        }
    }




////////////////// PRINT FUNCTIONS ////////////////////////////////////////////////////////////////////////////////////

    public void printAutoName(){
        System.out.println(getName());
    }
    public void printAutoPrice(){
        System.out.println(getBasePrice());
    }
    public void printOptionSets(){
        System.out.println(getOptS());
    }
    public void printOneOptionSet(String name){
        System.out.println(getOneOptionSet(name));
    }
    public void printOneOption(int x, String name){
        for (OptionSet i : optS)
        {
            if(i.getOptionSetName().equals(name))
            {
                i.printOneOption(x);
            }
        }
    }
    public void printAuto(){
        StringBuffer sb = new StringBuffer();
        sb.append("Name: ");
        sb.append(getName());
        sb.append(", Base price: ");
        sb.append(getBasePrice());
        sb.append(", OptionSet (Array): ");
        sb.append((getOptS()));
        System.out.println(sb);
    }


    /************************* New Methods **************************
     ******** OptionChoice GETTERS AND SETTERS + getTotalPrice ******
     ***************************************************************/

    public OptionSet.Options getOptionChoice(String SetName)
    {
        for (OptionSet i : this.getOptS())
        {
            if (i.getOptionSetName().equals(SetName))
            {
                return i.getOptionChoice();
            }
        }
        System.out.println("No such OptionSet with the name "  + SetName);
        return null;
    }
    public void setOptionChoice(String SetName, String OptName)
    {
        for (OptionSet i : this.getOptS())
        {
            if (i.getOptionSetName().equals(SetName))
            {
                i.setOptionChoice(OptName);
                choices.add(i.getOptionChoice());
                return;
            }
        }
        System.out.println("No such OptionSet with the name "  + SetName);
    }
    //Overload to change existing Option if user know the name
    public double getOptionChoicePrice(String SetName)
    {
        OptionSet.Options  optionChoice = getOptionChoice(SetName);
        if (optionChoice == null)
        {
            return 0;
        }
        return optionChoice.getOptPrice();
    }
    public double getTotalPrice()
    {
        double total = basePrice;
        for (OptionSet.Options i : choices)
        {
            total += i.getOptPrice();
        }
        return total;
    }

    /****************************************************************
     ******** Automobile GETTERS AND SETTERS ***********************
     ***************************************************************/

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public ArrayList<OptionSet> getOptS() {
        return optS;
    }
    public synchronized void setOptS(ArrayList<OptionSet> optS) {
        this.optS = optS;
    }
    public synchronized OptionSet getOneOptionSet(String name){
        for (OptionSet i : this.getOptS())
        {

            if (i.getOptionSetName().equals(name))
            {
                System.out.println(name + " OptionSet found Successfully");
                return i;
            }
        }
        System.out.println("No such OptionSet with the name "  + name);
        return null;
    }
    //Overload
    public synchronized OptionSet getOneOptionSet(int x){
        int count = 1;
        for (OptionSet i : this.getOptS())
        {
            if (count==x)
            {
                System.out.println(name + " OptionSet found Successfully");
                return i;
            }
            count++;
        }
        System.out.println("No such OptionSet with the number "  + x);
        return null;
    }
    public synchronized void setOneOptionSet(String name, OptionSet option)
    {
        for(OptionSet i : this.getOptS())
        {
            if(i.getOptionSetName().equals(name))
            {
                System.out.println(name + " OptionSet found Successfully");
                i = option;
                break;
            }
        }
        System.out.println("No such OptionSet with the name "  + name);
    }
    public synchronized void setOneOptionSet(int x, OptionSet option)
    {
        int count = 1;
        for (OptionSet i : this.getOptS())
        {
            if (count==x)
            {
                System.out.println(name + " OptionSet found Successfully");
                i = option;
                break;
            }
            count++;
        }
        System.out.println("No such OptionSet with the number "  + x);
    }
    public synchronized int getOptionSetLength(){
        return optS.size();
    }
    public synchronized int getOptionLength(String name){
        for (OptionSet i : this.getOptS())
        {
            if(i.getOptionSetName().equals(name))
            {
                return i.getOptionsLength();
            }
        }
        System.out.println("No such OptionSet with that name" + name +"to return a OptionSet length");
        return 0;
    }
    //Overload
    public synchronized int getOptionLength(int x){
        int count = 1;
        for (OptionSet i : this.getOptS())
        {
            if(count==x)
            {
                return i.getOptionsLength();
            }
            count++;
        }
        System.out.println("No such OptionSet with number" + x + " to return a OptionSet length");
        return 0;
    }
    public synchronized String getOptionSetName(int x){
        int count = 1;
        for (OptionSet i : this.getOptS())
        {
            if(count==x)
            {
                return i.getOptionSetName();
            }
            count++;
        }
        System.out.println("No such OptionSet with number" + x + " to return a OptionSet length");
        return null;
    }
    public synchronized void setOptionSetName(int x, String name){
        int count = 1;
        for (OptionSet i : this.getOptS())
        {
            if(count==x)
            {
                i.setOptionSetName(name);
                break;
            }
            count++;
        }
        System.out.println("No such OptionSet with number" + x + " to return a OptionSet length");
    }
    public synchronized String getOptionName(int x, String OptionSetName){
        int count = 1;
        for (OptionSet i : this.getOptS())
        {
            if(i.getOptionSetName().equals(OptionSetName))
            {
                for (OptionSet.Options j : i.getOpt())
                {
                    if (count==x)
                    {
                        return j.getOptName();
                    }
                }

                count++;
            }
        }
        System.out.println("No such OptionSet with number" + x + " to return a OptionSet length");
        return null;
    }
    public synchronized void setOptionName(int x, String OptionSetName, String newName){
        int count = 1;
        for (OptionSet i : this.getOptS())
        {
            if(i.getOptionSetName().equals(OptionSetName))
            {
                for (OptionSet.Options j : i.getOpt())
                {
                    if (count==x)
                    {
                        j.setOptName(newName);
                        break;
                    }
                }

                count++;
            }
        }
        System.out.println("No such OptionSet with number" + x + " to return a OptionSet length");
    }
    public synchronized double getOptionPrice(int x, String OptionSetName){
        int count = 1;
        for (OptionSet i : this.getOptS())
        {
            if(i.getOptionSetName().equals(OptionSetName))
            {
                for (OptionSet.Options j : i.getOpt())
                {
                    if (count==x)
                    {
                        return j.getOptPrice();
                    }
                }

                count++;
            }
        }
        System.out.println("No such OptionSet with number" + x + " to return a OptionSet length");
        return 0;
    }
    public synchronized void setOptionPrice(int x, String OptionSetName, double newPrice){
        int count = 1;
        for (OptionSet i : this.getOptS())
        {
            if(i.getOptionSetName().equals(OptionSetName))
            {
                for (OptionSet.Options j : i.getOpt())
                {
                    if (count==x)
                    {
                        j.setOptPrice(newPrice);
                        break;
                    }
                }

                count++;
            }
        }
        System.out.println("No such OptionSet with number" + x + " to return a OptionSet length");
    }
    public synchronized void setOneOptionSetOption(int x, String name, String newName, double newPrice)
    {
        for(OptionSet i : this.getOptS())
        {
            if(i.getOptionSetName().equals(name))
            {
                int count = 1;
                for (OptionSet.Options j : i.getOpt())
                {
                    if (count==x)
                    {
                        j.setOptName(newName);
                        j.setOptPrice(newPrice);
                        break;
                    }
                    count++;
                }
            }
        }
        System.out.println("No such OptionSet with the name "  + name);
    }
//    public void setOptArr
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
