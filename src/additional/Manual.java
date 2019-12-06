package additional;

public class Manual extends Book{
    private String device;

    public Manual() {
        super();
        device = "-none-";
    }

    public Manual(String title, int numberOfPages, String author, String device) throws WrongDeviceException{
        super(title, numberOfPages, author);
        if(device.length() < 3) throw new WrongDeviceException();
        this.device = device;
    }

    @Override
    public String toString(){
        return "Manual: " + device + ", " + super.toString();
    }

}
