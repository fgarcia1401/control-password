package br.com.fernando.control_password.app;

import android.app.Application;

import br.com.fernando.control_password.component.ControlPasswordComponent;
import br.com.fernando.control_password.component.DaggerControlPasswordComponent;
import br.com.fernando.control_password.module.ControlPasswordModule;

/**
 * Created by Fernando
 */


public class ControlPasswordApplication extends Application {

    private ControlPasswordComponent controlPasswordComponent;

    public ControlPasswordComponent getComponent() {
        return controlPasswordComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        controlPasswordComponent = DaggerControlPasswordComponent.builder()
                .controlPasswordModule(new ControlPasswordModule(this))
                .build();
    }
}
