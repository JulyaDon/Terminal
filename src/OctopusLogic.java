/**
 * Created by July on 22.05.2016.
 */
public class OctopusLogic {
    public static void main(String[] args) {
        I2C I2Cbus = new I2C();
        Terminal Terminal1 = new Terminal();
        DBConnector myDatabase = new DBConnector();

        int[] AllData = Terminal1.getNumbersBack();
        int[] Package = I2Cbus.DataRequest();
        Terminal1.getType();

        for(int i = 0; i<Package.length; i++) {
            System.out.print(Package[i] + " ");
        }
        System.out.println();
        for(int i = 0; i<AllData.length; i++){
            System.out.print(AllData[i] + " ");
        }
        System.out.println();
        for(int i = 0; i<Terminal1.sensors.length; i++){
            System.out.println(Terminal1.sensors[i].GetType());
            System.out.println(Terminal1.sensors[i].GetNumber());
            System.out.println(Terminal1.sensors[i].GetData());
        }

/*
            myDatabase.DB_INSERT(
                    Terminal1.sensors[0].GetType(),
                    Terminal1.sensors[0].GetNumber(),
                    Terminal1.sensors[0].GetData(),
                    1);
*/
        for(int i = 0; i<Terminal1.sensors.length; i++) {
            myDatabase.DB_INSERT(Terminal1.sensors[i], 1);
        }

    }
}
