package werner.e13;

class Discount {

    private String title;
    private double percent;

    Discount(String t, double p) {
        title = t;
        percent = p;
    }

    public String getTitle() {
        return title;
    }

    public double getPercent() {
        return percent;
    }

    @Override
    public String toString() {
        return title;
    }

}
