package com.nunens.pinkd.util;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FontUtil {

    private static Typeface typeFace;

    public static final String ROBOTO_REGULAR = "Roboto-Regular";
    public static final String ROBOTO_BOLD = "Roboto-Bold";
    public static final String ROBOTO_ITALIC = "Roboto-Italic";
    //public static final String MONS_BOLD = "mons-bold";
    //public static final String MONS_REG = "mons_regular";
    public static final String CHEMIST = "chemist";
    public static final String ROBOTO_BLACK = "Roboto-Black";
    public static final String ROBOTO_BLACKITALIC = "Roboto-BlackItalic";
    public static final String ROBOTO_BOLD_CONDENSED = "Roboto-BoldCondensed";
    public static final String ROBOTO_BOLD_CONDENSED_ITALIC = "Roboto-BoldCondensedItalic";
    public static final String ROBOTO_BOLD_ITALIC = "Roboto-BoldItalic";
    public static final String ROBOTO_CONDENSED = "Roboto-Condensed";
    public static final String ROBOTOCONDENSED_ITALIC = "Roboto-CondensedItalic";
    public static final String ROBOTO_LIGHT = "Roboto-Light";
    public static final String ROBOTO_LIGHT_ITALIC = "Roboto-LightItalic";
    public static final String ROBOTO_MEDIUM = "Roboto-Medium";
    public static final String ROBOTO_MEDIUM_ITALIC = "Roboto-MediumItalic";
    public static final String ROBOTO_THIN = "Roboto-Thin";
    public static final String ROBOTO_THIN_ITALIC = "Roboto-ThinItalic";
    public static final String ROBOTO_CONDENSED_BOLD = "Roboto-Condensed-Bold";
    public static final String ROBOTO_CONDENSED_BOLDITALIC = "Roboto-Condensed-BoldItalic";
    public static final String ROBOTO_CONDENSED_ITALIC = "RobotoCondensed-Italic";
    public static final String ROBOTO_CONDENSED_BOLD_ITALIC = "RobotoCondensed-BoldItalic";
    public static final String ROBOTOCONDENSED_LIGHT = "RobotoCondensed-Light";
    public static final String ROBOTOCONDENSED_LIGHTITALIC = "RobotoCondensed-LightItalic";
    public static final String ROBOTOCONDENSED_REGULAR = "RobotoCondensed-Regular";
    public static final String BORDEAUX_BLACK_REGULAR = "Bordeaux-Black-Regular";
    public static final String CLOCKOPIA = "Clockopia";
    public static final String DROIDSANS = "DroidSans";
    public static final String DROIDSANS_BOLD = "DroidSans-Bold";

    public static void setCustomTypeface(Context context, View view, String fontType) {
        if (typeFace == null) {
            typeFace = Typeface.createFromAsset(context.getAssets(),
                    "fonts/" + fontType + ".ttf");
        }

        setFont(view, typeFace);
    }

    private static void setFont(View view, Typeface typeFace) {
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                setFont(((ViewGroup) view).getChildAt(i), typeFace);
            }
        } else if (view instanceof TextView) {
            ((TextView) view).setTypeface(typeFace);
        } else if (view instanceof Button) {
            ((Button) view).setTypeface(typeFace);
        } else if (view instanceof EditText) {
            ((EditText) view).setTypeface(typeFace);
        }
    }

}
