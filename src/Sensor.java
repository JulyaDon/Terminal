/**
 * Created by July on 03.06.2016.
 */
public class Sensor {
    private int Data;
    private  int Number;
    private int Type;

    public int GetData(){
        return Data;
    }
    public int GetNumber(){
        return Number;
    }
    public int GetType(){
        return Type;
    }

    public void SetData(int Data){ this.Data = Data; }
    public void SetNumber(int Number){
        this.Number = Number;
    }
    public void SetType(int Type){
        this.Type = Type;
    }
}
