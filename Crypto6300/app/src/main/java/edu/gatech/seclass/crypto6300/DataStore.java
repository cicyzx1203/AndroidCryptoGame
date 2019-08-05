package edu.gatech.seclass.crypto6300;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataStore {
    private static Application application = new Application();
    public static Application getApplication() {return application;}
    private static final String FILE = "data.ser";
    private static Context context;

    // Stored the username of the logged in player
    public static String loggedInPlayerUsername = "";

    public static String getLoggedInPlayerUsername() {
        return loggedInPlayerUsername;
    }

    public static void setLoggedInPlayerUsername(String loggedInPlayerUsername) {
        DataStore.loggedInPlayerUsername = loggedInPlayerUsername;
    }


    public static void setContext(Context context){
        DataStore.context = context;
    }

    public static void save(){
        try {
            FileOutputStream fos = context.openFileOutput(FILE, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(application);
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load(){
        try{
            FileInputStream fis = context.openFileInput(FILE);
            ObjectInputStream is = new ObjectInputStream(fis);
            application = (Application) is.readObject();
            is.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
