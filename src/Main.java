import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        final String[] sentence = {"Dare Mighty Things"};
        List<String> argumentWords = Arrays.stream(sentence[0].split(" ")).toList();

        GraphicsPolygon graphicsPolygon = new GraphicsPolygon(argumentWords);
        graphicsPolygon.setVisible(true);
        graphicsPolygon.setLayout(new FlowLayout());

        TextField textField = new TextField("Dare Mighty Things                               ");
        textField.setSize(500,30);
        graphicsPolygon.add(textField);
        Button generate = new Button("Generate");
        generate.addActionListener(e -> {
            sentence[0] = textField.getText();
            graphicsPolygon.setWords(Arrays.stream(sentence[0].split(" ")).toList());
        });
        graphicsPolygon.add(generate);
        graphicsPolygon.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);

    }
}