package as;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.WritableStringValue;

class R implements Runnable {

    private Plane plane;
    private PixelPainter painter;
    private CancelSupport cancelSupport;
    WritableStringValue millis;
    private int part;

    public R(PixelPainter painter, Plane plane, CancelSupport cancelSupport) {
        this.plane = plane;
        this.painter = painter;
        this.cancelSupport = cancelSupport;
        this.millis = new SimpleStringProperty();
        this.millis.set("...");
    }

    public R(PixelPainter painter, Plane plane, CancelSupport cancelSupport, int part) {
        this.plane = plane;
        this.painter = painter;
        this.cancelSupport = cancelSupport;
        this.millis = new SimpleStringProperty();
        this.millis.set("...");
    }

    @Override
    public void run() {
        double start = System.currentTimeMillis();
        // Replace the following line with Mandelbrot.computeParallel(...)
        // Mandelbrot.computeSequential(painter, plane, cancelSupport);
        Mandelbrot.computeParallel(painter, plane, cancelSupport, part);
        double end = System.currentTimeMillis();
        Platform.runLater(() -> millis.set((end - start) + "ms"));
    }
}
