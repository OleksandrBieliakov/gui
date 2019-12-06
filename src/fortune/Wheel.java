package fortune;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

class Wheel extends Group {

    private static final int
            PRISE_STEP = 10,
            SECTORS = 10;
    private static final double
            RADIUS = 300,
            POINTER_RADIUS = 5,
            X0 = RADIUS,
            Y0 = RADIUS + 3 * POINTER_RADIUS,
            PERIOD = 5,  // in seconds
            OMEGA = 2 * Math.PI / PERIOD,
            PRISE_RADIUS = 0.8 * RADIUS,
            POSITIONER_STEP = Math.PI * 2 / SECTORS,
            TEXT_POSITIONER = (PERIOD / SECTORS) / 2,
            SECTOR_TIME = PERIOD / SECTORS;

    private Timeline anim;
    private DoubleProperty time;
    private Game game;

    Wheel(Game game) {

        this.game = game;

        Circle pointer = new Circle(X0, POINTER_RADIUS, POINTER_RADIUS, Color.RED);
        pointer.setStroke(Color.BLACK);
        pointer.setStrokeWidth(1.5);
        Circle circle = new Circle(X0, Y0, RADIUS, Color.LIGHTBLUE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(3);
        getChildren().addAll(pointer, circle);

        time = new SimpleDoubleProperty();

        for (int sect = 0, prise = PRISE_STEP; sect < SECTORS; ++sect, prise += PRISE_STEP) {
            Line line = new Line();
            line.setStrokeWidth(3);
            line.setStroke(Color.BLACK);
            Text text = new Text(prise + "");
            text.setFont(new Font(30));

            line.setStartX(X0);
            line.setStartY(Y0);

            double positioner = POSITIONER_STEP * sect - Math.PI / 2 - POSITIONER_STEP / 2;

            DoubleBinding xLineBind = new DoubleBinding() {
                {
                    super.bind(time);
                }

                @Override
                protected double computeValue() {
                    return X0 + RADIUS * Math.cos(OMEGA * time.get() + positioner);
                }
            };
            DoubleBinding yLineBind = new DoubleBinding() {
                {
                    super.bind(time);
                }

                @Override
                protected double computeValue() {
                    return Y0 + RADIUS * Math.sin(OMEGA * time.get() + positioner);
                }
            };

            DoubleBinding xTextBind = new DoubleBinding() {
                {
                    super.bind(time);
                }

                @Override
                protected double computeValue() {
                    return X0 + PRISE_RADIUS * Math.cos(OMEGA * (time.get() + TEXT_POSITIONER) + positioner);
                }
            };
            DoubleBinding yTextBind = new DoubleBinding() {
                {
                    super.bind(time);
                }

                @Override
                protected double computeValue() {
                    return Y0 + PRISE_RADIUS * Math.sin(OMEGA * (time.get() + TEXT_POSITIONER) + positioner);
                }
            };
            DoubleBinding rotateBind = new DoubleBinding() {
                {
                    super.bind(time);
                }

                @Override
                protected double computeValue() {
                    return Math.toDegrees(OMEGA * (time.get() + TEXT_POSITIONER) + positioner) + 90;
                }
            };

            line.endXProperty().bind(xLineBind);
            line.endYProperty().bind(yLineBind);

            text.xProperty().bind(xTextBind);
            text.yProperty().bind(yTextBind);
            text.rotateProperty().bind(rotateBind);

            getChildren().addAll(text, line);
        }

        anim = new Timeline(new KeyFrame(Duration.seconds(PERIOD), new KeyValue(time, PERIOD)));
        anim.setCycleCount(Animation.INDEFINITE);

    }

    void roll() {
        anim.play();
        Task task = new Task<Void>() {
            @Override
            protected Void call() {
                int sectorsToGo = 5 + (int) (Math.random() * 6);
                double timeToGo = sectorsToGo * SECTOR_TIME;
                try {
                    Thread.sleep((long) (timeToGo * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                anim.pause();
                game.newGuess(findPrise());
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }

    private int findPrise() {
        for (Object o : getChildren()) {
            if (o.getClass().equals(Text.class)) {
                Text text = (Text) o;
                int diffX = (int) Math.abs(text.xProperty().get() - X0);
                int diffY = (int) Math.abs(text.yProperty().get() - (Y0 - PRISE_RADIUS));
                if (diffX < 20 && diffY < 20) {
                    return Integer.parseInt(text.getText());
                }
            }
        }
        return -1;
    }

}
