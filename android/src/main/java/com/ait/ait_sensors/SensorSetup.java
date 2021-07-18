package com.ait.ait_sensors;

import android.os.Handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;

public class SensorSetup {
    private static EventChannel accelerometerChannel;
    private static EventChannel userAccelerometerChannel;
    private static EventChannel gyroscopeChannel;
    private static EventChannel lightChannel;

    public static ArrayList<String> accelerometerStream;
    public static ArrayList<String> userAccelerometerStream;
    public static ArrayList<String> gyroscopeStream;
    public static ArrayList<String> lightStream;

    private static Map<Object, Runnable> accelerometerListeners = new HashMap<>();
    private static Map<Object, Runnable> userAccelerometerListeners = new HashMap<>();
    private static Map<Object, Runnable> gyroscopeListeners = new HashMap<>();
    private static Map<Object, Runnable> lightListeners = new HashMap<>();

    public static void accelerometerSetup(BinaryMessenger binaryMessenger) {
        accelerometerChannel = new EventChannel(binaryMessenger, "accelerometerChannel");
        accelerometerChannel.setStreamHandler(new EventChannel.StreamHandler() {
            @Override
            public void onListen(Object arguments, EventChannel.EventSink events) {
                startAccelerometerListening(arguments, events);
            }

            @Override
            public void onCancel(Object arguments) {
                cancelAccelerometerListening(arguments);
            }
        });
    }

    public static void userAccelerometerSetup(BinaryMessenger binaryMessenger) {
        userAccelerometerChannel = new EventChannel(binaryMessenger, "userAccelerometerChannel");
        userAccelerometerChannel.setStreamHandler(new EventChannel.StreamHandler() {
            @Override
            public void onListen(Object arguments, EventChannel.EventSink events) {
                startUserAccelerometerListening(arguments, events);
            }

            @Override
            public void onCancel(Object arguments) {
                cancelUserAccelerometerListening(arguments);
            }
        });
    }

    public static void gyroscopeSetup(BinaryMessenger binaryMessenger) {
        gyroscopeChannel = new EventChannel(binaryMessenger, "gyroscopeChannel");
        gyroscopeChannel.setStreamHandler(new EventChannel.StreamHandler() {
            @Override
            public void onListen(Object arguments, EventChannel.EventSink events) {
                startGyroscopeListening(arguments, events);
            }

            @Override
            public void onCancel(Object arguments) {
                cancelGyroscopeListening(arguments);
            }
        });
    }

    public static void lightSetup(BinaryMessenger binaryMessenger) {
        lightChannel = new EventChannel(binaryMessenger, "lightChannel");
        lightChannel.setStreamHandler(new EventChannel.StreamHandler() {
            @Override
            public void onListen(Object arguments, EventChannel.EventSink events) {
                startLightListening(arguments, events);
            }

            @Override
            public void onCancel(Object arguments) {
                cancelLightListening(arguments);
            }
        });
    }

    private static void startAccelerometerListening(final Object listener, final EventChannel.EventSink emitter) {
        final Handler handler = new Handler();
        accelerometerListeners.put(listener, new Runnable() {
            @Override
            public void run() {
                if (accelerometerListeners.containsKey(listener)) {
                    String dataString;
                    while (!accelerometerStream.isEmpty()) {
                        dataString = accelerometerStream.remove(0);
                        emitter.success(dataString);
                    }
                    handler.postDelayed((Runnable) this, 50);
                }
            }
        });
        handler.post(accelerometerListeners.get(listener));
    }

    private static void cancelAccelerometerListening(Object listener) { accelerometerListeners.remove(listener); }

    private static void startUserAccelerometerListening(final Object listener, final EventChannel.EventSink emitter) {
        final Handler handler = new Handler();
        userAccelerometerListeners.put(listener, new Runnable() {
            @Override
            public void run() {
                if (userAccelerometerListeners.containsKey(listener)) {
                    String dataString;
                    while (!userAccelerometerStream.isEmpty()) {
                        dataString = userAccelerometerStream.remove(0);
                        emitter.success(dataString);
                    }
                    handler.postDelayed((Runnable) this, 50);
                }
            }
        });
        handler.post(userAccelerometerListeners.get(listener));
    }

    private static void cancelUserAccelerometerListening(Object listener) { userAccelerometerListeners.remove(listener); }

    private static void startGyroscopeListening(final Object listener, final EventChannel.EventSink emitter) {
        final Handler handler = new Handler();
        gyroscopeListeners.put(listener, new Runnable() {
            @Override
            public void run() {
                if (gyroscopeListeners.containsKey(listener)) {
                    String dataString;
                    while (!gyroscopeStream.isEmpty()) {
                        dataString = gyroscopeStream.remove(0);
                        emitter.success(dataString);
                    }
                    handler.postDelayed((Runnable) this, 50);
                }
            }
        });
        handler.post(gyroscopeListeners.get(listener));
    }

    private static void cancelGyroscopeListening(Object listener) { gyroscopeListeners.remove(listener); }

    private static void startLightListening(final Object listener, final EventChannel.EventSink emitter) {
        final Handler handler = new Handler();
        lightListeners.put(listener, new Runnable() {
            @Override
            public void run() {
                if (lightListeners.containsKey(listener)) {
                    String dataString;
                    while (!lightStream.isEmpty()) {
                        dataString = lightStream.remove(0);
                        emitter.success(dataString);
                    }
                    handler.postDelayed((Runnable) this, 50);
                }
            }
        });
        handler.post(lightListeners.get(listener));
    }

    private static void cancelLightListening(Object listener) { lightListeners.remove(listener); }
}
