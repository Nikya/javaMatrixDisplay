package generator;

import display.MatrixOutOfBoundsException;

public class ComputedGenerator {

    static final float HALF = 2F;

    public static float[][] generateSquare(int matrixWidth, int matrixHight, int sideSize) throws MatrixOutOfBoundsException {
        return generateRectangle(matrixWidth, matrixHight, sideSize, sideSize);
    }

    public static float[][] generateRectangle(int matrixWidth, int matrixHight, int sideWidthSize, int sideHightSize) throws MatrixOutOfBoundsException {
        var matrix = new float[matrixHight][matrixWidth];
        float centerX = matrixWidth / HALF;
        float centerY = matrixHight / HALF;

        var xLowerBound = centerX - (sideWidthSize / HALF);
        var xUpperBound = centerX + (sideWidthSize / HALF);
        var yLowerBound = centerY - (sideWidthSize / HALF);
        var yUpperBound = centerY + (sideWidthSize / HALF);

        System.out.println(String.format("w:%d h:%d sw:%d sh:%d cx:%f, cy:%f xlb:%f xup:%f ylb:%f yub:%f", matrixHight, matrixWidth, sideHightSize, sideWidthSize, centerX, centerY, xLowerBound, xUpperBound, yLowerBound, yUpperBound));

        for (int x = 0; x < matrixWidth; x++) {
            for (int y = 0; y < matrixHight; y++) {
                if (x >= xLowerBound && x <= xUpperBound
                        && y >= yLowerBound && y <= yUpperBound) {
                    if (x <= xLowerBound + 1 || x >= xLowerBound - 1)
                        matrix[y][x] = PixelValue.ON;
                    else
                        matrix[y][x] = PixelValue.MO;
                } else
                    matrix[y][x] = PixelValue.OFF;
            }
        }
        return matrix;
    }

    public static float[][] generateCircle(int matrixWidth, int matrixHight, int diameter) {
        float radius = diameter / HALF;
        float centerX = matrixWidth / HALF;
        float centerY = matrixHight / HALF;
        var matrix = new float[matrixHight][matrixWidth];

        // System.out.println(String.format("w:%d h:%d d:%d r:%f cx:%f, cy:%f", matrixHight, matrixWidth, diameter, radius, centerX, centerY));

        for (int x = 0; x < matrixWidth; x++) {
            for (int y = 0; y < matrixHight; y++) {
                double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));

                // System.out.println(String.format("x:%d y:%d dist:%f px:%s", x, y, distance, (distance <= radius ? "#" : '-')));

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

}