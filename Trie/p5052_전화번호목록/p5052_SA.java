import java.util.*;
import java.io.*;

public class Main {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int T;
    
    static int n;
    static class Node{
        Map<Character, Node> child = new HashMap<>();
        boolean endOfWord;
    } 
    static Node root;
    static List<String> strList;
    
    public static void main(String[] args) throws Exception{
        T = Integer.parseInt(br.readLine());
        boolean result = false;
        while(T-->0){
            input();
            for(String str : strList){
                result = insert(str);
                if(!result){
                    break;
                }
            }
            if(!result){
                sb.append("NO").append("\n");
                continue;
            }
            sb.append("YES").append("\n");
        }
        System.out.print(sb.toString());
    }
    
    static void input() throws Exception{
        n = Integer.parseInt(br.readLine());
        root = new Node();
        strList = new ArrayList<>();
        
        for(int i=0; i<n; ++i){
            strList.add(br.readLine());
        }
        Collections.sort(strList);
    }
    
    static boolean insert(String str){
        
        Node node = root;
        int len = str.length();
        for(int i=0; i<len; ++i){
            char curr = str.charAt(i);
            node = node.child.computeIfAbsent(curr, key->new Node());
            if(node.endOfWord){
                return false;
            }
        }
        node.endOfWord = true;
        return true;
    }
}

