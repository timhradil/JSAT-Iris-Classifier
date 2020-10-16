/*
* A very simple classifier of the Iris data set using JSAT.
* 
* @author Tim Hradil
*/

import java.io.File;
import jsat.ARFFLoader;
import jsat.DataSet;
import jsat.classifiers.CategoricalResults;
import jsat.classifiers.ClassificationDataSet;
import jsat.classifiers.Classifier;
import jsat.classifiers.DataPoint;
import jsat.classifiers.bayesian.NaiveBayes;

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
		
		ClassificationDataSet cDataSet = new ClassificationDataSet(dataSet, 0);

		int errors = 0;
		Classifier classifier = new NaiveBayes();
		classifier.trainC(cDataSet);

		for(int i = 0; i < dataSet.getSampleSize(); i++){
			DataPoint dataPoint = cDataSet.getDataPoint(i);
			int actual = cDataSet.getDataPointCategory(i);

			CategoricalResults prediction = classifier.classify(dataPoint);
			int predicted = prediction.mostLikely();
			if(predicted != actual)
				errors++;
			System.out.println("Sample: " + i + " | Actual: " + actual + " | Predicted: " + predicted + " | Confidence: "+ prediction.getProb(predicted));
		}

		System.out.println("There were " + errors + " made. This gives an error rate of " + 100.0*errors/dataSet.getSampleSize() + "%");
	}
}
