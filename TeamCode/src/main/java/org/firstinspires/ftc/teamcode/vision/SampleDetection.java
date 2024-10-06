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
    public int width = 480;
    public int length = 720;
    public boolean run = false;
    public int angle_delta = 0;
    public int x_delta = 0;
    public int y_delta = 0;
    public boolean run_continuous = false;


    private Telemetry telemetry;

    public SampleDetection(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        if (run) {
            Mat dst = new Mat(input.rows(), input.cols(), input.type());
            input.copyTo(dst);
            Imgproc.cvtColor(dst, dst, Imgproc.COLOR_RGB2GRAY);
            Imgproc.blur(dst, dst, new Size(3, 3));
            Imgproc.threshold(dst, dst, 100, 255, Imgproc.THRESH_BINARY_INV);

            final List<MatOfPoint> points = new ArrayList<>();
            final Mat hierarchy = new Mat();
            Imgproc.findContours(dst, points, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
            telemetry.addData("size: ", points.size());


            for (int i = 1; i < points.size(); i++) {
                MatOfPoint2f contour1 = new MatOfPoint2f(points.get(i).toArray());
                RotatedRect minRect = Imgproc.minAreaRect(contour1);
                if (minRect.center.x > length / 3 && minRect.center.x < 2 * length / 3 && minRect.center.y > width / 3 && minRect.center.y < 2 * width / 3) {
                    x_delta = (int) (minRect.center.x - length / 2);
                    y_delta = (int) (minRect.center.y - width / 2);
                    angle_delta = (int) (minRect.angle);
                    break;
                }
                telemetry.addData("center: ", minRect.center);
                telemetry.addData("rotation: ", minRect.angle);
                telemetry.addData("width: ", minRect.size.width + " height: " + minRect.size.height);
                telemetry.update();


            }
            Mat draw = Mat.zeros(dst.size(), CvType.CV_8UC3);
            for (int i = 0; i < points.size(); i++) {
                Scalar color = new Scalar(0, 0, 255);
                //Drawing Contours
                Imgproc.drawContours(draw, points, i, color, 2, Imgproc.LINE_8, hierarchy, 2, new Point());
            }

            //   MatOfPoint arr = new MatOfPoint(points);
            // MatOfPoint2f contour1 =  new MatOfPoint2f(arr);
            // Imgproc.minAreaRect(contour1);

            run = run_continuous;
            return draw;
        }
        return input;
    }

}