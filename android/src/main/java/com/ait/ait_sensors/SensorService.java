package com.ait.ait_sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorService {

    private final SensorManager sensorManager;

    public SensorService(Context context) {
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public boolean initAccelerometer() {
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            sensorManager.registerListener(accelerometerListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            return true;
        }
        return false;
    }

    public boolean initUserAccelerometer() {
        Sensor userAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        if (userAccelerometer != null) {
            sensorManager.registerListener(userAccelerometerListener, userAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            return true;
        }
        return false;
    }

    public boolean initGyroscope() {
        Sensor gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscope != null) {
            sensorManager.registerListener(gyroscopeListener, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
            return true;
        }
        return false;
    }

    public boolean initLight() {
        Sensor light = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (light != null) {
            sensorManager.registerListener(lightListener, light, SensorManager.SENSOR_DELAY_NORMAL);
            return true;
        }
        return false;
    }

    public boolean destroyAccelerometer() {
        sensorManager.unregisterListener(accelerometerListener);
        return true;
    }

    public boolean destroyUserAccelerometer() {
        sensorManager.unregisterListener(userAccelerometerListener);
        return true;
    }

    public boolean destroyGyroscope() {
        sensorManager.unregisterListener(gyroscopeListener);
        return true;
    }

    public boolean destroyLight() {
        sensorManager.unregisterListener(lightListener);
        return true;
    }

    private final SensorEventListener accelerometerListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                String value = String.valueOf(sensorEvent.values[0]) + ',' + String.valueOf(sensorEvent.values[1]) + ',' + String.valueOf(sensorEvent.values[2]);
                SensorSetup.accelerometerStream.add(value);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private final SensorEventListener userAccelerometerListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
                String value = String.valueOf(sensorEvent.values[0]) + ',' + String.valueOf(sensorEvent.values[1]) + ',' + String.valueOf(sensorEvent.values[2]);
                SensorSetup.userAccelerometerStream.add(value);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private final SensorEventListener gyroscopeListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                String value = String.valueOf(sensorEvent.values[0]) + ',' + String.valueOf(sensorEvent.values[1]) + ',' + String.valueOf(sensorEvent.values[2]);
                SensorSetup.gyroscopeStream.add(value);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    private final SensorEventListener lightListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
                String value = String.valueOf(sensorEvent.values[0]);
                SensorSetup.lightStream.add(value);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
