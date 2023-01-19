package generator;

import display.MatrixOutOfBoundsException;
import utils.TinyLogger;

import java.util.Arrays;
import java.util.stream.Stream;

public class ComputedGenerator {

    static final float HALF = 2F;

    public static float[][] checkerboard(int matrixWidth, int matrixHight) throws MatrixOutOfBoundsException {
        var matrix = new float[matrixHight][matrixWidth];

        for (int x = 0; x < matrixWidth; x++) {
            for (int y = 0; y < matrixHight; y++) {
                if (x % 2 == 0 && y % 2 == 0 || x % 2 != 0 && y % 2 != 0)
                    matrix[y][x] = PixelValue.ON;
                else
                    matrix[y][x] = PixelValue.OFF;
            }
        }

        return matrix;
    }

    public static float[][] square(int matrixWidth, int matrixHight, int sideSize) throws MatrixOutOfBoundsException {
        return rectangle(matrixWidth, matrixHight, sideSize, sideSize);
    }

    public static float[][] rectangle(int matrixWidth, int matrixHight, int sideWidthSize, int sideHightSize) throws MatrixOutOfBoundsException {
        var matrix = new float[matrixHight][matrixWidth];
        float centerX = matrixWidth / HALF;
        float centerY = matrixHight / HALF;

        var xLowerBound = centerX - (sideWidthSize / HALF);
        var xUpperBound = centerX + (sideWidthSize / HALF);
        var yLowerBound = centerY - (sideWidthSize / HALF);
        var yUpperBound = centerY + (sideWidthSize / HALF);

        TinyLogger.debug("w:%d h:%d sw:%d sh:%d cx:%f, cy:%f xlb:%f xup:%f ylb:%f yub:%f", matrixHight, matrixWidth, sideHightSize, sideWidthSize, centerX, centerY, xLowerBound, xUpperBound, yLowerBound, yUpperBound);

        for (int x = 0; x < matrixWidth; x++) {
            for (int y = 0; y < matrixHight; y++) {
                if (x >= xLowerBound && x <= xUpperBound
                        && y >= yLowerBound && y <= yUpperBound) {
                    if (x <= xLowerBound + 1 || x >= xUpperBound - 1
                            || y <= yLowerBound + 1 || y >= yUpperBound - 1)
                        matrix[y][x] = PixelValue.ON;
                    else
                        matrix[y][x] = PixelValue.MO;
                } else
                    matrix[y][x] = PixelValue.OFF;
            }
        }

        return matrix;
    }

    public static float[][] circle(int matrixWidth, int matrixHight, int diameter) {
        float radius = diameter / HALF;
        float centerX = matrixWidth / HALF;
        float centerY = matrixHight / HALF;
        var matrix = new float[matrixHight][matrixWidth];

        TinyLogger.debug("w:%d h:%d d:%d r:%f cx:%f, cy:%f", matrixHight, matrixWidth, diameter, radius, centerX, centerY);

        for (int x = 0; x < matrixWidth; x++) {
            for (int y = 0; y < matrixHight; y++) {
                double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));

                TinyLogger.debug("x:%d y:%d dist:%f px:%s", x, y, distance, (distance <= radius ? "#" : '-'));

                if (distance <= radius && distance >= radius - 1)
                    matrix[y][x] = PixelValue.ON;
                else if (distance < radius)
                    matrix[y][x] = PixelValue.MO;
                else
                    matrix[y][x] = PixelValue.OFF;
            }
        }

        return matrix;
    }

    public static float[][] funcXx(int matrixWidth, int matrixHight) throws MatrixOutOfBoundsException {
        var yRes = new int[matrixWidth];

        for (int x = 0; x < matrixWidth; x++) {
            yRes[x] = x * 2;
        }

        return funcX(matrixWidth, matrixHight, yRes);
    }

    public static void mm(String[] args) {

        String[] array = {"a", "b", "c", "d", "e"};

        //Arrays.stream
        Stream<String> stream1 = Arrays.stream(array);
        stream1.forEach(x -> System.out.println(x));

        //Stream.of
        Stream<String> stream2 = Stream.of(array);
        stream2.forEach(x -> System.out.println(x));
    }

    private static float[][] funcX(int matrixWidth, int matrixHight, int yRes[]) throws MatrixOutOfBoundsException {
        var matrix = new float[matrixHight][matrixWidth];

        for (int x = 0; x < matrixWidth; x++) {
            for (int y = 0; y < matrixHight; y++) {
                if (yRes[x] == y)
                    matrix[y][x] = PixelValue.ON;
                else
                    matrix[y][x] = PixelValue.OFF;
            }
        }

        return matrix;
    }
}