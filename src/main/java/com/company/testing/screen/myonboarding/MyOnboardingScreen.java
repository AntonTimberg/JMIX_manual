package com.company.testing.screen.myonboarding;

import com.company.testing.entity.User;
import com.company.testing.entity.UserStep;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.UiComponents;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.model.CollectionContainer;
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

    @Autowired
    private Label totalStepsLabel;

    @Autowired
    private Label completedStepsLabel;

    @Autowired
    private Label overdueStepsLabel;

    @Autowired
    private CollectionContainer<UserStep> userStepsDc;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        User user = (User) currentAuthentication.getUser();
        userStepsDl.setParameter("user", user);
        userStepsDl.load();
    }

//    @Install(to = "userStepsTable.completed", subject = "columnGenerator")
//    private Component userStepsTableCompletedColumnGenerator(UserStep userStep) {
//        CheckBox checkBox = uiComponents.create(CheckBox.class);
//        checkBox.setValue(userStep.getCompletedDate() != null);
//        checkBox.addValueChangeListener(e -> {
//            if (userStep.getCompletedDate() == null) {
//                userStep.setCompletedDate(LocalDate.now());
//            } else {
//                userStep.setCompletedDate(null);
//            }
//        });
//        return (Component) checkBox;
//    }
//
//    private void updateLabels() {
//        totalStepsLabel.setValue("Total steps: " + userStepsDc.getItems().size());
//
//        long completedCount = userStepsDc.getItems().stream()
//                .filter(us -> us.getCompletedDate() != null)
//                .count();
//        completedStepsLabel.setValue("Completed steps: " + completedCount);
//
//        long overdueCount = userStepsDc.getItems().stream()
//                .filter(us -> isOverdue(us))
//                .count();
//        overdueStepsLabel.setValue("Overdue steps: " + overdueCount);
//    }

    private boolean isOverdue(UserStep us) {
        return us.getCompletedDate() == null
                && us.getDueDate() != null
                && us.getDueDate().isBefore(LocalDate.now());
    }
}