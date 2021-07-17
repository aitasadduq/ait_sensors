
import 'dart:async';

import 'package:flutter/services.dart';

class AitSensors {
  static const MethodChannel _channel =
      const MethodChannel('ait_sensors');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
