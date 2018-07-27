package com.reactnativehelloagainworld;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.navisens.motiondnaapi.MotionDna;
import com.navisens.motiondnaapi.MotionDnaApplication;
import com.navisens.motiondnaapi.MotionDnaInterface;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.navisens.motiondnaapi.MotionDna.MotionType.FIDGETING;
import static com.navisens.motiondnaapi.MotionDna.MotionType.FORWARD;
import static com.navisens.motiondnaapi.MotionDna.MotionType.STATIONARY;

/**
 * Created by jamesgrantham on 3/15/18.
 */

public class MotionDnaReactBridge extends ReactContextBaseJavaModule implements MotionDnaInterface {

//    MotionDnaApplication motionDnaApplication;
    MotionDnaSynchronizedApplication motionDnaApplication;

    public MotionDnaReactBridge(ReactApplicationContext reactContext) {
        super(reactContext);
        Looper.prepare();

    }

    @ReactMethod
    public void runMotionDna(final String key, final Callback initializationCallback)
    {
        Handler NavisensHandler = new Handler(Looper.getMainLooper());
        NavisensHandler.post(new Runnable() {
            @Override
            public void run() {
                //        motionDnaApplication = new MotionDnaApplication(this);
                motionDnaApplication = new MotionDnaSynchronizedApplication(MotionDnaReactBridge.this);
                motionDnaApplication.runMotionDna(key);

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        initializationCallback.invoke();
                    }
                },1000);
            }
        });

    }

    @ReactMethod
    public void setExternalPositioningState(String state)
    {
        if (state.equals("OFF")) {
            motionDnaApplication.setExternalPositioningState(MotionDna.ExternalPositioningState.OFF);
        }
        else if (state.equals("HIGH_ACCURACY")){
            motionDnaApplication.setExternalPositioningState(MotionDna.ExternalPositioningState.HIGH_ACCURACY);
        }
        else if (state.equals("LOW_ACCURACY")) {
            motionDnaApplication.setExternalPositioningState(MotionDna.ExternalPositioningState.LOW_ACCURACY);
        } else {
            motionDnaApplication.setExternalPositioningState(MotionDna.ExternalPositioningState.EXTERNAL_UNDEFINED);
        }
    }

    @ReactMethod
    public void setPowerMode(String powerMode)
    {
        if (powerMode.equals("SUPER_LOW_CONSUMPTION")) {
            motionDnaApplication.setPowerMode(MotionDna.PowerConsumptionMode.SUPER_LOW_CONSUMPTION);
        }
        else if (powerMode.equals("LOW_CONSUMPTION")){
            motionDnaApplication.setPowerMode(MotionDna.PowerConsumptionMode.LOW_CONSUMPTION);
        }
        else if (powerMode.equals("MEDIUM_CONSUMPTION")) {
            motionDnaApplication.setPowerMode(MotionDna.PowerConsumptionMode.MEDIUM_CONSUMPTION);
        }
        else if (powerMode.equals("PERFORMANCE")) {
            motionDnaApplication.setPowerMode(MotionDna.PowerConsumptionMode.PERFORMANCE);
        }
    }


    @ReactMethod
    public void resume()
    {
        motionDnaApplication.resume();
    }

    @ReactMethod
    public void pause()
    {
        motionDnaApplication.pause();
    }

    @ReactMethod
    public void stop()
    {
        motionDnaApplication.stop();
    }

    @ReactMethod
    public void resetLocalEstimation()
    {
        motionDnaApplication.resetLocalEstimation();
    }

    @ReactMethod
    public void resetLocalHeading()
    {
        motionDnaApplication.resetLocalHeading();
    }

    @ReactMethod
    public void setARMModeEnabled(Boolean mode)
    {
        motionDnaApplication.setARModeEnabled(mode);
    }

    @ReactMethod
    public void setBinaryFileLoggingEnabled(Boolean state)
    {
        motionDnaApplication.setBinaryFileLoggingEnabled(state);
    }

    @ReactMethod
    public void setBackpropagationEnabled(boolean state) {
        motionDnaApplication.setBackpropagationEnabled(state);
    }

    @ReactMethod
    public void setBackpropagationBufferSize(int backPropBufferSize) {
        motionDnaApplication.setBackpropagationBufferSize(backPropBufferSize);
    }

    @ReactMethod
    public void setCallbackUpdateRateInMs(Double rate)
    {
        motionDnaApplication.setCallbackUpdateRateInMs(rate);
    }

    @ReactMethod
    public void setNetworkUpdateRateInMs(Double rate)
    {
        motionDnaApplication.setNetworkUpdateRateInMs(rate);
    }

    @ReactMethod
    public void setPowerMode(MotionDna.PowerConsumptionMode mode)
    {
        motionDnaApplication.setPowerMode(mode);
    }

    @ReactMethod
    public void setLocationNavisens()
    {
        motionDnaApplication.setLocationNavisens();
    }

    @ReactMethod
    public void setLocationGPS()
    {
        motionDnaApplication.setLocationGPSOnly();
    }

    @ReactMethod
    public void setLocationLatitudeLongitude(Double lat, Double lng)
    {
        motionDnaApplication.setLocationLatitudeLongitude(lat,lng);
    }

    @ReactMethod
    public void setLocationLatitudeLongitudeAndHeadingInDegrees(Double lat, Double lng, Double angle)
    {
        motionDnaApplication.setLocationLatitudeLongitudeAndHeadingInDegrees(lat,lng,angle);
    }

    @ReactMethod
    public void setHeadingMagInDegrees()
    {
        motionDnaApplication.setHeadingMagInDegrees();
    }

    @ReactMethod
    public void setHeadingInDegrees(Double heading)
    {
        motionDnaApplication.setHeadingInDegrees(heading);
    }

    @ReactMethod
    public void setLocalHeadingOffsetInDegrees(Double degrees)
    {
        motionDnaApplication.setLocalHeadingOffsetInDegrees(degrees);
    }

    @ReactMethod
    public void startUDP()
    {
        motionDnaApplication.startUDP();
    }

    @ReactMethod
    public void startUDP(String room)
    {
        motionDnaApplication.startUDP(room);
    }

    @ReactMethod
    public void stopUDP()
    {
        motionDnaApplication.stopUDP();
    }

    @ReactMethod
    public void setUDPRoom(String room)
    {
        motionDnaApplication.setUDPRoom(room);
    }

    @ReactMethod
    public void sendUDPPacket(String msg)
    {
        motionDnaApplication.sendUDPPacket(msg);
    }

    @ReactMethod
    public void sendUDPQueryRooms(String[] rooms)
    {
        motionDnaApplication.sendUDPQueryRooms(rooms);
    }

    @ReactMethod
    public void start()
    {
        motionDnaApplication.setExternalPositioningState(MotionDna.ExternalPositioningState.LOW_ACCURACY);
//        motionDnaApplication.setLocationAndHeadingGPSMag();
        motionDnaApplication.setLocationLatitudeLongitudeAndHeadingInDegrees(37.787582, -122.396627, 0);

        motionDnaApplication.setPowerMode(MotionDna.PowerConsumptionMode.PERFORMANCE);
//        motionDnaApplication.startUDPHostAndPort("45.79.101.164", "6512");
        motionDnaApplication.setCallbackUpdateRateInMs(100);

//        motionDnaApplication.setARModeEnabled(true);
//        motionDnaApplication.setARModeEnabled(false);
//
//        motionDnaApplication.setContinuousService(true);
//        motionDnaApplication.setContinuousService(false);
        Log.v(this.getClass().getSimpleName(),"Setup MotionDNAApplication");
        if (MainApplication.context == null) {
            Log.v(this.getClass().getSimpleName(),"Context is null");

        } else {
            Log.v(this.getClass().getSimpleName(),"Context is valid");
        }
    }

    // name used to access bridge from javascript side
    @Override
    public String getName() {
        return "MotionDnaReactBridge";
    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    @Override
    public void receiveMotionDna(MotionDna motionDna)
    {
        String str = "Lat: " + motionDna.getLocation().globalLocation.latitude + "/Lon: " + motionDna.getLocation().globalLocation.longitude + "\n";
        str +="X:" + motionDna.getLocation().localLocation.x + "/Y:" + motionDna.getLocation().localLocation.y + "/Z:" + motionDna.getLocation().localLocation.z + "\n";
        str += "Hdg: " + motionDna.getLocation().heading +  " \n";
//            str += motionDna.getMotion().primaryMotion + "\n";
        str += MotionDnaApplication.checkSDKVersion();

        WritableMap motionDnaParameters = Arguments.createMap();
        motionDnaParameters.putString("MotionDnaString",str);
        motionDnaParameters.putString("location_locationStatus",StringFromLocationStatus(motionDna.getLocation().locationStatus));
        motionDnaParameters.putDouble("location_localLocation_x",motionDna.getLocation().localLocation.x);
        motionDnaParameters.putDouble("location_localLocation_y",motionDna.getLocation().localLocation.y);
        motionDnaParameters.putDouble("location_localLocation_z",motionDna.getLocation().localLocation.z);
        motionDnaParameters.putDouble("location_globalLocation_latitude",motionDna.getLocation().globalLocation.latitude);
        motionDnaParameters.putDouble("location_globalLocation_longitude",motionDna.getLocation().globalLocation.longitude);
        motionDnaParameters.putDouble("location_globalLocation_altitude",motionDna.getLocation().globalLocation.altitude);
        motionDnaParameters.putDouble("location_heading",motionDna.getLocation().heading);
        motionDnaParameters.putDouble("location_localHeading",motionDna.getLocation().localHeading);
        motionDnaParameters.putDouble("location_uncertainty_x",motionDna.getLocation().uncertainty.x);
        motionDnaParameters.putDouble("location_uncertainty_y",motionDna.getLocation().uncertainty.y);
        motionDnaParameters.putInt("location_verticalMotionStatus",motionDna.getLocation().verticalMotionStatus.ordinal());
        motionDnaParameters.putDouble("location_floor",motionDna.getLocation().floor);
        motionDnaParameters.putInt("GPSLocation_locationStatus",motionDna.getGpsLocation().locationStatus.ordinal());
        motionDnaParameters.putDouble("GPSLocation_localLocation_x",motionDna.getGpsLocation().localLocation.x);
        motionDnaParameters.putDouble("GPSLocation_localLocation_y",motionDna.getGpsLocation().localLocation.y);
        motionDnaParameters.putDouble("GPSLocation_localLocation_z",motionDna.getGpsLocation().localLocation.z);
        motionDnaParameters.putDouble("GPSLocation_globalLocation_latitude",motionDna.getGpsLocation().globalLocation.latitude);
        motionDnaParameters.putDouble("GPSLocation_globalLocation_longitude",motionDna.getGpsLocation().globalLocation.longitude);
        motionDnaParameters.putDouble("GPSLocation_globalLocation_altitude",motionDna.getGpsLocation().globalLocation.altitude);
        motionDnaParameters.putDouble("GPSLocation_heading",motionDna.getGpsLocation().heading);
        motionDnaParameters.putDouble("GPSLocation_localHeading",motionDna.getGpsLocation().localHeading);
        motionDnaParameters.putDouble("GPSLocation_uncertainty_x",motionDna.getGpsLocation().uncertainty.x);
        motionDnaParameters.putDouble("GPSLocation_uncertainty_y",motionDna.getGpsLocation().uncertainty.y);
        motionDnaParameters.putInt("GPSLocation_verticalMotionStatus",motionDna.getGpsLocation().verticalMotionStatus.ordinal());
        motionDnaParameters.putDouble("GPSLocation_floor",motionDna.getGpsLocation().floor);
        motionDnaParameters.putDouble("attitude_roll",motionDna.getAttitude().roll);
        motionDnaParameters.putDouble("attitude_pitch",motionDna.getAttitude().pitch);
        motionDnaParameters.putDouble("attitude_yaw",motionDna.getAttitude().yaw);
        motionDnaParameters.putString("motion_motionType",StringFromMotionType(motionDna.getMotion().motionType));
        motionDnaParameters.putInt("location_verticalMotionStatus",motionDna.getLocation().verticalMotionStatus.ordinal());
        motionDnaParameters.putDouble("motionStatistics_dwelling",motionDna.getMotionStatistics().dwelling);
        motionDnaParameters.putDouble("motionStatistics_walking",motionDna.getMotionStatistics().walking);
        motionDnaParameters.putDouble("motionStatistics_stationary",motionDna.getMotionStatistics().stationary);
        motionDnaParameters.putDouble("polygonMotionStatistics_dwelling",motionDna.getPolygonMotionStatistics().dwelling);
        motionDnaParameters.putDouble("polygonMotionStatistics_walking",motionDna.getPolygonMotionStatistics().walking);
        motionDnaParameters.putDouble("polygonMotionStatistics_stationary",motionDna.getPolygonMotionStatistics().stationary);
        motionDnaParameters.putString("ID",motionDna.getID());
        motionDnaParameters.putString("deviceName",motionDna.getDeviceName());
        motionDnaParameters.putDouble("quaternion_x",motionDna.getOrientationQuaternion().x);
        motionDnaParameters.putDouble("quaternion_y",motionDna.getOrientationQuaternion().y);
        motionDnaParameters.putDouble("quaternion_z",motionDna.getOrientationQuaternion().z);
        motionDnaParameters.putDouble("quaternion_w",motionDna.getOrientationQuaternion().w);
        motionDnaParameters.putDouble("timestamp",motionDna.getTimestamp());
        sendEvent(getReactApplicationContext(),"MotionDnaEvent",motionDnaParameters);

    }

    private String StringFromMotionType(MotionDna.MotionType mt) {
        switch (mt) {
            case STATIONARY:
                return "STATIONARY";
            case FIDGETING:
                return "FIDGETING";
            case FORWARD:
                return "FORWARD";
            default:
                break;
        }
        return "";
    }

    private String StringFromLocationStatus(MotionDna.LocationStatus ls) {
        switch (ls) {
            case NAVISENS_INITIALIZED:
                return "NAVISENS_INITIALIZED";
            case NAVISENS_INITIALIZING:
                return "NAVISENS_INITIALIZING";
            case GPS_INITIALIZED:
                return "GPS_INITIALIZED";
            case USER_INITIALIZED:
                return "USER_INITIALIZED";
            case UNINITIALIZED:
                return "UNINITIALIZED";
            default:
                return "DEFAULT";
        }
//        return "";
    }

    @Override
    public Context getAppContext() {
        return getReactApplicationContext();
    }

    @Override
    public PackageManager getPkgManager() {
        return MainApplication.context.getPackageManager();
    }

    @Override
    public void reportError(MotionDna.ErrorCode errorCode, String s) {
        String errorCodeString = new String();
        switch (errorCode) {
            case ERROR_SENSOR_TIMING: {
                errorCodeString = "ERROR_SENSOR_TIMING";
                break;
            }
            case ERROR_AUTHENTICATION_FAILED: {
                errorCodeString = "ERROR_AUTHENTICATION_FAILED";
                break;
            }
            case ERROR_SENSOR_MISSING: {
                errorCodeString = "ERROR_SENSOR_MISSING";
                break;
            }
            case ERROR_SDK_EXPIRED: {
                errorCodeString = "ERROR_SDK_EXPIRED";
                break;
            }
            case ERROR_WRONG_FLOOR_INPUT: {
                errorCodeString = "ERROR_WRONG_FLOOR_INPUT";
                break;
            }
            case ERROR_PERMISSIONS: {
                errorCodeString = "ERROR_PERMISSIONS";
                break;
            }
        }
        Log.v(getClass().getSimpleName(), "Error: " + errorCodeString + " -- " + s);
        WritableMap motionDnaErrorParameters = Arguments.createMap();
        motionDnaErrorParameters.putString("errorCode", errorCodeString);
        motionDnaErrorParameters.putString("errorString",s);
        sendEvent(getReactApplicationContext(),"MotionDnaErrorEvent",motionDnaErrorParameters);

    }

    @Override
    public void receiveNetworkData(MotionDna motionDna) {
        System.out.println(motionDna.getDeviceName());
    }

    @Override
    public void receiveNetworkData(MotionDna.NetworkCode networkCode, Map<String, ? extends Object> map) {

    }
}
