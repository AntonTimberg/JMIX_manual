package com.company.testing.screen.step;

import io.jmix.ui.screen.*;
import com.company.testing.entity.Step;

@UiController("Step.edit")
@UiDescriptor("step-edit.xml")
@EditedEntityContainer("stepDc")
public class StepEdit extends StandardEditor<Step> {
}