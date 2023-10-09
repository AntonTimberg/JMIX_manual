package com.company.testing.screen.myonboarding;

import com.company.testing.entity.User;
import com.company.testing.entity.UserStep;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.Install;
import io.jmix.ui.screen.Screen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.time.LocalDate;

@UiController("MyOnboardingScreen")
@UiDescriptor("my-onboarding-screen.xml")
public class MyOnboardingScreen extends Screen {
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Autowired
    private CollectionLoader<UserStep> userStepsDl;

    @Autowired
    private UiComponents uiComponents;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        User user = (User) currentAuthentication.getUser();
        userStepsDl.setParameter("user", user);
        userStepsDl.load();
    }


}