import 'package:flutter/services.dart';

class FlutterUmeng {
  static const MethodChannel _channel = const MethodChannel('flutter_umeng');

  static void init({String appKey, String channel}) {
    _channel.invokeMethod("init", [appKey, channel]);
  }

  static void addCountEvent(String eventId) {
    _channel.invokeMethod("addCountEvent", eventId);
  }

  static void addMapCountEvent(String eventId, Map<String, String> params) {
    _channel.invokeMethod("addParamsEvent", [eventId, params]);
  }

  static void reportError(String errorMsg) {
    _channel.invokeMethod("reportError", errorMsg);
  }
}
