
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class SearchTable<K extends Comparable<K>,V> implements OrderedMap<K, V> {

    private Entry<K,V> entries[];
    private int size;

    public SearchTable(int capacity) {
        entries = new Entry[capacity];
        size = 0;       
    }

    public boolean isEmpty() { return size == 0; }

    public boolean isFull() { return size == entries.length; }

    @Override public Entry<K, V> firstEntry() {
    	if(size==0) {
    		return null;
    	}
    	Entry<K,V> tmp = entries[0];
    	for(int i=0;i<size;i++) {
    		int cmp = tmp.key.compareTo(entries[i].key);
    		if(cmp>0) {
    			tmp = entries[i];
    		}
    	}
    	return tmp;
    }

    @Override public Entry<K, V> lastEntry() {
    	if(size==0) {
    		return null;
    	}
    	Entry<K,V> tmp = entries[0];
    	for(int i=0;i<size;i++) {
    		int cmp = tmp.key.compareTo(entries[i].key);
    		if(cmp<0) {
    			tmp = entries[i];
    		}
    	}
    	return tmp;
    }

    @Override public Entry<K, V> floorEntry(final K key) {
    	int clk = 0;// final key보다 큰 값이 있어서 그 값과 비교한 적이 있다면 1로 바뀜. key보다 작은걸로만 이루어져있을 경우 쭉 0.
    	int cmp = 0;//현재 키와 비교해야하는 키를 비교한 값.
    	Entry<K, V> rtn = entries[0];
    	for(int i=0;i<size;i++) {
    		K key_now = entries[i].key;
    		cmp = key_now.compareTo(key);
    		if(cmp == 0) {
    			clk = 1;
    			return entries[i];
    		}
    		else if(cmp < 0) {// 현재 키 < 비교하는 키(final key)
    			if(clk==0) {//final key보다 큰 값 중 처음으로 비교되는 값.
    				clk = 1;
    				rtn = entries[i];
    			}
    			else if(key_now.compareTo(rtn.key)>0) {//현재 키가 비교중인 키보다 작은 경우 (비교해야하는 키 <현재 키 <비교중인 키) 
    				rtn = entries[i];
    			}
    		}
    	}
    	if(clk == 0) {
    		return null;
    	}
    	else {
    		return rtn;
    	}
    }

    @Override public Entry<K, V> ceilingEntry(final K key) {
    	int clk = 0;// final key보다 작은 값이 있어서 그 값과 비교한 적이 있다면 1로 바뀜. key보다 큰걸로만 이루어져있을 경우 쭉 0.
    	int cmp = 0;//현재 키와 비교해야하는 키를 비교한 값.
    	Entry<K, V> rtn = entries[0];
    	for(int i=0;i<size;i++) {
    		K key_now = entries[i].key;
    		cmp = key_now.compareTo(key);
    		if(cmp == 0) {
    			clk = 1;
    			return entries[i];
    		}
    		else if(cmp > 0) {// 현재 키 > 비교하는 키(final key)
    			if(clk==0) {//final key보다 작은 값 중 처음으로 비교되는 값.
    				clk = 1;
    				rtn = entries[i];
    			}
    			else if(key_now.compareTo(rtn.key)<0) {//현재 키가 비교중인 키보다 큰 경우 (비교중인 키 <현재 키 <비교해야하는 키) 
    				rtn = entries[i];
    			}
    		}
    	}
    	if(clk == 0) {
    		return null;
    	}
    	else {
    		return rtn;
    	}
    	
    }

    @Override public Entry<K, V> lowerEntry(final K key) { 
    	int clk = 0;// final key보다 큰 값이 있어서 그 값과 비교한 적이 있다면 1로 바뀜. key보다 작은걸로만 이루어져있을 경우 쭉 0.
    	int cmp = 0;//현재 키와 비교해야하는 키를 비교한 값.
    	Entry<K, V> rtn = entries[0];
    	for(int i=0;i<size;i++) {
    		K key_now = entries[i].key;
    		cmp = key_now.compareTo(key);
    		if(cmp < 0) {// 현재 키 < 비교하는 키(final key)
    			if(clk==0) {//final key보다 큰 값 중 처음으로 비교되는 값.
    				clk = 1;
    				rtn = entries[i];
    			}
    			else if(key_now.compareTo(rtn.key)>0) {//현재 키가 비교중인 키보다 큰 경우 (비교중인 키 <현재 키 <비교해야하는 키) 
    				rtn = entries[i];
    			}
    		}
    	}
    	if(clk == 0) {
    		return null;
    	}
    	else {
    		return rtn;
    	}
    }

    @Override public Entry<K, V> higherEntry(final K key) {
    	int clk = 0;// final key보다 큰 값이 있어서 그 값과 비교한 적이 있다면 1로 바뀜. key보다 작은걸로만 이루어져있을 경우 쭉 0.
    	int cmp = 0;//현재 키와 비교해야하는 키를 비교한 값.
    	Entry<K, V> rtn = entries[0];
    	for(int i=0;i<size;i++) {
    		K key_now = entries[i].key;
    		cmp = key_now.compareTo(key);
    		if(cmp > 0) {// 현재 키 > 비교하는 키(final key)
    			if(clk==0) {//final key보다 큰 값 중 처음으로 비교되는 값.
    				clk = 1;
    				rtn = entries[i];
    			}
    			else if(key_now.compareTo(rtn.key)<0) {//현재 키가 비교중인 키보다 작은 경우 (비교해야하는 키 <현재 키 <비교중인 키) 
    				rtn = entries[i];
    			}
    		}
    	}
    	if(clk == 0) {
    		return null;
    	}
    	else {
    		return rtn;
    	}
    }

    @Override public Entry<K, V> get(final K key) {
    	int cmp = 0;//현재 키와 비교해야하는 키를 비교한 값.
    	for(int i=0;i<this.size;i++) {
    		K key_now = this.entries[i].key;
    		cmp = key_now.compareTo(key);
    		if(cmp == 0) {
    			return entries[i];
    		}
    	}
    	return null;
    }

    /**
     * Associates the given value with the given key, returning any overridden value.
     * @param key search key
     * @param value value of entry
     * @return old value associated with the key, if already exists, or null otherwise
     * @throws IllegalStateException if full
     */
    @Override public V put(final K key, final V value) {
    	if(this.get(key)!=null) {
    		this.get(key).value = value;
    		return value;
    	}
    	else if(this.isFull()) {
    		throw new IllegalStateException();
    	}
    	entries[size] = new Entry(key, value);
    	size ++;
    	return value;
    }

    @Override public V remove(final K key) {//키가 중복되면 어떡해? 다없애??음 일단 하나만 없애기로하자.
		V rtn = null;
    	if(this.get(key)!=null) {//키가 있으면.
    		Entry<K,V>[] new_entries = new Entry[entries.length];
    		int cnt = 0;
    		for(int i=0;i<size;i++) {
    			if(entries[i].key.compareTo(key)!=0) {
    				new_entries[cnt] = new Entry(entries[i].key,entries[i].value);
    				cnt ++;
    			}
    			else {
    				rtn = entries[i].value;
    			}
    		}
    		entries = new_entries;
    		size--;
    	}
    	return rtn;
    }

    @Override public int size() {
        return size;
    }

    @Override public Set<K> keys() {
        return Arrays.stream(entries).limit(size).map(e -> e.key).collect(Collectors.toSet());
    }

    @Override public Collection<V> values() {
        return Arrays.stream(entries).limit(size).map(e -> e.value).collect(Collectors.toList());
    }

    @Override public Collection<Entry<K, V>> entries() {
    	return Arrays.stream(entries).limit(size).map(e -> e).collect(Collectors.toList());
    }

    @Override public String toString() {
        return Arrays.toString(entries);
    }

    // You can define private fields and/or methods below, if necessary ...
}


