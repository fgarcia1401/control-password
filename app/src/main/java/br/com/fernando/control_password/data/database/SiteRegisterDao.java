package br.com.fernando.control_password.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.fernando.control_password.model.SiteRegister;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface SiteRegisterDao {

    @Insert(onConflict = REPLACE)
    long insert(SiteRegister siteRegister);

    @Update
    int update(SiteRegister siteRegister);

    @Query("DELETE FROM SiteRegister WHERE :id = SiteRegister.id")
    void deleteById(int id);

    @Query("SELECT * FROM SiteRegister WHERE :emailAccount = SiteRegister.emailAccount")
    List<SiteRegister> findByEmail(String emailAccount);

}
