package org.firstinspires.ftc.teamcode.vision;


import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.Point;

import org.opencv.imgproc.Imgproc;
import java.util.List;
import java.util.ArrayList;

import org.openftc.easyopencv.OpenCvPipeline;


@Config
public class SampleDetectionRect extends OpenCvPipeline {
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

    public static int color = 0;

    public boolean run_continuous = true;
    public int threshold = 5;
    public int threshold_z = 200;
    public double prev_x = -1;
    public double prev_y = -1;
    public double prev_a = -1;


    private Telemetry telemetry;

    public SampleDetectionRect(Telemetry telemetry, int color) {
        this.telemetry = telemetry;
        this.color = color;
    }
    //lets find the orientation of one line between HoughLineP
    //
    @Override
    public Mat processFrame(Mat input) {
        if (run) {
            Mat dst = new Mat(input.rows(), input.cols(), input.type());
            input.copyTo(dst);
            Imgproc.cvtColor(dst, dst, Imgproc.COLOR_BGR2GRAY);
            Imgproc.blur(dst, dst, new Size(3, 3));
            Imgproc.threshold(dst, dst, threshold_z, 255, Imgproc.THRESH_TOZERO);
            Mat i2 = new Mat(input.rows(), input.cols(), input.type());
            dst.copyTo(i2);
            Imgproc.threshold(dst, dst, threshold, 255, Imgproc.THRESH_BINARY);
            Mat out = new Mat(input.rows(), input.cols(), input.type());
            dst.copyTo(out);
            final List<MatOfPoint> points = new ArrayList<>();
            final Mat hierarchy = new Mat();
            Imgproc.findContours(dst, points, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

            for (int i = 1; i < points.size(); i++) {
                MatOfPoint2f contour1 = new MatOfPoint2f(points.get(i).toArray());
                RotatedRect minRect = Imgproc.minAreaRect(contour1);
                if (minRect.size.width < width * 0.9 && minRect.size.width > width * 0.2 && minRect.size.height < length * 0.9 && minRect.size.height > length * 0.2) {
                    if ((prev_x/minRect.center.x >0.5 && prev_x/minRect.center.x < 1.5) && (prev_y/minRect.center.y > 0.5 && prev_y/minRect.center.y < 1.5) ){

                        telemetry.addData("center: ", minRect.center);
                        telemetry.addData("rotation: ", minRect.angle);
                        telemetry.addData("width: ", minRect.size.width + " height: " + minRect.size.height);
                        telemetry.update();
                        Point[] vertices = new Point[4];
                        minRect.points(vertices);
                        for (int k = 0; k < 4; k++) {
                            Imgproc.line(input, vertices[k], vertices[(k + 1) % 4], new Scalar(0, 0, 255), 2);
                        }
                        prev_x = minRect.center.x;
                        prev_y = minRect.center.y;
                        prev_a = minRect.angle;
                        break;
                    }
                    if (prev_x == -1) {
                        prev_x = minRect.center.x;
                        prev_y = minRect.center.y;
                        prev_a = minRect.angle;
                    }
                }
            }
            run = run_continuous;
            return input;
        }
        return input;
    }
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
}