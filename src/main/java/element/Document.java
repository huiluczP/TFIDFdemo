package element;

import java.util.ArrayList;

public class Document {
    private String content;
    private ArrayList<String> words;//分词
    private ArrayList<String> uniquewords;//非重复分词
    private int[] wordNum;//词出现次数,非重复

    public Document(){}
    public Document(String content){
        this.content=content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public ArrayList<String> getUniquewords() {
        return uniquewords;
    }

    public void setUniquewords(ArrayList<String> uniquewords) {
        this.uniquewords = uniquewords;
    }

    public int[] getWordNum() {
        return wordNum;
    }

    public void setWordNum(int[] wordNum) {
        this.wordNum = wordNum;
    }
}
