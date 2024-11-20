package edu.esia.tpmagicadrien.model.db.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.esia.tpmagicadrien.model.Deck;

@Dao
public interface DecksDao {

    @Insert()
    long insert(Deck c);

    @Query("SELECT * FROM DECK WHERE Id=:id")
    LiveData<Deck> getDeckById(int id);

    @Query("SELECT * FROM DECK")
    LiveData<List<Deck>> getAllDecks();

    @Update
    void updateDeck(Deck deck);

    @Delete
    void deleteDeck(Deck card);
}

