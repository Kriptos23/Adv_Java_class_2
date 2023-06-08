package Adapter;

public interface UpdateAuto
{
//    void UpdateOptionSetName(String OptSN, String name);
//    void UpdateOptionPrice(String OptSN, String OptN, int price);

    void UpdateOptionSetName(String ModelName, String OptSN, String name);

    void UpdateOptionPrice(String ModelName, String OptSN, String OptN, int price);

}
