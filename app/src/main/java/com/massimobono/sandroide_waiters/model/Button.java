package com.massimobono.sandroide_waiters.model;

/**
 * Created by K on 09/03/2017.
 */

public class Button implements Runnable {

    private enum Status{
        GREY{
            Status nextStep(Button p) {
                if(pressedStatus) return YELLOW;
                return GREY;
            }
        },
        YELLOW{
            Status nextStep(Button p) {
                //trun on or turn off
                if(yellow_light == true) yellow_light = false;
                else yellow_light = true;
                //timer to red
                if(timer < TTR) timer++;
                //time to red
                else return RED;
                return YELLOW;
            }

        },
        RED{
            Status nextStep(Button p) {
                if(yellow_light == true) yellow_light = false;
                red_light = true;
                if(taken){
                    red_light = false;
                    taken = false;
                    return GREY;
                }
                return RED;
            }
        };

        abstract Status nextStep(Button p);
    }
    //Class V.
    private static int TTR = 30;

    private static boolean pressedStatus = false;
    private static boolean taken = false;

    private static boolean yellow_light = false;
    private static boolean red_light = false;

    private static int timer = 0;

    private Status status;

    //how to comunicate with this obj
    public void setPressed(){
        this.pressedStatus = true;
    }
    public void setTaken(){
        this.taken = true;
    }

    public void nextStep(){
        status = status.nextStep(this);
    }

    @Override
    public void run() {
        while (true){
            nextStep();
            try {
                Thread.sleep(1000);
            }catch(InterruptedException exc){}
        }
    }
}
