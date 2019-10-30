package tf_idf;

import element.Document;
import element.SortWord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TF_IDFAdapter {

    ArrayList<String> stopWords;//停止词列表
    ArrayList<Document> docs;
    double [][] tfs;//词频矩阵
    double [][] idfs;//逆向文件频率
    double [][] tf_idf;//计算词频-逆向文件频率

    public TF_IDFAdapter(ArrayList<Document> docs){
        this.docs=docs;
        this.stopWords=null;
    }

    public TF_IDFAdapter(ArrayList<Document> docs,ArrayList<String> stopWord){
        this.docs=docs;
        this.stopWords=stopWord;
    }

    //将基础分词转化为非重复，并计算词频信息
    public void uniqueWord_calTF(){
        tfs=new double[docs.size()][];
        for(int d=0;d<docs.size();d++){
            Document doc=docs.get(d);
            unique(doc);
            //计算词频信息
            double[] tf=new double[doc.getUniquewords().size()];
            for(int i=0;i<doc.getUniquewords().size();i++){
                tf[i]=(double)doc.getWordNum()[i]/(double)doc.getWords().size();
            }
            tfs[d]=tf;
        }
    }

    //单个文档计算非重复
    public void unique(Document d){
        ArrayList<String> words=d.getWords();
        ArrayList<String> uniq=new ArrayList<String>();
        int []sum=new int[words.size()];
        for(String word:words){//计算每个词出现次数
            if(uniq.contains(word)){
                sum[uniq.indexOf(word)]++;
            }else{
                uniq.add(word);
                sum[uniq.indexOf(word)]++;
            }
        }

        //设置
        d.setUniquewords(uniq);
        int []wordNum=new int[uniq.size()];
        for(int i=0;i<wordNum.length;i++){
            wordNum[i]=sum[i];
        }
        d.setWordNum(wordNum);
    }

    //计算idf 逆向文件频率
    public void calIDF(){
        idfs=new double[tfs.length][];
        int docSize=docs.size();
        for(int i=0;i<docs.size();i++){
            ArrayList<String> uniqueWord=docs.get(i).getUniquewords();
            double[] idf=new double[uniqueWord.size()];
            for(int j=0;j<uniqueWord.size();j++){
                String word=uniqueWord.get(j);
                int sum=0;
                for(Document doc:docs){
                    if(doc.getWords().contains(word)){
                        sum++;
                    }
                }
                //idf计算 ln(size/sum)
                double result=Math.log((double)docSize/(double)sum);
                idf[j]=result;
            }
            idfs[i]=idf;
        }
    }

    public void calTF_IDF(){
        tf_idf=new double[tfs.length][];
        for(int i=0;i<tfs.length;i++){
            double []temple=new double[tfs[i].length];
            for(int j=0;j<tfs[i].length;j++){
                temple[j]=tfs[i][j]*idfs[i][j];
            }
            tf_idf[i]=temple;
        }
    }

    //排序，对每个对应矩阵进行倒序
    //输出对应的关键词序列向量 多次使用并列
    //k 为保留的关键词个数
    public ArrayList<String> getKeyWordString(int k){
        ArrayList<String> docString=new ArrayList<String>();

        for(int i=0;i<docs.size();i++){
            Document temple=docs.get(i);
            double[] resultTemple=tf_idf[i];//以计算结果为基准

            ArrayList<SortWord> sortArray=new ArrayList<SortWord>();
            for(int j=0;j<temple.getUniquewords().size();j++){
                sortArray.add(new SortWord(temple.getUniquewords().get(j),temple.getWordNum()[j],resultTemple[j]));
            }

            Collections.sort(
                    sortArray, new Comparator<SortWord>() {
                        public int compare(SortWord o1, SortWord o2) {
                            if(o1.tf_idf<o2.tf_idf)
                                return 1;
                            else if(o1.tf_idf==o2.tf_idf)
                                return 0;
                            else if(o1.tf_idf>o2.tf_idf)
                                return -1;
                            return 0;
                        }
                    }
            );

            StringBuffer sb=new StringBuffer();
            int round=0;
            for(SortWord word:sortArray){
                if(isStopWord(word.word))
                    continue;

                round++;
                for(int t=0;t<word.sum;t++){
                    sb.append(word.word+" ");
                }
                if(round>=k){
                    break;
                }
            }

            docString.add(sb.toString());
        }

        return docString;
    }

    boolean isStopWord(String word){
        if(stopWords==null)
            return false;

        if(stopWords.contains(word))
            return true;
        else
            return false;
    }

    //打印词频矩阵
    public void printTF(){
        for(int i=0;i<tfs.length;i++){
            for(int j=0;j<tfs[i].length;j++){
                System.out.print(tfs[i][j]+"|");
            }
            System.out.println();
        }
    }

    //打印逆向文件概率矩阵
    public void printIDF(){
        for(int i=0;i<idfs.length;i++){
            for(int j=0;j<idfs[i].length;j++){
                System.out.print(idfs[i][j]+"|");
            }
            System.out.println();
        }
    }

    //打印总矩阵
    public void printTF_IDF(){
        for(int i=0;i<tf_idf.length;i++){
            for(int j=0;j<tf_idf[i].length;j++){
                System.out.print(tf_idf[i][j]+"|");
            }
            System.out.println();
        }
    }
}
