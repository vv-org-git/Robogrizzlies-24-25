package org.firstinspires.ftc.teamcode.vision;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
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

public class SampleDetection extends OpenCvPipeline {
    public int width = 240;
    public int length = 360;
    public boolean run = true;
    public int angle_delta = 0;
    public int x_delta = 0;
    public int y_delta = 0;
    public boolean run_continuous = true;
    public int threshold = 120;

    private Telemetry telemetry;

    public SampleDetection(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        if (run) {
            Mat dst = new Mat(input.rows(), input.cols(), input.type());
            input.copyTo(dst);
            Imgproc.cvtColor(input, dst, Imgproc.COLOR_BGR2HSV);
 /*
            int lowerSaturation = 5;
            Scalar lowerBound = new Scalar(0, 40, 0);
            Scalar upperBound = new Scalar(180, 255, 255);
            Mat mask = new Mat();
            Mat r = new Mat();

            Core.inRange(dst, lowerBound, upperBound, mask);
            Core.bitwise_and(dst, dst, r, mask);


            Mat new_mask = new Mat();
            if (averageHSV.val[0] > 140) {
                 lowerBound = new Scalar(140, lowerSaturation, 50);
                 upperBound = new Scalar(180, 255, 255);

            }
            else if (averageHSV.val[0] > 50) {
                lowerBound = new Scalar(50, lowerSaturation, 50);
                upperBound = new Scalar(140, 255, 255);
            }
            else {
                lowerBound = new Scalar(0, lowerSaturation, 50);
                upperBound = new Scalar(50, 255, 255);
            }
            Core.inRange(dst, lowerBound, upperBound, new_mask);
            Core.bitwise_and(dst, dst, dst, new_mask);
 *
 */

            //Imgproc.cvtColor(r, r, Imgproc.COLOR_HSV2BGR); // Convert to BGR first
            //Imgproc.cvtColor(r, dst, Imgproc.COLOR_BGR2GRAY);
            Mat r = new Mat();
            Core.extractChannel(dst, r, 1);
            Core.extractChannel(dst, dst, 2);
            // r.convertTo(r, -1, 2.0, 0);
            Mat addedImage = new Mat();
            Core.add(dst, r, addedImage);
            dst = addedImage;
            Imgproc.blur(dst, dst, new Size(6, 6));
            Imgproc.threshold(dst, dst, threshold, 255, Imgproc.THRESH_BINARY);

            final List<MatOfPoint> points = new ArrayList<>();
            final Mat hierarchy = new Mat();
            Imgproc.findContours(dst, points, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

            for (int i = 1; i < points.size(); i++) {
                MatOfPoint2f contour1 = new MatOfPoint2f(points.get(i).toArray());
                RotatedRect minRect = Imgproc.minAreaRect(contour1);
                if (minRect.size.width < width * 0.75 && minRect.size.width > width * 0.12 && minRect.size.height < length * 0.75 && minRect.size.height > length * 0.12) {
                    x_delta = (int) (minRect.center.x - length / 2);
                    y_delta = (int) (minRect.center.y - width / 2);
                    angle_delta = (int) (minRect.angle);
                    telemetry.addData("center: ", minRect.center);
                    telemetry.addData("rotation: ", minRect.angle);
                    telemetry.addData("width: ", minRect.size.width + " height: " + minRect.size.height);
                    telemetry.update();
                    Point[] vertices = new Point[4];
                    minRect.points(vertices);
                    for (int k = 0; k < 4; k++) {
                        Imgproc.line(input, vertices[k], vertices[(k + 1) % 4], new Scalar(0, 0, 255), 2);
                    }
                    break;
                }
            }

            run = run_continuous;
            return input;
        }
        return input;
    }

}