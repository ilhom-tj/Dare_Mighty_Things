import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static List<Letter>getLetters(String word){
        ArrayList<Letter> letters = new ArrayList<>();
        char[] charsOfWord = word.toCharArray();
        for (int i = 0 ; i < charsOfWord.length;i++){
            letters.add(new Letter(charsOfWord[i]));
        }
        return letters;
    }
    public static java.awt.List prepareList(List<String> words){
        ArrayList<String> charcers = new ArrayList<>();
        for (String word : words) {
            List<Letter> letters = Helper.getLetters(word);
            for (Letter letter : letters) {
                charcers.add(letter.Litt + "\tBinary:\t" + letter.binary + "\tAlfa:\t" + letter.alf);
            }
        }
        java.awt.List list = new java.awt.List(charcers.size(),false);
        for (String info : charcers){
            list.add(info);
        }
        return list;
    }
}
