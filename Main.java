import java.io.File;
import jsat.ARFFLoader;
import jsat.DataSet;
import jsat.classifiers.DataPoint;

class Main {
    public static void main(String[] args) {
        File file = new File("iris.arff");
		DataSet dataSet = ARFFLoader.loadArffFile(file);

		System.out.println("The categorical features are:");
		for(int i = 0; i < dataSet.getNumCategoricalVars(); i++)
			System.out.println("\t" + dataSet.getCategoryName(i));
		
		System.out.println("\nThe numerical features are:");
		for(int i = 0; i < dataSet.getNumNumericalVars(); i++)
			System.out.println("\t" + dataSet.getNumericName(i));
		
		System.out.println("\nThere are "+dataSet.getSampleSize()+" samples.\n");

		System.out.print("The first 10 samples are:\n");
		for(int i = 0; i < 10; i++)
			System.out.println(dataSet.getDataPoint(i));
    }

	
}