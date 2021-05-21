package com.example.meetup.Interests;

import java.util.ArrayList;
import java.util.List;

public class Interests {
    private List<String> interests = new ArrayList<>();
    public String CRICKET = "Cricket";
    public String CHESS = "Chess";
    public String FOOTBALL = "Football";
    public String PUBG = "PUBG";
    public String FREEFIRE = "FreeFire";
    public String READ = "Reading Books";
    public String SLEEP = "Sleeping";
    public String TRAVEL = "Travelling";
    public String MUSIC = "Music";
    public String ART = "Art";


    public  List<String> getInterests() {
        interests.add(CRICKET);
        interests.add(CHESS);
        interests.add(FOOTBALL);
        interests.add(PUBG);
        interests.add(FREEFIRE);
        interests.add(READ);
        interests.add(SLEEP);
        interests.add(TRAVEL);
        interests.add(MUSIC);
        interests.add(ART);
        return interests;
    }
}
