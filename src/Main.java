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


        BorderLayout topLayout = new BorderLayout();
        graphicsPolygon.setLayout(topLayout);

        TextField textField = new TextField("Dare Mighty Things");
        textField.setSize(500,30);
        graphicsPolygon.add(textField,BorderLayout.NORTH);
        Button generate = new Button("Generate");

        java.awt.List charcestList = Helper.prepareList(Arrays.stream(sentence[0].split(" ")).toList());
        charcestList.setPreferredSize(new Dimension(500,500));
        graphicsPolygon.add(charcestList,BorderLayout.WEST);

        generate.addActionListener(e -> {
            sentence[0] = textField.getText();
            graphicsPolygon.setWords(Arrays.stream(sentence[0].split(" ")).toList());
            charcestList.removeAll();
            java.awt.List newList = Helper.prepareList(Arrays.stream(sentence[0].split(" ")).toList());
            for (String item : newList.getItems()){
                charcestList.add(item);
            }
            charcestList.notify();
            charcestList.repaint();
        });
        graphicsPolygon.add(generate,BorderLayout.EAST);


        graphicsPolygon.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);

    }
}