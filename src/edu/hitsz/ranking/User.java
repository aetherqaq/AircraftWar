package edu.hitsz.ranking;

public class User implements Comparable{
    private int score;
    private String userName;
    private String time;

    public User(int score, String userName, String time){
        this.score = score;
        this.userName = userName;
        this.time = time;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

    @Override
    public int compareTo(Object compareuser) {
        int compareScore=((User)compareuser).getScore();
        /* 正序排列 */
        return compareScore-this.score;
    }
}
