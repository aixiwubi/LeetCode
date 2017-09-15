import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class LFUCache {
    private class Cache implements Comparable<Cache>{
        private int key;
        private int value;
        private int frequency;
        private int createTime;
        public Cache(int key, int value, int createTime){
            this.key = key;
            this.value = value;
            this.frequency =0;
            this.createTime = createTime;
        }
        private void hit(){
            this.frequency+=1;
        }
        private void touched(int timer){
            this.createTime = timer;

        }
        @Override
        public int compareTo(Cache b){
            return this.frequency-b.frequency;
        }

        @Override
        public boolean equals(Object b){
            if(b==null){
                return false;
            }
            if(this == b){
                return true;
            }
            return false;
        }
        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + key;
            result = 31 * result + key;
            result = 31 * result + key;
            return result;
        }

    }
    private static int timer = 0;
    private int capacity;
    private Hashtable<Integer,Cache> table;
    private PriorityQueue<Cache> caches;
    public LFUCache(int cap){
        this.capacity = cap;
        this.table = new Hashtable<Integer, Cache>();
        this.caches = new PriorityQueue<Cache>(Cache::compareTo);
    }
    public int get(int key) {
        timer++;
        if(table.containsKey(key)){
            Cache cache = table.get(key);
            cache.hit();
            cache.touched(timer);
            caches.remove(cache);
            caches.add(cache);
            return cache.value;
        }
        return -1;
    }
    public void put(int key, int value) {
        timer++;
        if(this.capacity == 0){
            return;
        }
        if(table.size()<capacity){
            if(table.containsKey(key)){
                Cache cache = table.get(key);
                cache.value = value;
                cache.touched(timer);
            }
            else{
                Cache cache = new Cache(key,value,timer);
                table.put(key,cache);
                caches.add(cache);
            }
        }
        else{
            if(!table.containsKey(key)) {
                LinkedList<Cache> addBack = new LinkedList<Cache>();
                Cache current = caches.poll();
                addBack.add(current);
                Cache toDelete = current;
                while(caches.peek()!= null && current.frequency==caches.peek().frequency){
                    current = caches.poll();
                    if(current.createTime<toDelete.createTime){
                        toDelete = current;
                    }
                    addBack.add(current);
                }
                addBack.remove(toDelete);
                caches.addAll(addBack);
                table.remove(toDelete.key);
                current = new Cache(key,value,timer);
                caches.add(current);
                table.put(key,current);

            }
            else{
                Cache cache = table.get(key);
                cache.value = value;
                cache.touched(timer);
            }
        }
    }
    public static void main(String [] args){
        LFUCache cache = new LFUCache(4);
        cache.put(2,1);
        cache.put(1,1);

        cache.put(2,3);

        cache.put(4,1);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        cache.put(5,3);

        cache.put(3,1);
        System.out.println(cache.get(2));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
        System.out.println(cache.get(1));


    }
}
