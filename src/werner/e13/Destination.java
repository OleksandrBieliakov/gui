package werner.e13;

class Destination {

    private String place;
    private double price;

    Destination(String pl, double pr) {
        place = pl;
        price = pr;
    }

    public String getPlace() {
        return place;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return place;
    }
}
