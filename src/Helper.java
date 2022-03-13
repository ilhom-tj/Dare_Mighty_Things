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
}
