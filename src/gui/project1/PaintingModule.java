package gui.project1;

import java.util.ArrayList;

class PaintingModule extends DrawingFrame {

    private ArrayList<Figure> figures = new ArrayList<>();

    PaintingModule() {
        super.setTitle("Painting module");
        generateFigure();
    }

    private void generateFigure() {
        for(int i = 0; i < 100; ++i) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            figures.add(new Figure());
            System.out.println(i);
        }
    }

    @Override
    ArrayList<Figure> getFigures() {
        return figures;
    }

}
