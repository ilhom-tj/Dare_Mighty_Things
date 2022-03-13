
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class GraphicsPolygon extends Frame {
    private final ArrayList<String> binaries = new ArrayList<>();
    private int indexN1 = 0;
    private int indexN2 = 1;
    public List<String> words;

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
        setUpBuffers();
        this.repaint();
    }

    private void setUpBuffers(){
        ArrayList<StringBuffer> buffers = new ArrayList<>();
        binaries.clear();
        for (String word : words) {
            StringBuffer buffer = new StringBuffer();
            List<Letter> letters = Helper.getLetters(word);
            for (Letter letter : letters) {
                buffer.append(letter.getBinary()).append("000");
            }
            buffers.add(buffer);
        }
        for (StringBuffer buffer : buffers) {
            int length = buffer.length();
            buffer.append("1".repeat(Math.max(0, (80 - length))));
            binaries.add(buffer.toString());
        }
    }

    public GraphicsPolygon(List<String> array) {
        this.words = array;
        this.setBackground(new Color(0x3f51b5));
        setUpBuffers();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke(50));
        double cornerdifferents = 4.5;
        double corner1 = 13.5;
        double corner2 = 18;
        double PREV_BOTTOM_1 = 30;
        double PREV_BOTTOM_2 = 30;
        int SCALE_FACTOR = 6;
        for (int c = 0; c < binaries.size(); c++) {
            double SIZE_CR1 = (130-binaries.size()*SCALE_FACTOR) * (1 + (c * 0.5));
            double SIZE_CR2 = (130-binaries.size()*SCALE_FACTOR) * (1 + (c * 0.5));
            double CORNER_SIZE1 = 0;
            double CORNER_SIZE2 = 4.5;
            double[] ax00 = {
                    cordinates(CORNER_SIZE1, SIZE_CR1, "cos"),
                    cordinates(CORNER_SIZE2, SIZE_CR2, "cos"),
                    cordinates(CORNER_SIZE2, PREV_BOTTOM_2, "cos"),
                    cordinates(CORNER_SIZE1, PREV_BOTTOM_1, "cos")
            };
            double[] ay00 = {
                    cordinates(CORNER_SIZE1, SIZE_CR1, "sin"),
                    cordinates(CORNER_SIZE2, SIZE_CR1, "sin"),
                    cordinates(CORNER_SIZE2, PREV_BOTTOM_2, "sin"),
                    cordinates(CORNER_SIZE1, PREV_BOTTOM_1, "sin")};
            double[] ax01 = {
                    cordinates(9, SIZE_CR1, "cos"),
                    ax00[1], ax00[2],
                    cordinates(9, PREV_BOTTOM_1, "cos")};
            double[] ay01 = {
                    cordinates(9, SIZE_CR1, "sin"),
                    ay00[1], ay00[2],
                    cordinates(9, PREV_BOTTOM_1, "sin")};

            for (int n = 0; n < binaries.get(c).length() / 2; n++) {
                Path2D polygon_00 = new Path2D.Double(),
                        polygon_01 = new Path2D.Double();
                polygon_00.moveTo(ax00[0], ay00[0]);
                polygon_01.moveTo(ax01[0], ay01[0]);
                for (int i = 0; i < ax00.length; ++i) {
                    polygon_00.lineTo(ax00[i], ay00[i]);
                    polygon_01.lineTo(ax01[i], ay01[i]);
                }
                g2D.setStroke(new BasicStroke(3));
                if ((int) this.binaries.get(c).charAt(indexN1) == 48) {
                    draw(g2D, new Color(0xe91e63), polygon_00);
                } else {
                    draw(g2D, Color.white, polygon_00);
                }
                if ((int) this.binaries.get(c).charAt(this.indexN2) == 48) {
                    draw(g2D, new Color(0xe91e63), polygon_01);
                } else {
                    draw(g2D, Color.white, polygon_01);
                }

                ax00[0] = ax01[0];
                ax00[1] = cordinates(corner1, SIZE_CR2, "cos");
                ax00[2] = cordinates(corner1, PREV_BOTTOM_2, "cos");
                ax00[3] = ax01[3];
                ay00[0] = ay01[0];
                ay00[1] = cordinates(corner1, SIZE_CR2, "sin");
                ay00[2] = cordinates(corner1, PREV_BOTTOM_2, "sin");
                ay00[3] = ay01[3];

                ax01[0] = cordinates(corner2, SIZE_CR1, "cos");
                ax01[1] = ax00[1];
                ax01[2] = ax00[2];
                ax01[3] = cordinates(corner2, PREV_BOTTOM_1, "cos");
                ay01[0] = cordinates(corner2, SIZE_CR1, "sin");
                ay01[1] = ay00[1];
                ay01[2] = ay00[2];
                ay01[3] = cordinates(corner2, PREV_BOTTOM_1, "sin");


                corner1 += cornerdifferents * 2;
                corner2 += cornerdifferents * 2;

                indexN1 = indexN1 + 2;
                indexN2 = indexN2 + 2;

            }
            PREV_BOTTOM_1 = SIZE_CR1;
            PREV_BOTTOM_2 = SIZE_CR2;
            indexN1 = 0;
            indexN2 = 1;
        }

    }

    private void draw(Graphics2D g2D, Color color1, Path2D polygon) {
        g2D.setColor(new Color(0x212121));
        g2D.draw(polygon);
        g2D.setColor(color1);
        g2D.fill(polygon);
    }

    private double cordinates(double corner, double radius, String cs) {
        double cordinate;
        if (cs.equals("sin")) {
            cordinate = (Toolkit.getDefaultToolkit().getScreenSize().height / 2)- (Math.sin(Math.toRadians(corner)) * radius);
        } else {
            cordinate = (Toolkit.getDefaultToolkit().getScreenSize().width / 2)-(Math.cos(Math.toRadians(corner)) * radius);
        }
        return cordinate;
    }
}