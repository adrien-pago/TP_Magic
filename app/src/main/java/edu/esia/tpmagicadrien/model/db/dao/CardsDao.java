package edu.esia.tpmagicadrien.model.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.esia.tpmagicadrien.model.Artefact;
import edu.esia.tpmagicadrien.model.Creature;
import edu.esia.tpmagicadrien.model.Interruption;
import edu.esia.tpmagicadrien.model.Land;
import edu.esia.tpmagicadrien.model.Ritual;

@Dao
public interface CardsDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Land l);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Creature c);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Artefact l);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Ritual r);

    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(Interruption i);

    @Query("SELECT * FROM Land WHERE DeckId=:id")
    LiveData<List<Land>> getLandsByDeckId(long id);

    @Query("SELECT * FROM Creature WHERE DeckId=:id")
    LiveData<List<Creature>> getCreaturesByDeckId(long id);

    @Query("SELECT * FROM Artefact WHERE DeckId=:id")
    LiveData<List<Artefact>> getArtefactByDeckId(long id);

    @Query("SELECT * FROM Ritual WHERE DeckId=:id")
    LiveData<List<Ritual>> getRitualsByDeckId(long id);

    @Query("SELECT * FROM Interruption WHERE DeckId=:id")
    LiveData<List<Interruption>> getInterruptionsByDeckId(long id);

    @Update
    void update(Land card);

    @Update
    void update(Creature card);

    @Update
    void update(Artefact card);

    @Update
    void update(Ritual card);

    @Update
    void update(Interruption card);

    @Delete
    void delete(Land card);

    @Delete
    void delete(Creature card);

    @Delete
    void delete(Artefact card);

    @Delete
    void delete(Ritual card);

    @Delete
    void delete(Interruption card);
}

