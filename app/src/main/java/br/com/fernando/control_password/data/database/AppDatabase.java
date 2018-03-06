package br.com.fernando.control_password.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import br.com.fernando.control_password.model.SiteRegister;

@Database(entities = {SiteRegister.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            String DATA_BASE_RAD = "DATA_BASE_CONSUMER";
            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, DATA_BASE_RAD)
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract SiteRegisterDao siteRegisterDao();

}
