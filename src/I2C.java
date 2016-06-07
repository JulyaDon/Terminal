import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Разбиваем каждое число на 2 части
 */
public class I2C {
public int[] DataRequest () {
    String InitialNumbers; //числа, которые получаем на вход
    String[] SplittedInitialNumbers;
    int[] IntSplittedInitialNumbers = new int[3];
    int mask = 0b11111111;
    int[] Package = new int[8];

    try {
        FileReader fr = new FileReader("src\\1.txt");
        BufferedReader br = new BufferedReader(fr);
        InitialNumbers = br.readLine();
        fr.close();
    }
    catch(Exception ex){
        InitialNumbers="0-0-0";
    }

    //System.out.println(InitialNumbers);

    SplittedInitialNumbers = InitialNumbers.split("-"); //разбиваем их на массив из трех строк


    for(int i=0,j=0; i<SplittedInitialNumbers.length; i++) {


        if(!SplittedInitialNumbers[i].isEmpty()) {

            IntSplittedInitialNumbers[j] = Integer.parseInt(SplittedInitialNumbers[i]); //делаем массив целых чисел


            if (SplittedInitialNumbers[i].isEmpty()) {

                IntSplittedInitialNumbers[j] = -1 * IntSplittedInitialNumbers[j];

            }
            else{
                IntSplittedInitialNumbers[j] = IntSplittedInitialNumbers[j];
            }

            //System.out.println(Integer.toString(IntSplittedInitialNumbers[j], 2)); //они же в двуичной системе
            j++;
        }
    }
    //Заголовок
    Package[0] = 0;
    Package[1] = 0;
    //Разбиваем первое число
    Package[2] = (IntSplittedInitialNumbers[0]>>8);
    Package[3] = IntSplittedInitialNumbers[0]&mask;
    //Разбиваем второе число
    Package[4] = (IntSplittedInitialNumbers[1]>>8);
    Package[5] = IntSplittedInitialNumbers[1]&mask;
    //Разбиваем третье число
    Package[6] = (IntSplittedInitialNumbers[2]>>8);
    Package[7] = IntSplittedInitialNumbers[2]&mask;

    return Package;
}

}
