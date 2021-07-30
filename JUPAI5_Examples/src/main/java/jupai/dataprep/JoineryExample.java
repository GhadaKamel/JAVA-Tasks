package jupai.dataprep;

import joinery.DataFrame;

import java.io.IOException;
import java.util.Date;

public class JoineryExample {
    public static void main(String args[]){
        try {

            DataFrame<Object>  df1= DataFrame.readCsv ("src/main/resources/data/vgsales.csv")
                    .retain("Year", "NA_Sales","EU_Sales","JP_Sales")
                    .describe ();
           System.out.println (df1.toString ());
            System.out.println ("=========================================================================================");
      //How to Use System.in in Java. At times you might need to read console input provided by the user from the keyboard.
     // The System.in field permits you to read input from the keyboard.
    //The input can be converted into a stream of characters and then buffered so that all characters up to but not including the Enter key can be presented to the program.
            System.in.read();
            DataFrame<Object>  df= DataFrame.readCsv ("src/main/resources/data/vgsales.csv")
                    .retain("Year", "NA_Sales","EU_Sales","JP_Sales")
                    .sortBy("Year")
                    .groupBy(row ->row.get(0))
                    .mean ();
            df.iterrows ().forEachRemaining (System.out::println);

            System.out.println ("=========================================================================================");
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
