package com.example.flutterumeng;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class UmengHandler implements MethodChannel.MethodCallHandler, Application.ActivityLifecycleCallbacks {
    private PluginRegistry.Registrar registrar;

    public UmengHandler(PluginRegistry.Registrar registrar) {
        this.registrar = registrar;
        context = registrar.context();
        ((Application) registrar.activeContext().getApplicationContext()).registerActivityLifecycleCallbacks(this);
    }

    private Context context;

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String method = methodCall.method;
        if ("init".equals(method)) {
            init(result);
        } else if ("addCountEvent".equals(method)) {
            addCountEvent(methodCall, result);
        } else if ("addParamsEvent".equals(method)) {
            addCountMapEvent(methodCall, result);
        } else if ("reportError".equals(method)) {
            reportError(methodCall, result);
        }
    }

    private void init(MethodChannel.Result result) {
        result.success(true);
    }

    private void addCountEvent(MethodCall methodCall, MethodChannel.Result result) {
        String eventId = (String) methodCall.arguments;
        MobclickAgent.onEvent(context, eventId);
        result.success(true);
    }

    private void addCountMapEvent(MethodCall methodCall, MethodChannel.Result result) {
        List<Object> list = methodCall.arguments();
        String eventId = (String) list.get(0);
        Map<Object, Object> params = (Map) list.get(1);
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<Object, Object> entry : params.entrySet()) {
            if (entry.getKey() instanceof String && entry.getValue() instanceof String) {
                map.put(((String) entry.getKey()), ((String) entry.getValue()));
            }
        }
        MobclickAgent.onEvent(context, eventId, map);
        result.success(true);
    }

    private void reportError(MethodCall methodCall, MethodChannel.Result result) {
        String errorMsg = ((String) methodCall.arguments);
        MobclickAgent.reportError(context, errorMsg);
        result.success(true);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        MobclickAgent.onResume(activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        MobclickAgent.onPause(activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
