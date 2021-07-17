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

    public static boolean destroyAccelerometer(SensorManager sensorManager) {
        sensorManager.unregisterListener(accelerometerListener);
        return true;
    }

    public static boolean destroyUserAccelerometer(SensorManager sensorManager) {
        sensorManager.unregisterListener(userAccelerometerListener);
        return true;
    }

    public static boolean destroyGyroscope(SensorManager sensorManager) {
        sensorManager.unregisterListener(gyroscopeListener);
        return true;
    }

    public static boolean destroyLight(SensorManager sensorManager) {
        sensorManager.unregisterListener(lightListener);
        return true;
    }

    private static final SensorEventListener accelerometerListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                String value = String.valueOf(sensorEvent.values[0]) + ',' + String.valueOf(sensorEvent.values[1]) + ',' + String.valueOf(sensorEvent.values[2]);
                AitSensorsPlugin.accelerometerStream.add(value);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private static final SensorEventListener userAccelerometerListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                String value = String.valueOf(sensorEvent.values[0]) + ',' + String.valueOf(sensorEvent.values[1]) + ',' + String.valueOf(sensorEvent.values[2]);
                AitSensorsPlugin.userAccelerometerStream.add(value);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private static final SensorEventListener gyroscopeListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                String value = String.valueOf(sensorEvent.values[0]) + ',' + String.valueOf(sensorEvent.values[1]) + ',' + String.valueOf(sensorEvent.values[2]);
                AitSensorsPlugin.gyroscopeStream.add(value);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private static final SensorEventListener lightListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
                String value = String.valueOf(sensorEvent.values[0]);
                AitSensorsPlugin.lightStream.add(value);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
