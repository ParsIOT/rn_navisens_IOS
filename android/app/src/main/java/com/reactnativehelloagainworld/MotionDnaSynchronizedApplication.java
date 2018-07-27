package com.reactnativehelloagainworld;

import android.util.Log;

import com.navisens.motiondnaapi.MotionDna;
import com.navisens.motiondnaapi.MotionDnaApplication;
import com.navisens.motiondnaapi.MotionDnaInterface;

public class MotionDnaSynchronizedApplication extends MotionDnaApplication {
    MotionDnaSynchronizedApplication(MotionDnaInterface motionDnaInterface) {
        super(motionDnaInterface);
    }

    public synchronized void runMotionDna(String key)
    {
        super.runMotionDna(key);
    }


    public synchronized void setExternalPositioningState(MotionDna.ExternalPositioningState state)
    {
        super.setExternalPositioningState(state);
    }


    public synchronized void resume()
    {
        super.resume();
    }


    public synchronized void pause()
    {
        super.pause();
    }


    public synchronized void stop()
    {
        super.stop();
    }


    public synchronized void resetLocalEstimation()
    {
        super.resetLocalEstimation();
    }


    public synchronized void resetLocalHeading()
    {
        super.resetLocalHeading();
    }


    public synchronized void setARMModeEnabled(Boolean mode)
    {
        super.setARModeEnabled(mode);
    }


    public synchronized void setBinaryFileLoggingEnabled(Boolean state)
    {
        super.setBinaryFileLoggingEnabled(state);
    }

    public synchronized void setBackpropagationEnabled(Boolean state) {
        super.setBackpropagationEnabled(state);
    }

    public synchronized void setBackpropagationBufferSize(int backPropBufferSize) {
        super.setBackpropagationBufferSize(backPropBufferSize);
    }

    public synchronized void setCallbackUpdateRateInMs(Double rate)
    {
        super.setCallbackUpdateRateInMs(rate);
    }


    public synchronized void setNetworkUpdateRateInMs(Double rate)
    {
        super.setNetworkUpdateRateInMs(rate);
    }


    public synchronized void setPowerMode(MotionDna.PowerConsumptionMode mode)
    {
        super.setPowerMode(mode);
    }


    public synchronized void setLocationNavisens()
    {
        super.setLocationNavisens();
    }


    public synchronized void setLocationGPS()
    {
        super.setLocationGPSOnly();
    }


    public synchronized void setLocationLatitudeLongitude(Double lat, Double lng)
    {
        super.setLocationLatitudeLongitude(lat,lng);
    }


    public synchronized void setLocationLatitudeLongitudeAndHeadingInDegrees(Double lat, Double lng, Double angle)
    {
        super.setLocationLatitudeLongitudeAndHeadingInDegrees(lat,lng,angle);
    }


    public synchronized void setHeadingMagInDegrees()
    {
        super.setHeadingMagInDegrees();
    }


    public synchronized void setHeadingInDegrees(Double heading)
    {
        super.setHeadingInDegrees(heading);
    }


    public synchronized void setLocalHeadingOffsetInDegrees(Double degrees)
    {
        super.setLocalHeadingOffsetInDegrees(degrees);
    }


    public synchronized void startUDP()
    {
        super.startUDP();
    }


    public synchronized void startUDP(String room)
    {
        super.startUDP(room);
    }


    public synchronized void stopUDP()
    {
        super.stopUDP();
    }


    public synchronized void setUDPRoom(String room)
    {
        super.setUDPRoom(room);
    }


    public synchronized void sendUDPPacket(String msg)
    {
        super.sendUDPPacket(msg);
    }


    public synchronized void sendUDPQueryRooms(String[] rooms)
    {
        super.sendUDPQueryRooms(rooms);
    }


//    @Override
//    public void receiveMotionDna(MotionDna motionDna)
//    {
//
//    }


//    @Override
//    public void reportError(MotionDna.ErrorCode errorCode, String s) {
//
//    }
//
//    @Override
//    public void receiveNetworkData(MotionDna motionDna) {
//
//    }

//    @Override
//    public void receiveNetworkData(MotionDna.NetworkCode networkCode, Map<String, ? extends Object> map) {
//
//    }

}
