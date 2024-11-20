package edu.esia.tpmagicadrien.view.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;

// Classe utilitaire pour les opérations liées à l'interface utilisateur
public final class UiUtils {

    // Méthode pour obtenir l'activité contenant une vue donnée
    public static Activity getActivityContaining(final View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    // Méthode pour appliquer un gradient personnalisé à une vue
    public static void fillCustomGradient(final View v, final int[] colors) {
        final View view = v;
        final Drawable[] layers = new Drawable[1];
        final int nbColors = colors.length;
        final float[] positions = new float[nbColors];
        positions[0] = 0;
        positions[nbColors - 1] = 1;
        if (nbColors > 2) {
            final float div = 1.0f / nbColors;
            float cur = 0;
            for (int i = 1; i < nbColors - 1; i++) {
                cur += div;
                positions[i] = cur;
            }
        }
        final ShapeDrawable.ShaderFactory sf = new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(final int width, final int height) {
                return new LinearGradient(0, 0, view.getHeight() * 2, view.getHeight() * 2,
                        colors, positions, Shader.TileMode.REPEAT);
            }
        };
        final PaintDrawable p = new PaintDrawable();
        p.setAlpha(128);
        p.setShape(new RectShape());
        p.setShaderFactory(sf);
        p.setCornerRadii(new float[]{5, 5, 5, 5, 0, 0, 0, 0});
        layers[0] = (Drawable) p;

        final LayerDrawable composite = new LayerDrawable(layers);
        view.setBackground(composite);
    }

    /*
    // Méthode pour dessiner un arrière-plan à partir d'une image (commentée)
    public static void drawBackgroundFromImage(final View v, final int imageResourceId) {
        v.setBackgroundResource();
        final View view = v;
        final Drawable[] layers = new Drawable[1];
        final ShapeDrawable.ShaderFactory sf = new ShapeDrawable.ShaderFactory() {
            @Override
            public Shader resize(final int width, final int height) {
                return new LinearGradient(0, 0, view.getHeight() * 2, view.getHeight() * 2,
                        colors, positions, Shader.TileMode.REPEAT);
            }
        };
        final PaintDrawable p = new PaintDrawable();
        p.setAlpha(128);
        p.setShape(new RectShape());
        p.set
        p.setShaderFactory(sf);
        p.setCornerRadii(new float[]{5, 5, 5, 5, 0, 0, 0, 0});
        layers[0] = (Drawable) p;

        final LayerDrawable composite = new LayerDrawable(layers);
        view.setBackground(composite);
    }
    */
}