package entity;

import java.util.ArrayList;
import java.util.List;

public class Taxi {
    List<String> marka=new ArrayList<String>();
    List<String> model=new ArrayList<>();
    List<String> time=new ArrayList<String>();
    public int ida = 0 ;
    public ArrayList<Auto> listAutos;
    public int last_ID = 0;

    public void createList() {

        marka.add("Audi");
        marka.add("Lada ");
        marka.add("Lada ");
        marka.add("Geely");
        marka.add("Chery");
        marka.add("BMW");
        marka.add("Haval");
        model.add("Q6");
        model.add("Granta");
        model.add("Niva");
        model.add("Coolray");
        model.add("Tiggo 7 PRO");
        model.add("M3");
        model.add("Jolion ");
        time.add("5.36");
        time.add("2.12");
        time.add("810");
        time.add("11.45");
        time.add("9.37");
        time.add("3.21");
        time.add("2.42");

        if (listAutos == null) {
            listAutos = new ArrayList<Auto>();
            for (int i = 0; i < marka.size(); i++) {
                add(ida++, marka.get(i),model.get(i),time.get(i));
            }
        }
    }

    public Taxi() {

    }

    public void Add(String marka, String model, String time) {
        int id = maxId() + 1;
        add(id, marka, model, time);
    }

    public void Remove(int argId) {
        int index = findId(argId);
        if (index >= 0) {
            listAutos.remove(index);
        }
    }

    public void Save(int argId, String marka, String model, String time) {
        int index = findId(argId);
        if (index >= 0) {
            listAutos.get(index).Marka = marka;
            listAutos.get(index).Model = model;
            listAutos.get(index).Time = time;
        }
    }

    private void add(int argId, String marka, String model, String time) {
        Auto auto = new Auto();
        auto.Id = argId;
        auto.Marka = marka;
        auto.Model = model;
        auto.Time= time;
        listAutos.add(auto);
        if (last_ID < argId) {
            last_ID = argId;
        }
    }

    public int findId(int argId) {
        int index = -1;
        for (int i = 0; i < listAutos.size(); i++) {
            if (argId == listAutos.get(i).Id) {
                index = i;
            }
        }
        return index;
    }

    private int maxId() {
        last_ID += 0.5;
        return last_ID;
    }

}
