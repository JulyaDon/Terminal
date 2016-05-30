/**
 * Created by July on 22.05.2016.
 */
public class Terminal {

    Moisture MSensor = new Moisture();
    Photo PSensor = new Photo();
    Temperature TSensor = new Temperature();

    public int[] getNumbersBack(){
    I2C bus = new I2C();
    int[] AllData = new int[3];
    int[] Package = bus.DataRequest();

    //Возвращаем числа обратно, чтоб проанализировать их
    AllData[0] = Package[2]<<8|Package[3];
    AllData[1] = Package[4]<<8|Package[5];
    AllData[2] = Package[6]<<8|Package[7];

    return AllData;
}
public void getType(){
    //Разбиваем числа на тип
    //Выводим их на экран
    int Type;
    int mask1 = 0b1110000000000000; //маски для сдвига
    int mask2 = 0b000111;
    int mask3 = 0b0000001111111111;
    int[] AllData = getNumbersBack();

    for(int i = 0; i<AllData.length; i++) {
        Type = AllData[i] & mask1;
        Type >>= 13;
        System.out.println("Type of " +  (i+1) + " number: " + Type);
        switch (Type) {
            case 1:
                MSensor.MoistureNumber = AllData[i] >> 10 & mask2;
                MSensor.MoistureData = AllData[i] & mask3;
                break;


            case 2:
                PSensor.PhotoNumber = AllData[i] >> 10 & mask2;
                PSensor.PhotoData = AllData[i] & mask3;
                break;

            case 3:
                TSensor.TemperatureNumber = AllData[i] >> 10 & mask2;
                TSensor.TemperatureData = AllData[i] & mask3;
                break;

            default:
                System.out.println("Все плохо");
                break;
        }
        /*if(Type == 1){
            MSensor.MoistureNumber = AllData[i]>>10&mask2;
            MSensor.MoistureData = AllData[i]&mask3;

        }
        if(Type == 2){
            PSensor.PhotoNumber = AllData[i]>>10&mask2;
            PSensor.PhotoData = AllData[i]&mask3;
        }
        if(Type == 3){
            TSensor.TemperatureNumber = AllData[i]>>10&mask2;
            TSensor.TemperatureData = AllData[i]&mask3;
        }*/
    }
}
}
