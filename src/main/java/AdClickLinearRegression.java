import com.opencsv.CSVReader;
import com.talkot.model.AdMetrics;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class AdClickLinearRegression {

    public static void main(String[] args) throws IOException {
        List<AdMetrics> adMetricsData = getAdMetrics();

        double[][] encodedData = new double[adMetricsData.size()][2];
        int row = 0;

        for (AdMetrics a: adMetricsData) {
            encodedData[row][0] = a.getId();
            encodedData[row][1] = a.getImpressions();
            row++;
        }

        System.out.println(adMetricsData.size());
        System.out.println(encodedData.length);

        for (int i = 0; i < encodedData.length; i++) {
            System.out.println(encodedData[i][0] + ", " + encodedData[i][1]);
        }

        System.out.println("=== impressions regression ==== ");
        SimpleRegression simpleRegression = new SimpleRegression(true);
        simpleRegression.addData(encodedData);
        System.out.println("slope = " + simpleRegression.getSlope());
        System.out.println("bias term = " + simpleRegression.getIntercept());

        for (int i = 152; i < 152 + 30; i++) {
            System.out.println(i + " : " + simpleRegression.predict(i));
        }
    }

    private static List<AdMetrics> getAdMetrics() throws IOException {
        List<AdMetrics> adMetricsData = new LinkedList<>();

        var resourceAsStream = AdClickLinearRegression.class.getResourceAsStream("/ad_click_data_train.csv");

        CSVReader reader = new CSVReader(new InputStreamReader(resourceAsStream), ',');

        String[] record = null;
        int i = 0;

        while ((record = reader.readNext()) != null) {
            if (i != 0) {
                AdMetrics adMetrics = AdMetrics.builder()
                    .id(i)
                    .date(record[0])
                    .impressions(Integer.parseInt(record[1]))
                    .clicks(Integer.parseInt(record[2]))
                    .ctr(Float.parseFloat(record[3]))
                    .build();

                adMetricsData.add(adMetrics);
            }
            i++;
        }
        return adMetricsData;
    }
}
