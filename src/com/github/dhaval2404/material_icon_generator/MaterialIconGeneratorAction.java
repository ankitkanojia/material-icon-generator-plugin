package com.github.dhaval2404.material_icon_generator;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.annotation.Nonnull;

/**
 * Provide Initial Trigger for the Menu Item Click
 *
 * Ref: https://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/creating_an_action.html
 *
 * Created by Dhaval Patel on 29 September 2019.
 */
public class MaterialIconGeneratorAction extends AnAction {

    /**
     * actionPerformed method is called each time you select a menu item or click a toolbar button
     */
    public void actionPerformed(@Nonnull AnActionEvent event) {
        MaterialDesignIconGenerateDialog dialog = new MaterialDesignIconGenerateDialog(getEventProject(event));
        dialog.show();
    }

}