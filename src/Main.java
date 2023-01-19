import display.ConsoleMatrixDisplay;
import display.MatrixOutOfBoundsException;
import generator.ComputedGenerator;
import generator.ManualGenerator;
import utils.TinyLogger;

public class Main {

    public static void main(String... args) {

        TinyLogger.info("--- Java display.MatrixDisplay Display ---");
        try {
            // Manual
            new ConsoleMatrixDisplay(ManualGenerator.basic2x2()).print("Manual 2x2");
            new ConsoleMatrixDisplay(ManualGenerator.checkerboard()).print("Manual checkerboard");
            new ConsoleMatrixDisplay(ManualGenerator.squareCentered()).print("Manual Square");
            new ConsoleMatrixDisplay(ManualGenerator.triangle12()).print("Manual Triangle 12");
            new ConsoleMatrixDisplay(ManualGenerator.triangle13()).print("Manual Triangle 13");

            // Computed
            new ConsoleMatrixDisplay(ComputedGenerator.square(14, 12, 4)).print("Computed square 4");
            new ConsoleMatrixDisplay(ComputedGenerator.square(23, 17, 10)).print("Computed square 10");
            new ConsoleMatrixDisplay(ComputedGenerator.rectangle(18, 22, 5, 3)).print("Computed rectangle 5x3");

            new ConsoleMatrixDisplay(ManualGenerator.tinycircle()).print("Tiny circle 5");
            new ConsoleMatrixDisplay(ComputedGenerator.circle(68, 68, 64)).print("Computed Circle");
            new ConsoleMatrixDisplay(ComputedGenerator.checkerboard(32, 8)).print("computed checkerboard");
            new ConsoleMatrixDisplay(ComputedGenerator.funcXx(22,46)).print("computed f(x)");


        /*
        var ma = new display.MatrixDisplay(ManualGenerator.manualTriangleF1());
        ma.addFrame(ManualGenerator.manualTriangleF2());
        ma.printAll("Animated Triangle", 6);
         */

        } catch (MatrixOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            TinyLogger.error("It's not possible to get out of the matrix. Trying to draw outside of the matrix bounds - %s", e.getMessage());
            e.printStackTrace();
        }
    }
}