package hu.dpal.phonegap.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TableLayout;

public class NumericKeyboard extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        ViewGroup cordovaViewGroup = null;
        RelativeLayout wrapperView = null;
        TableLayout numpadView = null;
        Activity cdvActivity;
        
        if (action.equals("show")) {
            
            String id = args.getString(0);
            
            cdvActivity = cordova.getActivity();
            
            wrapperView = new RelativeLayout(cordova.getActivity());
            cordovaViewGroup = (ViewGroup) webView.getParent();
            cordovaViewGroup.removeView(webView);
            wrapperView.addView(webView);
            cdvActivity.setContentView(wrapperView);
            
            numpadView = (TableLayout) cdvActivity.findViewById(
            		cdvActivity.getApplication().getResources().getIdentifier(
            				"numpad",
            				"layout",
            				cdvActivity.getApplication().getPackageName()));
            
            RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(
                                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            lparams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
                                    RelativeLayout.TRUE);
            lparams.addRule(RelativeLayout.CENTER_HORIZONTAL,
                                RelativeLayout.TRUE);
            
            numpadView.setLayoutParams(lparams);
            wrapperView.addView(numpadView);
            
            return true;
        } else if (action.equals("hide")) {
            
        	if (wrapperView != null) {
	            wrapperView.removeView(webView);
	            wrapperView.removeView(numpadView);
	            cordovaViewGroup.addView(webView);
	            cdvActivity.setContentView(cordovaViewGroup);
	            wrapperView = null;
                cdvActivity = null;
        	}
            
            
            return true;
        }

        return false;
    }

}