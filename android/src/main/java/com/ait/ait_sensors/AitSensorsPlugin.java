package com.ait.ait_sensors;

import android.util.EventLog;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Handler;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** AitSensorsPlugin */
public class AitSensorsPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  private EventChannel accelerometerChannel;
  private EventChannel userAccelerometerChannel;
  private EventChannel gyroscopeChannel;
  private EventChannel lightChannel;

  public static ArrayList<String> accelerometerStream;
  public static ArrayList<String> userAccelerometerStream;
  public static ArrayList<String> gyroscopeStream;
  public static ArrayList<String> lightStream;

  private Map<Object, Runnable> accelerometerListeners = new HashMap<>();
  private Map<Object, Runnable> userAccelerometerListeners = new HashMap<>();
  private Map<Object, Runnable> gyroscopeListeners = new HashMap<>();
  private Map<Object, Runnable> lightListeners = new HashMap<>();

  private void accelerometerSetup(BinaryMessenger binaryMessenger) {
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

  private void startAccelerometerListening(final Object listener, final EventChannel.EventSink emitter) {
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

  private void cancelAccelerometerListening(Object listener) { accelerometerListeners.remove(listener); }

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "ait_sensors");
    channel.setMethodCallHandler(this);
    accelerometerSetup(flutterPluginBinding.getBinaryMessenger());
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
