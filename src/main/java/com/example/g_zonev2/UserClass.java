package com.example.g_zonev2;

public class UserClass {
    String name,email,password;
    int score,numOftaskCompleted,level;

    public UserClass() {
    }

    public UserClass(String name, String email, String password, int score, int numOftaskCompleted, int level) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.score = score;
        this.numOftaskCompleted = numOftaskCompleted;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumOftaskCompleted() {
        return numOftaskCompleted;
    }

    public void setNumOftaskCompleted(int numOftaskCompleted) {
        this.numOftaskCompleted = numOftaskCompleted;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String convertEmail(){
        return  email.replace(".",",");
    }
}
