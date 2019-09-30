package com.github.dhaval2404.material_icon_generator.constant;

/**
 * Supported Theme by Plugin
 *
 * Created by Dhaval Patel on 29 September 2019.
 */
public enum Theme {
    FILL("baseline"),
    OUTLINE("outline"),
    ROUND("round"),
    TWO_TONE("twotone"),
    SHARP("sharp");

    private String value;

    Theme(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Theme getTheme(String theme){
        switch (theme.toLowerCase()){
            case "outline":
                return Theme.OUTLINE;
            case "round":
                return Theme.ROUND;
            case "twotone":
                return Theme.TWO_TONE;
            case "sharp":
                return Theme.SHARP;
            case "fill":
            default:
                return Theme.FILL;
        }
    }
}
