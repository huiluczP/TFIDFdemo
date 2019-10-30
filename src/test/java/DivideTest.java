import data.DataUtil;
import data.DocumentDao;

public class DivideTest {
    public static void main(String[] args){
        //分词测试
        DataUtil util=new DataUtil();
        util.initDoc();
        util.divideWords();
        util.printWords();
    }
}
