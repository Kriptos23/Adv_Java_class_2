package Scale;

import Adapter.proxyAutomobile;
import Model.Automobile;

public class EditOptions extends proxyAutomobile implements Runnable
{
    /**
     *  EditOption class that used Synchronized and Non-Synchronized methods with
     *  switch option to alter the object
     *
     *  Colors to change already defined
     *
     *  Also, new useful methods were added like getCarSetOfModels() that returns a car using a String name
     *  from LinkedHashMap SetOfModels
     * **/

    private Thread t;
    private Automobile auto;
    private boolean DEBUG = true;
    private String modelName;
    private String [] args;
    private int operation;

    public EditOptions(String modelName, int operation, String [] args)
    {
        this.modelName = modelName;
        this.operation = operation;
        this.args = args;
        this.t = new Thread(this);
        this.auto = getCarSetOfModels(modelName);
    }

    public Automobile getAuto() {
        return auto;
    }

    public Thread getT() {
        return t;
    }

    @Override
    public void run()
    {
        ops();
    }

    public void ops()
    {
        Helper helper = new Helper();

        switch (operation)
        {
            case 0:
                //Call method in helper class
                System.out.println("Thread Non syn - 1");
                helper.UpdateOptionNonSyn(auto, args[0],"Blue", "Green (NonSyn-1)", Integer.parseInt(args[1]));
                //Update the option name from blue to dark blue - non synchronized operation
                break;
            case 1:
                //Call method in helper class
                System.out.println("Thread Non syn - 2");
                helper.UpdateOptionNonSyn(auto, args[0],"Blue", "Green (NonSyn-2)", Integer.parseInt(args[1]));
                //Update the option name from blue to dark red	- non synchronized operation
                break;
            case 2:
                //Call method in helper class
                System.out.println("Thread syn - 1");
                helper.UpdateOptionSyn(auto, args[0],"Blue", "Red (Syn-1)", Integer.parseInt(args[1]));
                //Update the option name from blue to dark blue - synchronized operation
                break;
            case 3:
                //Call method in helper class
                System.out.println("Thread syn - 2");
                helper.UpdateOptionSyn(auto, args[0],"Blue", "Red (Syn-2)", Integer.parseInt(args[1]));
                //Update the option name from blue to red - synchronized operation
                break;
        }
    }
}
