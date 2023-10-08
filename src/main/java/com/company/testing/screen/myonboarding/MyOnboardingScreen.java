package com.company.testing.screen.myonboarding;

import com.company.testing.entity.User;
import com.company.testing.entity.UserStep;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("MyOnboardingScreen")
@UiDescriptor("myonboarding-screen.xml")
public class MyOnboardingScreen extends Screen {
    @Autowired
    private CurrentAuthentication currentAuthentication;
    @Autowired
    private CollectionLoader<UserStep> userStepsDl;

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        final User user = (User) currentAuthentication.getUser();
        userStepsDl.setParameter("user", user);
        userStepsDl.load();
    }
}