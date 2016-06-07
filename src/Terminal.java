/**
 * Created by July on 22.05.2016.
 */
public class Terminal {

    Sensor[] sensors = new Sensor[3];

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
    //Разбиваем числа на тип, номер и данные
        //Закидываем их в Sensor()
    int mask1 = 0b1110000000000000; //маски для сдвига
    int mask2 = 0b000111;
    int mask3 = 0b0000001111111111;
    int[] AllData = getNumbersBack();

    for(int i = 0; i<AllData.length; i++) {
        sensors[i] = new Sensor();
        sensors[i].SetType((AllData[i] & mask1)>>13);
        sensors[i].SetNumber(AllData[i]>>10&mask2);
        sensors[i].SetData(AllData[i]&mask3);
    }
}
}
