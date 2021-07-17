package com.ait.ait_sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorService {

    static Sensor accelerometer;
    static Sensor userAccelerometer;
    static Sensor gyroscope;
    static Sensor light;

    public static boolean initAccelerometer(SensorManager sensorManager) {
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(accelerometerListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            return true;
        }
        return false;
    }

    public static boolean initUserAccelerometer(SensorManager sensorManager) {
        userAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        if (userAccelerometer != null) {
            sensorManager.registerListener(userAccelerometerListener, userAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            return true;
        }
        return false;
    }

    public static boolean initGyroscope(SensorManager sensorManager) {
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscope != null) {
            sensorManager.registerListener(gyroscopeListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
            return true;
        }
        return false;
    }

    public static boolean initLight(SensorManager sensorManager) {
        light = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (light != null) {
            sensorManager.registerListener(lightListener, light, SensorManager.SENSOR_DELAY_NORMAL);
            return true;
        }
        return false;
    }

    private static final SensorEventListener accelerometerListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private static final SensorEventListener userAccelerometerListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private static final SensorEventListener gyroscopeListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private static final SensorEventListener lightListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
