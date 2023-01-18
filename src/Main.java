import display.ConsoleMatrixDisplay;
import display.MatrixOutOfBoundsException;
import generator.ComputedGenerator;
import generator.ManualGenerator;

public class Main {

    public static void main(String... args) {

        System.out.println("--- Java display.MatrixDisplay Display ---");
        try {
            // Manual
            new ConsoleMatrixDisplay(ManualGenerator.basic2x2()).print("Manual 2x2");
            new ConsoleMatrixDisplay(ManualGenerator.checkerboardl()).print("Manual Checkerboardl");
            new ConsoleMatrixDisplay(ManualGenerator.squareCentered()).print("Manual Square");
            new ConsoleMatrixDisplay(ManualGenerator.triangle12()).print("Manual Triangle 12");
            new ConsoleMatrixDisplay(ManualGenerator.triangle13()).print("Manual Triangle 13");

            // Computed
            new ConsoleMatrixDisplay(ComputedGenerator.generateSquare(14, 12, 4)).print("Computed square 4");
            new ConsoleMatrixDisplay(ComputedGenerator.generateSquare(23, 17, 10)).print("Computed square 10");
            new ConsoleMatrixDisplay(ComputedGenerator.generateRectangle(18, 22, 5, 3)).print("Computed rectangle 5x3");

            new ConsoleMatrixDisplay(ManualGenerator.tinycircle()).print("Tiny circle 5");
            new ConsoleMatrixDisplay(ComputedGenerator.generateCircle(32, 32, 23)).print("Computed Circle 5");

        /*
        var ma = new display.MatrixDisplay(ManualGenerator.manualTriangleF1());
        ma.addFrame(ManualGenerator.manualTriangleF2());
        ma.printAll("Animated Triangle", 6);
         */

        } catch (MatrixOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.err.println("It's not possible to get out of the matrix. Trying to draw outside of the matrix bounds - " + e.getMessage());
            e.printStackTrace();
        }
    }
}