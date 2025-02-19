package org.firstinspires.ftc.teamcode.mech.CV;


import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.Point;

import org.opencv.imgproc.Imgproc;

import java.util.Collections;
import java.util.ArrayList;
import org.openftc.easyopencv.OpenCvPipeline;


@Config
public class SampleDetectionEdges extends OpenCvPipeline {
    public int width = 320;
    public int length = 240;
    public boolean run = true;
    public int angle_delta = 0;
    public int x_delta = 0;
    public int y_delta = 0;
    public static int low_sat = 240;
    public static int low_val = 240;
    public static double alpha = 2.0;
    public static double beta = 0;
    public static int iter_num = 5;


    public boolean run_continuous = true;
    public int threshold = 100;
    public int threshold_z = 120;
    public double prev_x = -1;
    public double prev_y = -1;
    public double prev_a = -1;
    public ArrayList<Double[]> prev_val = new ArrayList<Double[]>();

    public int color = 0;

    private Telemetry telemetry;

    public SampleDetectionEdges(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
    public void reset() {
        prev_x = -1;
        prev_y = -1;
        prev_a = -1;
        prev_val = new ArrayList<Double[]>();
    }
    public void setColor(int c){color = c;}

    @Override
    public Mat processFrame(Mat input) {
        if (run) {
//            Core.convertScaleAbs(input, input, alpha, beta);

            Mat dst = new Mat(input.rows(), input.cols(), input.type());
            Mat k1 = new Mat(input.rows(), input.cols(), input.type());

            input.copyTo(dst);
            //Imgproc.cvtColor(dst, dst, Imgproc.COLOR_BGR2HSV);

            Mat hueChannel = new Mat();
            Core.extractChannel(dst, dst, color); // 0 is the index for the Hue channel
            dst.copyTo(hueChannel);

            // Normalize the hue channel to make it more visible (optional)
            Mat normalizedHue = new Mat();
            Core.normalize(dst, dst, 0, 255, Core.NORM_MINMAX, CvType.CV_8U);

            Imgproc.blur(dst, dst, new Size(3, 3));
            Imgproc.threshold(dst, dst, threshold_z, 255, Imgproc.THRESH_TOZERO);
            Mat i2 = new Mat(input.rows(), input.cols(), input.type());
            Imgproc.threshold(dst, dst, threshold, 255, Imgproc.THRESH_BINARY);



            Mat out = new Mat(input.rows(), input.cols(), input.type());
            dst.copyTo(out);
            Mat edges = new Mat();
            Imgproc.Canny(dst, edges, 50, 150);

            Mat lines = new Mat();
            Imgproc.HoughLinesP(edges, lines, 1, Math.PI / 180, 5, 20, 20); // Use HoughLinesP for segments
            double maxLength = 0;
            double sMaxLength = 0;
            double tMaxLength = 0;
            double fMaxLength = 0;




            Point longestStart = null;
            Point longestEnd = null;
            Point secondLongestStart = null;
            Point secondLongestEnd = null;
            Point thirdLongestStart = null;
            Point thirdLongestEnd = null;
            Point fourthLongestStart = null;
            Point fourthLongestEnd = null;

            for (int i = 0; i < lines.rows(); i++) {
                double[] line = lines.get(i, 0);
                Point start = new Point(line[0], line[1]);
                Point end = new Point(line[2], line[3]);


                double length = Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2));

                if (length > maxLength) {
                    maxLength = length;
                    longestStart = start;
                    longestEnd = end;

                }
                else if (length > sMaxLength) {
                    sMaxLength = length;
                    secondLongestStart = start;
                    secondLongestEnd = end;

                }
                else if (length > tMaxLength) {
                    tMaxLength = length;
                    thirdLongestStart = start;
                    thirdLongestEnd = end;

                }
                else if (length > fMaxLength) {
                    fMaxLength = length;
                    fourthLongestStart = start;
                    fourthLongestEnd = end;

                }
            }

            if (longestStart != null && longestEnd != null && fourthLongestEnd != null) {
                prev_x = longestStart.x;
                //Point[] points = {longestStart, longestEnd, secondLongestStart, secondLongestEnd, thirdLongestStart, thirdLongestEnd, fourthLongestStart, fourthLongestEnd};

                // Get the bounding rectangle
                // Rect boundingRect = rectangle2(points,telemetry);


                // Draw the rectangle on the image
                //Imgproc.rectangle(input, boundingRect.tl(), boundingRect.br(), new Scalar(0, 255, 0), 2);

                prev_val.add(new Double[] {(longestStart.x + longestEnd.x)/2, (longestStart.y + longestEnd.y)/2, Math.atan((longestEnd.y - longestStart.y)/(longestEnd.x - longestStart.x))});
                if (prev_val.size() > 5) {
                    telemetry.addData("k",  getResult()[2]);

                }
                telemetry.addData("p",  Math.atan((longestEnd.y - longestStart.y)/(longestEnd.x - longestStart.x))/ (3.14) * 180);
                telemetry.update();
                // Imgproc.rectangle(input, longestStart, secondLongestEnd, new Scalar(0, 255, 0), 2);
                //Imgproc.rectangle(input, secondLongestStart, longestEnd, new Scalar(0, 255, 0), 2);
                Imgproc.line(k1, longestStart, longestEnd, new Scalar(255, 255, 255), 2);
                Imgproc.line(k1, secondLongestStart, secondLongestEnd, new Scalar(255, 255, 255), 2);
                Imgproc.line(k1, thirdLongestStart, thirdLongestEnd, new Scalar(255, 255, 255), 2); // Green color, thickness 2
                Imgproc.line(k1, fourthLongestStart, fourthLongestEnd, new Scalar(255, 255, 255), 2); // Green color, thickness 2
            }
            /*
            Imgproc.cvtColor(k1, k1, Imgproc.COLOR_BGR2GRAY);
            Imgproc.threshold(k1, k1, 240, 255, Imgproc.THRESH_TOZERO);

            final List<MatOfPoint> points = new ArrayList<>();
            final Mat hierarchy = new Mat();
            Imgproc.findContours(k1, points, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
            telemetry.addData("data: ", points.size());
            telemetry.addData("t1: "," minRect.center");
            RotatedRect minAreaRect;
            if (!points.isEmpty()) {
                // Combine all contours to create a single point array
                List<Point> allPoints = new ArrayList<>();
                for (MatOfPoint contour : points) {
                    allPoints.addAll(contour.toList());
                }

                // Convert list to MatOfPoint2f
                MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
                matOfPoint2f.fromList(allPoints);

                // Calculate the minimum area rectangle
                minAreaRect = Imgproc.minAreaRect(matOfPoint2f);
                Point[] boxPoints = new Point[4];
                minAreaRect.points(boxPoints);

                // Draw the minimum area rectangle on the original image
                for (int i = 0; i < 4; i++) {
                    Imgproc.line(input, boxPoints[i], boxPoints[(i + 1) % 4], new Scalar(0, 255, 0), 2);
                }

            }

             */

            if (prev_val.size() >= iter_num) {
                run = run_continuous;


            }
            return k1;
        }
        return input;
    }
    public double[] getResult() {
        return calculateMedian(prev_val);
    }
    public void run_main() {
        reset();
        run = true;
    }
    public static double[] calculateMedian(ArrayList<Double[]> data) {
        // Check if the input list is empty
        if (data.isEmpty()) {
            throw new IllegalArgumentException("Input data is empty.");
        }

        // Lists to hold each column's values
        ArrayList<Double> column1 = new ArrayList<>();
        ArrayList<Double> column2 = new ArrayList<>();
        ArrayList<Double> column3 = new ArrayList<>();

        // Populate the column lists
        for (Double[] row : data) {
            if (row.length != 3) {
                throw new IllegalArgumentException("Each row must have a length of 3.");
            }
            column1.add(row[0]);
            column2.add(row[1]);
            column3.add(row[2]);
        }

        // Calculate median for each column
        double median1 = findMedian(column1);
        double median2 = findMedian(column2);
        double median3 = findMedian(column3);

        // Return the medians as a double array
        return new double[]{median1, median2, median3};
    }

    private static double findMedian(ArrayList<Double> column) {
        Collections.sort(column);
        int size = column.size();
        if (size % 2 == 0) {
            // Even number of elements
            return (column.get(size / 2 - 1) + column.get(size / 2)) / 2.0;
        } else {
            // Odd number of elements
            return column.get(size / 2);
        }
    }

}