import data.DataUtil;
import element.Document;
import tf_idf.TF_IDFAdapter;

import java.util.ArrayList;

public class DocTest {
    public static void main(String[] args){
        DataUtil util=new DataUtil();
        util.initDoc();
        util.divideWords();
        ArrayList<Document> docs=util.getDocs();

        //获取停止词列表
        ArrayList<String> stopwords= DataUtil.getStopWordList();

        //词频计算
        TF_IDFAdapter adapter=new TF_IDFAdapter(docs,stopwords);
        adapter.uniqueWord_calTF();
        adapter.calIDF();
        adapter.calTF_IDF();

        //关键词提取测试(带停止词)
        ArrayList<String> docStrings=adapter.getKeyWordString(10);
        for(String str:docStrings){
            System.out.println(str);
        }
    }
}
