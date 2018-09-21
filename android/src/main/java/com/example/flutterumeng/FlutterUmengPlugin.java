package com.example.flutterumeng;

import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FlutterUmengPlugin
 */
public class FlutterUmengPlugin {
    /**
     * Plugin registration.
     */
    private static UmengHandler umengHandler;

    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_umeng");

        if (umengHandler == null) {
            umengHandler = new UmengHandler(registrar);
        }

        channel.setMethodCallHandler(umengHandler);
    }

}
