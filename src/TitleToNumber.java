import java.util.Hashtable;
import java.util.stream.*;

public class TitleToNumber {

    public int titleToNumber(String s)
    {
        if(s.length()==0){
            return 0;
        }
        if(s.length()==1){
            return Integer.valueOf(s.charAt(0))-64;
        }

        int total = Integer.valueOf(s.charAt(s.length()-1))-64;
        for(int a = s.length(); a>1; a--){
            int x = 26;
            for(int i = a;i >2; i--){
                x*=26;
            }
            total += x*(Integer.valueOf(s.charAt(s.length()-a))-64);
        }
        return total;
    }
    public boolean canConstruct(String ransomNote, String magazine) {
        Hashtable<Character, Integer> table = new Hashtable<>();
        for(char c : magazine.toCharArray()){
            if(table.containsKey(c)){
                table.replace(c,table.get(c)+1);
            }
            else{
                table.put(c,1);
            }

        }
        for(char c: ransomNote.toCharArray()){
            if(!table.containsKey(c)){
                return false;
            }
            else{
                table.replace(c,table.get(c)-1);
                if(table.get(c)==0){
                    table.remove(c);
                }
            }
        }
       return true;

    }

    public String[] findWords(String[] words) {
        return Stream.of(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")).toArray(String[]::new);

    }

    public static void main(String[] args){
        TitleToNumber t = new TitleToNumber();
        System.out.println(t.canConstruct("asd","aaassd"));

    }
}
