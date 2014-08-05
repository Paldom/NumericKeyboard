package hu.dpal.phonegap.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TableLayout;

public class UniqueDeviceID extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {

        ViewGroup cordovaViewGroup;
        RelativeLayout wrapperView;
        TableLayout numpadView;
        
        if (action.equals("show")) {
            
            String id = args.getString(0);
            
            wrapperView = new RelativeLayout(cordova.getActivity());
            cordovaViewGroup = (ViewGroup) webView.getParent();
            cordovaViewGroup.removeView(webView);
            wrapperView.addView(webView);
            cordova.getActivity().setContentView(wrapperView);
            
            numpadView = (TableLayout)findViewById(R.id.tblNumpad);
            
            RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(
                                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            lparams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
                                    RelativeLayout.TRUE);
            lparams.addRule(RelativeLayout.CENTER_HORIZONTAL,
                                RelativeLayout.TRUE);
            
            numpadView.setLayoutParams(params);
            wrapperView.addView(numpadView);
            
            return true;
        } else if (action.equals("hide")) {
            
            wrapperView.removeView(webView);
            wrapperView.removeView(numpadView);
            cordovaViewGroup.addView(webView);
            cordova.getActivity().setContentView(cordovaViewGroup);
            wrapperView = null;
            
            return true;
        }

        return false;
    }

}