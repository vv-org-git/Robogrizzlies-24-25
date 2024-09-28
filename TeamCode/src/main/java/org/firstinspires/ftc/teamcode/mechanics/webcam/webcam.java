package org.firstinspires.ftc.teamcode.mechanics.webcam;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class webcam {
    OpenCvWebcam w;
    Integer resolution_x = 720;
    Integer resolution_y = 480;

    public webcam(LinearOpMode l) {
        int cameraMonitorViewId = l.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", l.hardwareMap.appContext.getPackageName());
        w = OpenCvCameraFactory.getInstance().createWebcam(l.hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
    }

    public void startStreaming(OpenCvPipeline pipeline) {

        w.setPipeline(pipeline);
        w.setMillisecondsPermissionTimeout(5000); // Timeout for obtaining permission is configurable. Set before opening.
        w.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                w.startStreaming(resolution_x, resolution_y, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode)
            {

            }
        });
    }
    public void setResolution(Integer x, Integer y) {
        resolution_x = x;
        resolution_y = y;
    }
}
