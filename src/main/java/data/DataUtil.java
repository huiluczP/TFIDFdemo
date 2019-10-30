package data;

import element.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * 文档操作
 * 分词
 */
public class DataUtil {
    private DocumentDao dao;
    private ArrayList<Document> docs;

    public DataUtil(){
        dao=new DocumentDao();
        dao.initCon();
    }

    public ArrayList<Document> getDocs(){
        return docs;
    }

    //生成对应document对象
    public void initDoc(){
        ArrayList<String> contents=dao.getWholeDoc();
        if(contents==null){
            docs=null;
            System.out.println("没有文档数据");
        }else{
            docs=new ArrayList<Document>();
            for(int i=0;i<contents.size();i++){
                Document doc=new Document(contents.get(i));
                docs.add(doc);
            }
        }
    }

    boolean isPunctuation(char c){
        if(c=='.'||c==','||c=='?'||c==':'||c=='!'||c=='@')
            return true;
        return false;
    }

    //分词
    public void divideWords(){
        for(Document doc:docs){
            ArrayList<String> words=new ArrayList<String>();

            String content=doc.getContent();

            char[] chars=content.toCharArray();
            for(int i=0;i<chars.length;i++){
                char c=chars[i];
                //将标点符号转换为空格
                if(isPunctuation(c)){
                    chars[i]=' ';
                }
            }
            String newContent=String.copyValueOf(chars);
            newContent=newContent.toLowerCase();//不区分大小写

            //根据空格进行分词
            String [] arr =newContent.split("\\s+");
            for(String s:arr){
                words.add(s);
            }

            doc.setWords(words);
        }
    }

    public void printWords(){
        for(int i=0;i<docs.size();i++){
            System.out.print("doc "+i+": ");
            for(int j=0;j<docs.get(i).getWords().size();j++){
                System.out.print(docs.get(i).getWords().get(j).toString()+"|");
            }
            System.out.println();
        }
    }

    public static ArrayList<String> getStopWordList(){
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader("src/main/resources/StopWord"));
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                arrayList.add(str.trim());
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
