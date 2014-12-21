package org.meridor.arabica.support;

import org.meridor.arabica.Launcher;
import org.meridor.arabica.LauncherAware;
import org.meridor.arabica.support.annotations.RequiresUI;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ArabicaInvocationHandler implements InvocationHandler {

    private final LauncherAware launcherAware;
    private final Object implementationInstance;

    public ArabicaInvocationHandler(LauncherAware launcherAware, Object implementationInstance) {
        this.launcherAware = launcherAware;
        this.implementationInstance = implementationInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method instanceMethod = implementationInstance.getClass().getMethod(method.getName(), method.getParameterTypes());
        if (requiresUI(implementationInstance, instanceMethod)) {
            getLauncher().requireLaunchedApplication();
            return getLauncher().withUI(() -> instanceMethod.invoke(implementationInstance, args));
        }
        return instanceMethod.invoke(args);
    }

    private boolean requiresUI(Object implementationInstance, Method method) {
        return
                implementationInstance.getClass().isAnnotationPresent(RequiresUI.class)
                || method.isAnnotationPresent(RequiresUI.class);
    }

    private Launcher<?> getLauncher() {
        return launcherAware.getLauncher();
    }

}
