package org.firstinspires.ftc.teamcode.helperFunctions;
public class helperFunctions {
    public double Clip(double x){
        if(x>1.0){
            return 1.0;
        }
        else if(x<-1.0){
            return -1.0;
        }else{
            return x;
        }
    }
}
