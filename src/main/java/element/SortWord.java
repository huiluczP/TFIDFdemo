package element;

//排序用
public class SortWord {
    public String word;
    public int sum;
    public double tf_idf;
    public SortWord(String word,int sum,double tf_idf){
        this.word=word;
        this.sum=sum;
        this.tf_idf=tf_idf;
    }
}
