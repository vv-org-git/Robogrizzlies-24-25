package org.firstinspires.ftc.teamcode.mech.CV;


import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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


    public boolean run_continuous = true;
    public int threshold = 5;
    public int threshold_z = 200;
    public double prev_x = -1;
    public double prev_y = -1;
    public double prev_a = -1;


    private Telemetry telemetry;

    public SampleDetectionRect(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        if (run) {
//            Core.convertScaleAbs(input, input, alpha, beta);

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

}