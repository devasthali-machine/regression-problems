/**
 * https://spark.apache.org/docs/latest/ml-classification-regression.html#linear-regression
 */
public class CovidCaseLinearRegressionSpark {
//
//    public static void main(String[] args) {
//        Dataset<Row> training = spark.read().format("libsvm")
//            .load("data/mllib/sample_linear_regression_data.txt");
//
//        LinearRegression lr = new LinearRegression()
//            .setMaxIter(10)
//            .setRegParam(0.3)
//            .setElasticNetParam(0.8);
//
//// Fit the model.
//        LinearRegressionModel lrModel = lr.fit(training);
//
//// Print the coefficients and intercept for linear regression.
//        System.out.println("Coefficients: "
//            + lrModel.coefficients() + " Intercept: " + lrModel.intercept());
//
//// Summarize the model over the training set and print out some metrics.
//        LinearRegressionTrainingSummary trainingSummary = lrModel.summary();
//        System.out.println("numIterations: " + trainingSummary.totalIterations());
//        System.out.println("objectiveHistory: " + Vectors.dense(trainingSummary.objectiveHistory()));
//        trainingSummary.residuals().show();
//        System.out.println("RMSE: " + trainingSummary.rootMeanSquaredError());
//        System.out.println("r2: " + trainingSummary.r2());
//    }
}
