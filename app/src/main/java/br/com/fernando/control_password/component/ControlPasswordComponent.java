package br.com.fernando.control_password.component;

import br.com.fernando.control_password.ui.activities.MainActivity;
import br.com.fernando.control_password.module.ControlPasswordModule;
import br.com.fernando.control_password.ui.activities.RegisterActivity;
import br.com.fernando.control_password.ui.fragments.LoginFragment;
import br.com.fernando.control_password.ui.fragments.MainFragment;
import dagger.Component;

/**
 * Created by Fernando
 */


@Component(modules = ControlPasswordModule.class)
public interface ControlPasswordComponent {

    void inject(MainActivity mainActivity);

    void inject(RegisterActivity registerActivity);

    void inject(LoginFragment loginFragment);

    void inject(MainFragment mainFragment);

}
