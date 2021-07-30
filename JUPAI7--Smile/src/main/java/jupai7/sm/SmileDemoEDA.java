package jupai7.sm;

import smile.classification.RandomForest;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.data.measure.NominalScale;
import smile.data.vector.IntVector;
import smile.plot.swing.Histogram;
import smile.validation.metric.Accuracy;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class SmileDemoEDA {
    String totalPath = "src/main/resources/data/titanic_full.csv";
    String trainPath = "src/main/resources/data/titanic_train.csv";
    String testPath = "src/main/resources/data/titanic_test.csv";
    
    
    public static void main(String[] args) throws IOException {
        SmileDemoEDA sd = new SmileDemoEDA ();
        PassengerProvider pProvider = new PassengerProvider ();
        //DataFrame trainData = pProvider.readCSV (sd.trainPath);
        //DataFrame testData = pProvider.readCSV (sd.testPath);
        DataFrame full = pProvider.readCSV(sd.totalPath);
        DataFrame trainData = full.slice(0, 900);
        DataFrame testData = full.slice(901, 1309);
         
       // System.out.println(full.structure());
        System.out.println (trainData.structure ());
        System.in.read();
        System.out.println (trainData.summary ());
        System.in.read();
//         
        trainData = Datapreparation(trainData);
        testData = Datapreparation(testData);
        
        trainData = trainData.merge (IntVector.of ("Gender",
                encodeCategory (trainData, "Sex")));
        trainData = trainData.merge (IntVector.of ("PClassValues",
                encodeCategory (trainData, "Pclass")));

        System.out.println ("=======Encoding Non Numeric Data==============");
        System.out.println (trainData.structure ());
        //System.out.println (trainData);
         System.in.read();
        System.out.println ("=======Dropping the Name, Pclass, and Sex Columns==============");
        trainData = trainData.drop ("Name");
        trainData=trainData.drop("Pclass");
        trainData=trainData.drop("Sex");
        System.out.println (trainData.structure ());
         System.in.read();
        System.out.println (trainData.summary ());
         System.in.read();
        trainData = trainData.omitNullRows ();
        System.out.println ("=======After Omitting null Rows==============");
        System.out.println (trainData.summary ());
         System.in.read();
        System.out.println ("=======Start of Explaratory Data Analysis==============");
        try {
            eda (trainData);
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace ();
//        }
        RandomForest model = RandomForest.fit(Formula.lhs("Survived"), trainData);
        System.out.println("feature importance:");
        System.out.println(Arrays.toString(model.importance()));
        System.out.println(model.metrics ());
        //TODO load test data to validate model
//            int [][] result = model.test(TestData);
//            RandomForest model1 = model.prune(TestData);
//            model1.importance();
//            model1.metrics();
            testModel(testData, model);
            
            
        }
    }
    
    public static void testModel(DataFrame testData,RandomForest model){
        int[][] results=model.test (testData);
        RandomForest model1= model.prune (testData);
        System.out.println("feature importance:");
        System.out.println(Arrays.toString(model1.importance()));
        System.out.println(model1.metrics ());
        int[] pred = model.predict(testData);
        System.out.println("Accuracy= "+ Accuracy.of(testData.column("survived").toIntArray(), pred));
    }
    public static DataFrame Datapreparation(DataFrame new_df){
        new_df = new_df.merge(IntVector.of("Gender", encodeCategory(new_df, "Sex")));
        new_df = new_df.omitNullRows();
        new_df = new_df.drop("Name");
        new_df = new_df.drop("Sex");
        return new_df;
    }
    public static int[] encodeCategory(DataFrame df, String columnName) {
        String[] values = df.stringVector (columnName).distinct ().toArray (new String[]{});
        int[] pclassValues = df.stringVector (columnName).factorize (new NominalScale (values)).toIntArray ();
        return pclassValues;
    }

    private static void eda(DataFrame titanic) throws InterruptedException, InvocationTargetException {
        titanic.summary ();
        DataFrame titanicSurvived = DataFrame.of (titanic.stream ().filter (t -> t.get ("Survived").equals (1)));
        DataFrame titanicNotSurvived = DataFrame.of (titanic.stream ().filter (t -> t.get ("Survived").equals (0)));
        titanicNotSurvived.omitNullRows ().summary ();
        titanicSurvived = titanicSurvived.omitNullRows ();
        titanicSurvived.summary ();
        int size = titanicSurvived.size ();
        System.out.println (size);
        Double averageAge = titanicSurvived.stream ()
                .mapToDouble (t -> t.isNullAt ("Age") ? 0.0 : t.getDouble ("Age"))
                .average ()
                .orElse (0);
        System.out.println (averageAge.intValue ());
        Map map = titanicSurvived.stream ()
                .collect (Collectors.groupingBy (t -> Double.valueOf (t.getDouble ("Age")).intValue (), Collectors.counting ()));

        double[] breaks = ((Collection<Integer>) map.keySet ())
                .stream ()
                .mapToDouble (l -> Double.valueOf (l))
                .toArray ();

        int[] valuesInt = ((Collection<Long>) map.values ())
                .stream ().mapToInt (i -> i.intValue ())
                .toArray ();

        Histogram.of (titanicSurvived.doubleVector ("Age").toDoubleArray (), 15, false)
                .canvas ().setAxisLabels ("Age", "Count")
                .setTitle ("Age frequencies among surviving passengers")
                .window ();
        Histogram.of (titanicSurvived.intVector ("PClassValues").toIntArray (), 4, true)
                .canvas ().setAxisLabels ("Classes", "Count")
                .setTitle ("Pclass values frequencies among surviving passengers")
                .window ();
        //Histogram.of(values, map.size(), false).canvas().window();
        System.out.println (titanicSurvived.schema ());
        //////////////////////////////////////////////////////////////////////////

    }
}
