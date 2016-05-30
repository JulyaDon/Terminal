/**
 * Created by July on 22.05.2016.
 */
public class OctopusLogic {
    public static void main(String[] args) {
        I2C test = new I2C();
        Terminal test2 = new Terminal();

        int[] AllData = test2.getNumbersBack();
        int[] Package = test.DataRequest();
        test2.getType();

        for(int i = 0; i<Package.length; i++) {
            System.out.print(Package[i] + " ");
        }
        System.out.println();
        for(int i = 0; i<AllData.length; i++){
            System.out.print(AllData[i] + " ");
        }
        System.out.println();
        System.out.println(test2.MSensor.MoistureNumber);
        System.out.println(test2.PSensor.PhotoNumber);
        System.out.println(test2.TSensor.TemperatureNumber);

        System.out.println(test2.MSensor.MoistureData);
        System.out.println(test2.PSensor.PhotoData);
        System.out.println(test2.TSensor.TemperatureData);
    }
}
