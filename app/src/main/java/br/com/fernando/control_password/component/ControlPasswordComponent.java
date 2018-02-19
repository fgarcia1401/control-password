package br.com.fernando.control_password.component;

import br.com.fernando.control_password.MainActivity;
import br.com.fernando.control_password.module.ControlPasswordModule;
import dagger.Component;

/**
 * Created by tqiuser on 19/02/18.
 */


@Component(modules = ControlPasswordModule.class)
public interface ControlPasswordComponent {

    void inject(MainActivity mainActivity);

}
