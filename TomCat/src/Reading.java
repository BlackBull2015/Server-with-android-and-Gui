/**
 * Created by Eric on 3/29/2016.
 */

public class Reading {

    private int accXValue;
    private int accYValue;
    private int accZValue;

    private int magXValue;
    private int magYValue;
    private int magZValue;
    private int heading;


    private int temperature;
    private String time;

    public Reading(){
         accXValue = 0;
         accYValue = 0;
         accZValue = 0;
         magXValue = 0;
         magYValue = 0;
         magZValue = 0;
         heading = 0;
         temperature = 0;
         time = "";
    }

    public String toString(){
        return "Reading is: Acc: X="+accXValue+" Y="+accYValue+" Z="+accZValue+" Mag: X="+magXValue+" Y="+magYValue+
                " Z="+magZValue+", Temp: "+temperature +" and reading was done at: "+time;
    }

    public String toStringDb(){
        return accXValue+" "+accYValue+" "+accZValue+" "+magXValue+" "+magYValue+
                " "+magZValue+" "+temperature+" "+time;
    }


    public int getAccXValue() {
        return accXValue;
    }

    public void setAccXValue(int accXValue) {
        this.accXValue = accXValue;
    }

    public int getAccYValue() {
        return accYValue;
    }

    public void setAccYValue(int accYValue) {
        this.accYValue = accYValue;
    }

    public int getAccZValue() {
        return accZValue;
    }

    public void setAccZValue(int accZValue) {
        this.accZValue = accZValue;
    }

    public int getMagXValue() {
        return magXValue;
    }

    public void setMagXValue(int magXValue) {
        this.magXValue = magXValue;
    }

    public int getMagYValue() {
        return magYValue;
    }

    public void setMagYValue(int magYValue) {
        this.magYValue = magYValue;
    }

    public int getMagZValue() {
        return magZValue;
    }

    public void setMagZValue(int magZValue) {
        this.magZValue = magZValue;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public void computeHeading(){
       // Heading = atan2 (Yout_uT, Xout_uT) * 180 / PI;
        heading = (int)(Math.atan2(magYValue,magXValue) * (180/Math.PI));

    }

}
