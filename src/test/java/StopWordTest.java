import data.DataUtil;

import java.util.ArrayList;

public class StopWordTest {
    public static void main(String []args){
        ArrayList<String> stopwords= DataUtil.getStopWordList();
        for(String str:stopwords){
            System.out.println(str);
        }
    }
}
