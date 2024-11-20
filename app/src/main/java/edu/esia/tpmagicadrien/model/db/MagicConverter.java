package edu.esia.tpmagicadrien.model.db;

import android.text.TextUtils;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Iterator;

import edu.esia.tpmagicadrien.model.Cost;
import edu.esia.tpmagicadrien.model.Effect;
import edu.esia.tpmagicadrien.model.enums.Color;
import edu.esia.tpmagicadrien.model.enums.CreatureCharacteristic;

public class MagicConverter {

    // Séparateurs utilisés pour convertir les listes en chaînes de caractères et vice versa
    private static final String MAIN_SEPARATOR = "#";
    private static final String COST_SEPARATOR = " ";
    private static final String EFFECT_SEPARATOR = "¤";

    // Convertit une liste d'effets en une chaîne de caractères
    @TypeConverter
    public static String listOfEffecsToString(final ArrayList<Effect> list) {
        String ret = "";
        if (list == null) {
            return ret;
        }
        boolean first = true;
        for (final Effect effect : list) {
            if (first) {
                first = false;
            } else {
                ret = ret.concat(MagicConverter.MAIN_SEPARATOR);
            }
            ret = ret.concat(costToString(effect.getCost()));
            ret = ret.concat(EFFECT_SEPARATOR);
            ret = ret.concat(effect.getDescription());
        }
        return ret;
    }

    // Convertit une chaîne de caractères en une liste d'effets
    @TypeConverter
    public static ArrayList<Effect> stringToEffectsList(final String listAsString) {
        final ArrayList<Effect> ret = new ArrayList<>();
        if (listAsString != null && !listAsString.isEmpty()) {
            final TextUtils.StringSplitter splitter = new TextUtils.SimpleStringSplitter(MagicConverter.MAIN_SEPARATOR.charAt(0));
            splitter.setString(listAsString);
            for (final Iterator<String> it = splitter.iterator(); it.hasNext(); ) {
                final String effectAsStr = it.next();
                final String[] effectArr = effectAsStr.split(EFFECT_SEPARATOR);
                final Cost cost = stringToCost(effectArr[0]);
                final Effect effect = new Effect(cost, effectArr[1]);
                ret.add(effect);
            }
        }
        return ret;
    }

    // Convertit une chaîne de caractères en un coût
    @TypeConverter
    public static Cost stringToCost(final String currentCost) {
        if (currentCost != null && !currentCost.isEmpty()) {
            final String[] parts = currentCost.split(COST_SEPARATOR);
            final int amount = Integer.parseInt(parts[0]);
            final Color color = Color.valueOf(parts[1]);
            return new Cost(amount, color);
        }
        return null;
    }

    // Convertit un coût en une chaîne de caractères
    @TypeConverter
    public static String costToString(final Cost cost) {
        String ret = "";
        if (cost != null) {
            ret = ret.concat(Integer.toString(cost.getAmount()));
            ret = ret.concat(COST_SEPARATOR);
            ret = ret.concat(cost.getColor().name());
        }
        return ret;
    }

    // Convertit une chaîne de caractères en une liste de coûts
    @TypeConverter
    public static ArrayList<Cost> stringToCostsList(final String listAsString) {
        final ArrayList<Cost> ret = new ArrayList<>();
        if (listAsString != null && !listAsString.isEmpty()) {
            final TextUtils.StringSplitter splitter = new TextUtils.SimpleStringSplitter(MagicConverter.MAIN_SEPARATOR.charAt(0));
            splitter.setString(listAsString);
            for (final Iterator<String> it = splitter.iterator(); it.hasNext(); ) {
                final String currentCost = it.next();
                ret.add(stringToCost(currentCost));
            }
        }
        return ret;
    }

    // Convertit une liste de coûts en une chaîne de caractères
    @TypeConverter
    public static String listOfCostsToString(final ArrayList<Cost> list) {
        String ret = "";
        if (list == null) {
            return ret;
        }
        boolean first = true;
        for (final Cost cost : list) {
            if (first) {
                first = false;
            } else {
                ret = ret.concat(MagicConverter.MAIN_SEPARATOR);
            }
            ret = ret.concat(costToString(cost));
        }
        return ret;
    }

    // Convertit une chaîne de caractères en une liste de caractéristiques de créature
    @TypeConverter
    public static ArrayList<CreatureCharacteristic> stringToCharacteristicsList(final String listAsString) {
        final ArrayList<CreatureCharacteristic> ret = new ArrayList<>();
        if (listAsString != null && !listAsString.isEmpty()) {
            final TextUtils.StringSplitter splitter = new TextUtils.SimpleStringSplitter(MagicConverter.MAIN_SEPARATOR.charAt(0));
            splitter.setString(listAsString);
            for (final Iterator<String> it = splitter.iterator(); it.hasNext(); ) {
                final String currentChar = it.next();
                final CreatureCharacteristic charac = CreatureCharacteristic.valueOf(currentChar);
                ret.add(charac);
            }
        }
        return ret;
    }

    // Convertit une liste de caractéristiques de créature en une chaîne de caractères
    @TypeConverter
    public static String listOfCharacteristicsToString(final ArrayList<CreatureCharacteristic> list) {
        String ret = "";
        if (list == null) {
            return ret;
        }
        boolean first = true;
        for (final CreatureCharacteristic charac : list) {
            if (first) {
                first = false;
            } else {
                ret = ret.concat(MagicConverter.MAIN_SEPARATOR);
            }
            ret = ret.concat(charac.name());
        }
        return ret;
    }

    // Convertit une chaîne de caractères en une liste de couleurs
    @TypeConverter
    public static ArrayList<Color> stringToColorsList(final String listAsString) {
        final ArrayList<Color> ret = new ArrayList<>();
        if (listAsString != null && !listAsString.isEmpty()) {
            final TextUtils.StringSplitter splitter = new TextUtils.SimpleStringSplitter(MagicConverter.MAIN_SEPARATOR.charAt(0));
            splitter.setString(listAsString);
            for (final Iterator<String> it = splitter.iterator(); it.hasNext(); ) {
                final String currentChar = it.next();
                final Color charac = Color.valueOf(currentChar);
                ret.add(charac);
            }
        }
        return ret;
    }

    // Convertit une liste de couleurs en une chaîne de caractères
    @TypeConverter
    public static String listOfColorsToString(final ArrayList<Color> list) {
        String ret = "";
        if (list == null) {
            return ret;
        }
        boolean first = true;
        for (final Color charac : list) {
            if (first) {
                first = false;
            } else {
                ret = ret.concat(MagicConverter.MAIN_SEPARATOR);
            }
            ret = ret.concat(charac.name());
        }
        return ret;
    }

}