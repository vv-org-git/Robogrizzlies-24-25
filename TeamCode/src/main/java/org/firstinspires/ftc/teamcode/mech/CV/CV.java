package org.firstinspires.ftc.teamcode.mech.CV;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class CV {
    OpenCvWebcam camera;
    Integer resolution_x = 320;
    Integer resolution_y = 240;

    public CV(LinearOpMode l) {
        int cameraMonitorViewId = l.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", l.hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(l.hardwareMap.get(WebcamName.class, "CV"), cameraMonitorViewId);
    }
    public void stream(){
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                // Usually this is where you'll want to start streaming from the camera (see section 4)
            }
            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
        camera.startStreaming(320, 420, OpenCvCameraRotation.UPRIGHT);
        OpenCvPipeline pipeline = new DetectSample();
        camera.setPipeline(pipeline);
    }
}
