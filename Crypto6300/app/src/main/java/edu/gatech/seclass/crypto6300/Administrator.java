package edu.gatech.seclass.crypto6300;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Administrator {

    private Application app;

    Administrator(Application app){
        this.app = app;
    }

    public List<Cryptogram> getStatistics(){
        ArrayList<Cryptogram> cryptograms = new ArrayList<>(app.getAllCryptogram());
        Collections.sort(cryptograms, new Desc());

        return cryptograms;
    }

    public void disableCryptogram(String title, int penalty){

        Cryptogram c = app.getCryptogram(title);
        c.setDisabled(true);
        c.getCreator().addPoints(-1* penalty);
    }

    // Getter and setter
    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }
}

class Desc implements Comparator<Cryptogram>
{
    // Used for sorting in ascending order of
    // roll number
    public int compare(Cryptogram a, Cryptogram b)
    {
        Date date1 = a.getCreationDate();
        Date date2 = b.getCreationDate();
        if(date1.equals(date2)){
            return  0;
        }
        return date2.after(date1) ? 1 : -1;
    }
}

