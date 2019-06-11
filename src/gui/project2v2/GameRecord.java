package gui.project2v2;

class GameRecord implements Comparable<GameRecord>{
    private String name;
    private int time;

    public GameRecord(String n, int t) {
        name = n;
        time = t;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    @Override
    public int compareTo(GameRecord other) {
        if(this.time < other.time) return -1;
        if(this.time > other.time) return 1;
        return 0;
     }

     @Override
    public String toString() {
        return name + "\n" + time + "\n";
     }

}

