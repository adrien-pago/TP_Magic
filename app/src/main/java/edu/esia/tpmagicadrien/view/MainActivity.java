package edu.esia.tpmagicadrien.view;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import edu.esia.tpmagicadrien.R;
import edu.esia.tpmagicadrien.viewmodel.MagicViewModel;

// Activité principale de l'application
public class MainActivity extends AppCompatActivity {
    // ViewModel pour accéder aux données
    private MagicViewModel model;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialisation du ViewModel
        this.model = new ViewModelProvider(this).get(MagicViewModel.class);
        // Définir le contexte dans le ViewModel
        this.model.setContext(this);
        // Définir le layout de l'activité
        setContentView(R.layout.activity_main);
    }

    // Méthode pour activer ou désactiver le bouton d'ajout de deck
    public void setAddDeckButtonEnabled(final boolean enabled) {
        final ImageButton b = findViewById(R.id.ib_addDeck);
        b.setEnabled(enabled);
        if (enabled) {
            b.setAlpha(1.0f); // Rendre le bouton complètement opaque
        } else {
            b.setAlpha(0.5f); // Rendre le bouton semi-transparent
        }
    }
}