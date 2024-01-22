package main.model;

public class User {
    private int id;
    private String name;
    private int wins;
    private int losses;
    private int draws;

    public User(int id){
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public void increaseWins(){
        this.wins++;
    }

    public void increaseLoses(){
        this.losses++;
    }

    public void increaseDraws(){
        this.draws++;
    }

}
