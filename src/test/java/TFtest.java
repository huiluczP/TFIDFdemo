import data.DataUtil;
import element.Document;
import tf_idf.TF_IDFAdapter;

import java.util.ArrayList;

public class TFtest {
    public static void main(String []args){
        DataUtil util=new DataUtil();
        util.initDoc();
        util.divideWords();
        util.printWords();
        ArrayList<Document> docs=util.getDocs();

        //测试词频计算
        TF_IDFAdapter adapter=new TF_IDFAdapter(docs);
        adapter.uniqueWord_calTF();
        System.out.println("TF:");
        adapter.printTF();

        //测试idf计算
        adapter.calIDF();
        System.out.println("IDF:");
        adapter.printIDF();

        //测试tf*idf计算
        adapter.calTF_IDF();
        System.out.println("TF_IDF:");
        adapter.printTF_IDF();
    }
}
