package edu.esia.tpmagicadrien.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import edu.esia.tpmagicadrien.model.AbstractCard;
import edu.esia.tpmagicadrien.model.Deck;
import edu.esia.tpmagicadrien.model.db.MagicDatabase;
import edu.esia.tpmagicadrien.model.db.dao.CardsDao;
import edu.esia.tpmagicadrien.model.db.dao.DecksDao;

// ViewModel pour gérer les données de l'application
public class MagicViewModel extends ViewModel {

    // Contexte de l'application
    private Context ctx;
    // DAO pour accéder aux cartes
    private CardsDao cardsDao;
    // DAO pour accéder aux decks
    private DecksDao decksDao;

    // Deck actuellement sélectionné
    private Deck curDeck;
    // Carte actuellement sélectionnée
    private AbstractCard curCard;
    // Liste de toutes les cartes du deck actuel
    private List<AbstractCard> allCardsFromCurrentDeck = new ArrayList<>();

    // Définir le contexte de l'application
    public void setContext(final Context ctx) {
        this.ctx = ctx;
        final MagicDatabase db = MagicDatabase.getInstance(ctx);
        this.cardsDao = db.getCardsDao();
        this.decksDao = db.getDecksDao();
    }

    // Obtenir le contexte de l'application
    public Context getContext() {
        return this.ctx;
    }

    // Obtenir le deck actuellement sélectionné
    public Deck getCurDeck() {
        return this.curDeck;
    }

    // Définir le deck actuellement sélectionné
    public void setCurDeck(final Deck curDeck) {
        this.curDeck = curDeck;
    }

    // Obtenir la carte actuellement sélectionnée
    public AbstractCard getCurCard() {
        return this.curCard;
    }

    // Définir la carte actuellement sélectionnée
    public void setCurCard(final AbstractCard curCard) {
        this.curCard = curCard;
    }

    // Ajouter un deck à la base de données
    public void addDeck(final Deck d) {
        this.decksDao.insert(d);
    }

    // Obtenir tous les decks
    public LiveData<List<Deck>> getAllDecks() {
        return this.decksDao.getAllDecks();
    }

    // Obtenir toutes les cartes d'un deck
    public LiveData<List<AbstractCard>> getAllCardsFromDeck(final long deckId) {
        this.allCardsFromCurrentDeck.clear();
        MediatorLiveData<List<AbstractCard>> ret = new MediatorLiveData<>();
        ret.addSource(this.cardsDao.getLandsByDeckId(deckId), value -> {
            update(((List)value), ret);
        });
        ret.addSource(this.cardsDao.getCreaturesByDeckId(deckId), value -> {
            update(((List)value), ret);
        });
        ret.addSource(this.cardsDao.getArtefactByDeckId(deckId), value -> {
            update(((List)value), ret);
        });
        ret.addSource(this.cardsDao.getRitualsByDeckId(deckId), value -> {
            update(((List)value), ret);
        });
        ret.addSource(this.cardsDao.getInterruptionsByDeckId(deckId), value -> {
            update(((List)value), ret);
        });
        return ret;
    }

    // Mettre à jour la liste des cartes
    void update(List<AbstractCard> value, MediatorLiveData<List<AbstractCard>> ld) {
        this.allCardsFromCurrentDeck.addAll(value);
        ld.setValue(this.allCardsFromCurrentDeck);
    }
}