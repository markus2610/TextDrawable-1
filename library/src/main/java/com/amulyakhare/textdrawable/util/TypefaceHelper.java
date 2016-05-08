package com.amulyakhare.textdrawable.util;

import android.content.Context;
import android.graphics.Typeface;
import java.util.Hashtable;

public class TypefaceHelper {
  private static final Hashtable<String, Typeface> cache = new Hashtable<>();

  private static final String FONTS_ASSET_PATH = "fonts/";
  private static final String FILE_EXTENSION = ".ttf";

  public static Typeface get(Context context, String fontFamilyName) {
    return get(context, fontFamilyName, -1);
  }

  public static Typeface get(Context context, String fontFamilyName, int style) {
    synchronized (cache) {
      if (!cache.containsKey(fontFamilyName)) {
        try {

          String fileName = new StringBuilder().append(FONTS_ASSET_PATH)
              .append(fontFamilyName)
              .append(FILE_EXTENSION)
              .toString();

          Typeface t = Typeface.createFromAsset(context.getAssets(), fileName);
          cache.put(fontFamilyName, t);
        } catch (Exception e) {
          if (style != -1) {
            return Typeface.create(fontFamilyName, style);
          }
          return null;
        }
      }

      return cache.get(fontFamilyName);
    }
  }
}
