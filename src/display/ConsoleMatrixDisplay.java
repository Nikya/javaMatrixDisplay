package display;

public class ConsoleMatrixDisplay {
    /**
     * ASCII code 176 = ░ ( Graphic character, low density dotted )
     */
    public static final char DOT_OFF = '░';

    /**
     * ASCII code 177 = ▒ ( Graphic character, medium density dotted )
     */
    public static final char DOT_MED = '▒';

    /**
     * ASCII code 178 = ▓ ( Graphic character, high density dotted )
     */
    public static final char DOT_ON = '▓';

    /* ASCII code 186 = ║ ( Box drawing character double vertical line ) */
    public static final char BOX_V = '║';

    /* ASCII code 187 = ╗ ( Box drawing character double line upper right corner ) */
    public static final char BOX_UR = '╗';

    /* ASCII code 188 = ╝ ( Box drawing character double line lower right corner ) */
    public static final char BOX_LR = '╝';

    /* ASCII code 200 = ╚ ( Box drawing character double line lower left corner ) */
    public static final char BOX_LL = '╚';

    /* ASCII code 201 = ╔ ( Box drawing character double line upper left corner ) */
    public static final char BOX_UL = '╔';

    /* ASCII code 205 = ═ ( Box drawing character double horizontal line ) */
    public static final char BOX_H = '═';

    /* ASCII code 174 = « ( Angle quotes, guillemets, right-pointing quotation mark ) */
    public static final char CAP_START = '«';

    /* ASCII code 175 = » ( Guillemets, angle quotes, left-pointing quotation marks ) */
    public static final char CAP_END = '»';

    private float[][][] contentFrames;

    private final int lenght;

    private final int high;

    StringBuffer frameLineUpper;

    StringBuffer frameLineLower ;

    /**
     * 2D content : Line, row
     */
    public ConsoleMatrixDisplay(float[][] contentDef) {
        contentFrames = new float[1][][];
        contentFrames[0] = contentDef;
        high = contentDef.length;
        lenght = contentDef[0].length;
    }

    public void addFrame(float[][] newContentFrame) {
        var contentFramesUpscale = new float[contentFrames.length+1][][];
        int i=0;
        for (; i<contentFrames.length; i++) {
            contentFramesUpscale[i] = contentFrames[i];
        }
        contentFramesUpscale[i] = newContentFrame;
        contentFrames = contentFramesUpscale;
    }

    public String bufferingContentFrame(int iFrame) {
        StringBuffer display = new StringBuffer((high + 2) * (lenght + 2));
        var contentFrame = contentFrames[iFrame];

        for (int l = 0; l < contentFrame.length; l++) {
            float[] line = contentFrame[l];
            display.append(BOX_V).append(' ');

            for (int r = 0; r < line.length; r++) {
                float dot = line[r];

                if (dot == 1)
                    display.append(DOT_ON).append(DOT_ON);
                else if (dot < 1 && dot > 0)
                    display.append(DOT_MED).append(DOT_MED);
                else
                    display.append(DOT_OFF).append(DOT_OFF);
            }
            display.append(' ').append(BOX_V).append('\n');
        }

        return display.toString();
    }

    private void bufferingFrameLine() {
        StringBuffer frameLine = new StringBuffer(lenght + 2);
        for (int i = 0; i < lenght * 2 + 2; i++)
            frameLine.append(BOX_H);

        frameLineUpper = new StringBuffer(frameLine).append(BOX_UL).reverse().append(BOX_UR);
        frameLineLower = new StringBuffer(frameLine).append(BOX_LL).reverse().append(BOX_LR);
    }

    public ConsoleMatrixDisplay print(String caption) {
        this.printAll(caption, 1);

        return this;
    }

    public void printAll(String caption, int loopCnt) {
        if (frameLineUpper == null || frameLineLower == null)
            bufferingFrameLine();

        var wide = contentFrames[0][0].length;
        var hight = contentFrames[0].length;

        for (int l = 0; l < loopCnt; l++) {
            for (int f = 0; f < contentFrames.length; f++) {
                StringBuffer fullBuffer = new StringBuffer();
                fullBuffer.append(frameLineUpper);
                fullBuffer.append(' ').append(CAP_START).append(' ').append(caption).append(' ').append(CAP_END);
                fullBuffer.append(' ').append("◘").append(wide).append("x").append(hight);
                fullBuffer.append(" #").append(l+1).append(".").append(f+1).append('\n');
                fullBuffer.append(bufferingContentFrame(f));
                fullBuffer.append(frameLineLower);

                /*
                try {
                    TinyLogger.debug("sleep...");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    TinyLogger.warn("Not sleep");
                }
                */

                System.out.println(fullBuffer.toString());
            }
        }
    }
}
