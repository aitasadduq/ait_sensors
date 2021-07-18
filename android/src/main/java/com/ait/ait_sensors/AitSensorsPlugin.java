package com.ait.ait_sensors;

import android.content.Context;
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
  private SensorService sensorService;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    Context context = flutterPluginBinding.getApplicationContext();
    sensorService = new SensorService(context);
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "ait_sensors");
    channel.setMethodCallHandler(this);
    SensorSetup.accelerometerSetup(flutterPluginBinding.getBinaryMessenger());
    SensorSetup.userAccelerometerSetup(flutterPluginBinding.getBinaryMessenger());
    SensorSetup.gyroscopeSetup(flutterPluginBinding.getBinaryMessenger());
    SensorSetup.lightSetup(flutterPluginBinding.getBinaryMessenger());
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if (call.method.equals("initSensors")) {
      String sensor = call.argument("sensor");
      switch (sensor) {
        case "accelerometer":
          sensorService.initAccelerometer();
          break;
        case "userAccelerometer":
          sensorService.initUserAccelerometer();
          break;
        case "gyroscope":
          sensorService.initGyroscope();
          break;
        case "light":
          sensorService.initLight();
          break;
      }
    } else if (call.method.equals("destroySensors")) {
      String sensor = call.argument("sensor");
      switch (sensor) {
        case "accelerometer":
          sensorService.destroyAccelerometer();
          break;
        case "userAccelerometer":
          sensorService.destroyUserAccelerometer();
          break;
        case "gyroscope":
          sensorService.destroyGyroscope();
          break;
        case "light":
          sensorService.destroyLight();
          break;
      }
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
