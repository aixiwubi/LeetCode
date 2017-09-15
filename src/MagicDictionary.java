import java.util.HashSet;
import java.util.Hashtable;

public class MagicDictionary {
    private class Node{
        private char id;
        private Hashtable<Character, Node> nexts;
        public Node(char id){
            this.id = id;
            this.nexts = new Hashtable<>();
        }

    }
    private Hashtable<Character,Node> dictionary;

    /** Initialize your data structure here. */
    public MagicDictionary() {
        this.dictionary = new Hashtable<>();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        this.dictionary = null;
        this.dictionary = new Hashtable<>();
        for(String s: dict){
            Hashtable<Character,Node> current = dictionary;
            for(char c: s.toCharArray()){
                if(!current.containsKey(c)){
                    Node node = new Node(c);
                    current.put(c,node);
                    current = node.nexts;
                }
                else{
                    current = current.get(c).nexts;
                }
            }
        }

    }
    private boolean getSub(String a, Hashtable<Character,Node> nodes){
        if(a.length()==1){
            return true;
        }
        if(nodes.containsKey(a.charAt(0))){
                return getSub(a.substring(1), nodes.get(a.charAt(0)).nexts);
        }
        return false;
    }
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        Hashtable<Character,Node> current = dictionary;
        int strike = 0;
        for(int i = 0; i< word.length();i++){
            if(!current.containsKey(word.charAt(i))){
                if(strike>1){
                    return false;
                }
                else{
                    strike += 1;
                    for(Node node: current.values()){
                        if(i+1<word.length() && getSub(word.substring(i+1),node.nexts)){
                            return true;
                        }
                    }
                }
            }
            else{
                current = current.get(word.charAt(i)).nexts;
            }
        }
        return false;
    }

    public static void main(String [] args ){
        MagicDictionary m = new MagicDictionary();
        m.buildDict(new String[]{"hello", "leetcode","hallo"});
        System.out.println(m.search("hello"));


    }
}
