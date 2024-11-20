package edu.esia.tpmagicadrien.model.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.esia.tpmagicadrien.model.Artefact;
import edu.esia.tpmagicadrien.model.Cost;
import edu.esia.tpmagicadrien.model.Creature;
import edu.esia.tpmagicadrien.model.Deck;
import edu.esia.tpmagicadrien.model.Effect;
import edu.esia.tpmagicadrien.model.Interruption;
import edu.esia.tpmagicadrien.model.Land;
import edu.esia.tpmagicadrien.model.Ritual;
import edu.esia.tpmagicadrien.model.db.dao.CardsDao;
import edu.esia.tpmagicadrien.model.db.dao.DecksDao;
import edu.esia.tpmagicadrien.model.enums.Color;
import edu.esia.tpmagicadrien.model.enums.CreatureCharacteristic;

// Annotation pour définir la base de données Room avec les entités et les convertisseurs
@Database(version = 1, entities = {Deck.class,
        Land.class, Creature.class, Artefact.class, Interruption.class, Ritual.class})
@TypeConverters(MagicConverter.class)
public abstract class MagicDatabase extends RoomDatabase {

    // Méthodes abstraites pour obtenir les DAO
    public abstract CardsDao getCardsDao();
    public abstract DecksDao getDecksDao();

    // Instance unique de la base de données
    private static volatile MagicDatabase instance;
    private final static int THREADS_NUMBER;
    private final static ExecutorService DB_WRITE_EXECUTOR;

    static {
        // Nombre de threads pour les opérations en arrière-plan
        THREADS_NUMBER = Runtime.getRuntime().availableProcessors() * 2 - 2;
        DB_WRITE_EXECUTOR = Executors.newFixedThreadPool(MagicDatabase.THREADS_NUMBER);
    }

    // Méthode pour obtenir l'instance unique de la base de données
    public static MagicDatabase getInstance(final Context ctx) {
        if (MagicDatabase.instance == null) {
            synchronized (MagicDatabase.class) {
                if (MagicDatabase.instance == null) {
                    MagicDatabase.instance = Room.databaseBuilder(ctx, MagicDatabase.class, "MagicDb")
                            .addCallback(MagicDatabase.onCreateCallback).build();
                }
            }
        }
        return MagicDatabase.instance;
    }

    // Callback pour les événements de création et d'ouverture de la base de données
    private static final RoomDatabase.Callback onCreateCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull final SupportSQLiteDatabase db) {
            super.onOpen(db);
        }

        @Override
        public void onCreate(@NonNull final SupportSQLiteDatabase db) {
            super.onCreate(db);
            MagicDatabase.DB_WRITE_EXECUTOR.execute(() -> {
                MagicDatabase.insertReserve();
                MagicDatabase.insertFakeBlueDeck();
            });
        }
    };

    // Méthode pour insérer des données de réserve dans la base de données
    private static void insertReserve() {
        final DecksDao decksDao = MagicDatabase.instance.getDecksDao();
        final CardsDao cardsDao = MagicDatabase.instance.getCardsDao();
        final Deck reserve = new Deck("Reserve");
        reserve.setReserve(true);
        final long deckId = decksDao.insert(reserve);
        reserve.setId(deckId);
        Land l = new Land("Montagne", Color.RED);
        reserve.addCard(l);
        cardsDao.insert(l);
        l = new Land("Marais", Color.BLACK);
        reserve.addCard(l);
        cardsDao.insert(l);
        l = new Land("Forêt", Color.GREEN);
        reserve.addCard(l);
        cardsDao.insert(l);
        final Creature c = new Creature("Durial, seigneur des brumes", Color.BLUE, 4, 4);
        c.addCharacteristic(CreatureCharacteristic.FLY);
        final Cost c1 = new Cost(3, Color.COLORLESS);
        final Cost c2 = new Cost(2, Color.BLUE);
        c.addCost(c1);
        c.addCost(c2);
        final Cost c3 = new Cost(3, Color.COLORLESS);
        final Effect e = new Effect(c3, "Renvoyez Durial dans la main de son propriétaire");
        c.addEffect(e);
        reserve.addCard(c);
        cardsDao.insert(c);
        decksDao.updateDeck(reserve);
    }

    // Méthode pour insérer un faux deck bleu dans la base de données
    private static void insertFakeBlueDeck() {
        final DecksDao decksDao = MagicDatabase.instance.getDecksDao();
        final CardsDao cardsDao = MagicDatabase.instance.getCardsDao();
        final Deck deck = new Deck("Bleu illusion");
        deck.setReserve(false);
        final long deckId = decksDao.insert(deck);
        deck.setId(deckId);
        Land l = new Land("Ile", Color.BLUE);
        deck.addCard(l);
        cardsDao.insert(l);
        l = new Land("Ile", Color.BLUE);
        deck.addCard(l);
        cardsDao.insert(l);
        l = new Land("Ile", Color.BLUE);
        deck.addCard(l);
        cardsDao.insert(l);
        final Creature c = new Creature("Durial, seigneur des brumes", Color.BLUE, 4, 4);
        c.addCharacteristic(CreatureCharacteristic.FLY);
        final Cost c1 = new Cost(3, Color.COLORLESS);
        final Cost c2 = new Cost(2, Color.BLUE);
        c.addCost(c1);
        c.addCost(c2);
        final Cost c3 = new Cost(3, Color.COLORLESS);
        final Effect e = new Effect(c3, "Renvoyez Durial dans la main de son propriétaire");
        c.addEffect(e);
        deck.addCard(c);
        cardsDao.insert(c);
        decksDao.updateDeck(deck);
    }
}