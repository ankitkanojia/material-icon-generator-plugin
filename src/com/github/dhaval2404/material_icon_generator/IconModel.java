package com.github.dhaval2404.material_icon_generator;

import com.github.dhaval2404.material_icon_generator.constant.Theme;
import com.intellij.openapi.project.Project;

import java.io.File;

/*
 * Copyright 2014-2015 Material Design Icon Generator (Yusuke Konishi)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class IconModel {

    private static final String PATH_ICONS = "/icons";
    private static final String PATH_DRAWABLE_PREFIX = "drawable-";
    private static final String VECTOR_DEFAULT_DP = "24dp";
    private static final String VECTOR_VIEWPORT_SIZE = "24.0";
    private static final String UNDERBAR = "_";
    private static final String PNG_SUFFIX = ".png";
    private static final String XML_SUFFIX = ".xml";
    private static final String BLACK = "black";
    private static final String DP = "dp";

    private String iconName;
    private String displayColorName;
    private String colorCode;
    private String dp;
    private Theme theme;
    private String fileName;
    private String resDir;

    private boolean mdpi;
    private boolean hdpi;
    private boolean xhdpi;
    private boolean xxhdpi;
    private boolean xxxhdpi;

    private boolean isVectorType;
    private boolean drawable;

    public IconModel(String iconName,
                     String displayColorName,
                     String colorCode,
                     String theme,
                     String dp,
                     String fileName,
                     String resDir,
                     boolean mdpi,
                     boolean hdpi,
                     boolean xhdpi,
                     boolean xxhdpi,
                     boolean xxxhdpi,
                     boolean isVectorType,
                     boolean drawable) {
        this.iconName = iconName;
        this.displayColorName = displayColorName;
        this.colorCode = colorCode;
        this.theme = Theme.getTheme(theme);
        this.dp = dp;
        this.fileName = fileName;
        this.resDir = resDir;
        this.mdpi = mdpi;
        this.hdpi = hdpi;
        this.xhdpi = xhdpi;
        this.xxhdpi = xxhdpi;
        this.xxxhdpi = xxxhdpi;
        this.isVectorType = isVectorType;
        this.drawable = drawable;
    }

    public String getLocalPath(String size, boolean shouldForcePng) {
        if (iconName != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(PATH_ICONS);

            String[] fileString = iconName.split("/");
            String fileName = fileString[1];

            boolean isVector = isVectorType && !shouldForcePng;
            String iconName = isVector
                    ? getVectorIconName(fileName)
                    : getImageIconName(fileName);

            sb.append(getLocalDrawableIconPath(iconName, size, isVector));

            return sb.toString();
        } else {
            return "";
        }
    }

    public String getLocalPath(String size) {
        return getLocalPath(size, false);
    }

    public String getVectorLocalPath() {
        return getLocalPath(null);
    }

    private String getLocalDrawableIconPath(String fileName, String size, boolean isVector) {
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        String[] fileString = iconName.split("/");
        sb.append(fileString[0]);
        sb.append("/");
        sb.append(fileString[1]);
        sb.append("/");

        if(isVector || size==null){
            sb.append("drawable");
            sb.append("/");
        }else{
            sb.append(PATH_DRAWABLE_PREFIX);
            sb.append(size);
            sb.append("/");
        }

        sb.append(fileName);
        return sb.toString();
    }

    private String getImageIconName(String shortName) {
        return getIconName(shortName, BLACK, theme, dp, false, false);
    }

    private String getVectorIconName(String shortName) {
        return getIconName(shortName, BLACK, theme, VECTOR_DEFAULT_DP, true, false);
    }

    private String getIconName(String shortName, String colorName) {
        return getIconName(shortName, colorName, this.theme, this.dp, isVectorType, true);
    }

    private String getIconName(String shortName, String colorName, Theme theme, String dp, boolean isVectorType, boolean isResource) {
        StringBuilder sb = new StringBuilder();
        sb.append(theme.getValue());
        sb.append(UNDERBAR);
        sb.append(shortName);
        sb.append(UNDERBAR);

        if (!isResource){

            if (!isVectorType) {
                //Add Color for PNG image only
                sb.append(colorName);
                sb.append(UNDERBAR);
            }

            if (dp.contains("dp")) {
                dp = dp.replaceAll("dp", "");
            }
        }else{
            sb.append(colorName);
            sb.append(UNDERBAR);
        }

        sb.append(dp);

        String suffix = isVectorType ? XML_SUFFIX : PNG_SUFFIX;
        sb.append(suffix);
        return sb.toString();
    }

    public String getResourcePath(Project project) {
        return resDir;
    }

    public String getCopyPath(Project project, String size) {
        StringBuilder sb = new StringBuilder();
        sb.append(getResourcePath(project));
        sb.append(File.separator);
        sb.append(PATH_DRAWABLE_PREFIX);
        sb.append(size);
        sb.append(File.separator);
        sb.append(fileName);

        return sb.toString();
    }

    public String getVectorCopyPath(Project project, String dir) {
        StringBuilder sb = new StringBuilder();
        sb.append(getResourcePath(project));
        sb.append(File.separator);
        sb.append(dir);
        sb.append(File.separator);
        sb.append(fileName);

        return sb.toString();
    }

    public void setIconAndFileName(String iconName) {
        if (iconName == null) {
            this.iconName = "";
            this.fileName = "";
        } else {
            this.iconName = iconName;
            setFileNameFromIconName();
        }
    }

    public void setDpAndFileName(String dp) {
        this.dp = dp;
        setFileNameFromIconName();
    }

    public void setThemeAndFileName(String theme) {
        this.theme = Theme.getTheme(theme);
        setFileNameFromIconName();
    }

    public void setDisplayColorName(String displayColorName) {
        this.displayColorName = displayColorName;
        setFileNameFromIconName();
    }

    public void setVectorTypeAndFileName(boolean vectorType) {
        isVectorType = vectorType;
        setFileNameFromIconName();
    }

    private void setFileNameFromIconName() {
        String[] fileString = this.iconName.split("/");
        if (fileString.length > 1) this.fileName = getIconName(fileString[1], displayColorName);
    }

    public void setResDir(String resDir) {
        this.resDir = resDir;
    }

    public boolean isMdpi() {
        return mdpi;
    }

    public void setMdpi(boolean mdpi) {
        this.mdpi = mdpi;
    }

    public boolean isHdpi() {
        return hdpi;
    }

    public void setHdpi(boolean hdpi) {
        this.hdpi = hdpi;
    }

    public boolean isXhdpi() {
        return xhdpi;
    }

    public void setXhdpi(boolean xhdpi) {
        this.xhdpi = xhdpi;
    }

    public boolean isXxhdpi() {
        return xxhdpi;
    }

    public void setXxhdpi(boolean xxhdpi) {
        this.xxhdpi = xxhdpi;
    }

    public boolean isXxxhdpi() {
        return xxxhdpi;
    }

    public void setXxxhdpi(boolean xxxhdpi) {
        this.xxxhdpi = xxxhdpi;
    }

    public String getDp() {
        return dp;
    }

    public String getViewportSize() {
        return VECTOR_VIEWPORT_SIZE;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public boolean isVectorType() {
        return isVectorType;
    }

    public boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }

}