import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class test {

    public static void main(String[] args){
    String path = "/Users/spy/Desktop/CN311-TheBigBagHave/all_countries.csv";
    ArrayList<String> list = new ArrayList<String>();
    try { 
            CSVReader reader = new CSVReader(new FileReader(path));
            File file = new File(path);
            BufferedReader data = new BufferedReader(new FileReader(file));
            String line;
            while ((line = data.readLine()) != null) {
                String[] arr = line.split(",");
                list.add(arr[1]);
            }
            System.out.println(list);
        } catch (Exception error){
            System.out.println("error");
        }
}
}

