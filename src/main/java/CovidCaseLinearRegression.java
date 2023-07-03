import org.apache.commons.math3.stat.regression.SimpleRegression;

public class CovidCaseLinearRegression {

    /**
     * https://www.codingame.com/playgrounds/3771/machine-learning-with-java---part-1-linear-regression
     */
    public static void main(String[] args) {
        // creating regression object, passing true to have intercept term
        SimpleRegression simpleRegression = new SimpleRegression(true);

        double[][] dayCovidCases = {
            {1, 2},
            {2, 3},
            {3, 4},
            {4, 5},
            {5, 6}
        };
        // passing data to the model
        // model will be fitted automatically by the class
        simpleRegression.addData(dayCovidCases);

        // querying for model parameters
        System.out.println("slope = " + simpleRegression.getSlope());
        System.out.println("intercept/bias term = " + simpleRegression.getIntercept());

        // trying to run model for unknown data
        //day 1.5, covid cases 2.5
        System.out.println("covid cases for 1.5 = " + simpleRegression.predict(1.5));
    }
}
