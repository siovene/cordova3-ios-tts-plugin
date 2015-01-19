package com.lefortsoftware.ttsplugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.speech.tts.TextToSpeech;
import java.util.Locale;

import android.widget.Toast;

public class TtsPlugin extends CordovaPlugin {
    TextToSpeech tts;
    CallbackContext cbc;

    @Override
    public boolean execute(
        String action, JSONArray args,
        CallbackContext callbackContext) throws JSONException
    {
        cbc = callbackContext;

        if(action.equals("initTTS")) {
            tts = new TextToSpeech(
                cordova.getActivity().getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status == TextToSpeech.SUCCESS) {
                            cbc.success("TextToSpeech initialized");
                        } else {
                            cbc.error("Could not initialize TextToSpeech");
                        }
                    }
                }
            );
        } else if(action.equals("speak")) {
            String txt = args.getString(0);
            tts.speak(txt, TextToSpeech.QUEUE_ADD, null);
        } else if(action.equals("stop")) {
            tts.stop();
        } else if(action.equals("setLanguage")){
            // Not implemented
            return false;
        } else if(action.equals("setRate")){
            // Not implemented
            return false;
        } else {
            return false;
        }

        cbc.success("OK");
        return true;
    }
}
