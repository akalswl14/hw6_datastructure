import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Link-based implementation of binary search tree
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class LinkedBinarySearchTree<K extends Comparable<K>, V> extends LinkedBinaryTree<Entry<K, V>>
		implements OrderedMap<K, V> {

	@Override
	public Set<K> keys() {
		Set<K> snapshot = new HashSet<>();
		for (Position<Entry<K, V>> p : this.positions()) {
			Node<Entry<K, V>> node = (Node<Entry<K, V>>) p;
			snapshot.add(node.getElement().key);
		}
		return snapshot;
	}

	@Override
	public Collection<V> values() {
		List<V> snapshot = new ArrayList<>();
		for (Position<Entry<K, V>> p : this.positions()) {
			Node<Entry<K, V>> node = (Node<Entry<K, V>>) p;
			snapshot.add(node.getElement().value);
		}
		return snapshot;
	}

	@Override
	public Collection<Entry<K, V>> entries() {
		List<Entry<K, V>> snapshot = new ArrayList<>();
		for (Position<Entry<K, V>> p : this.positions()) {
			Node<Entry<K, V>> node = (Node<Entry<K, V>>) p;
			snapshot.add(node.getElement());
		}
		return snapshot;
	}

	@Override
	public Entry<K, V> firstEntry() {
		Entry<K, V> rtn = new Entry<K, V>();
		K tmp = null;
		if (this.isEmpty()) {
			return null;
		}
		for (Position<Entry<K, V>> p : this.positions()) {
			Node<Entry<K, V>> node = (Node<Entry<K, V>>) p;
			K now = node.getElement().key;
			if (tmp == null) {
				tmp = now;
				rtn = node.getElement();
			} else if (now.compareTo(tmp) < 0) {
				tmp = now;
				rtn = node.getElement();
			}
		}
		return rtn;
	}

	@Override
	public Entry<K, V> lastEntry() {
		Entry<K, V> rtn = new Entry<K, V>();
		K tmp = null;
		if (this.isEmpty()) {
			return null;
		}
		for (Position<Entry<K, V>> p : this.positions()) {
			Node<Entry<K, V>> node = (Node<Entry<K, V>>) p;
			K now = node.getElement().key;
			if (tmp == null) {
				tmp = now;
				rtn = node.getElement();
			} else if (now.compareTo(tmp) > 0) {
				tmp = now;
				rtn = node.getElement();
			}
		}
		return rtn;
	}

	@Override
	public Entry<K, V> floorEntry(K key) {// key값보다 큰것 중 가장 작은 값.
		Entry<K, V> rtn = new Entry<K, V>();
		int clk = 0;
		K tmp = null;
		for (Position<Entry<K, V>> p : this.positions()) {
			Node<Entry<K, V>> node = (Node<Entry<K, V>>) p;
			K now = node.getElement().key;
			if (now.compareTo(key) == 0) {// key값을 가진 키가 있을 경우.
				return node.getElement();
			} else if (key.compareTo(now) > 0) {// key값보다 클 경우.
				if (tmp == null) {
					tmp = now;
					clk = 1;
					rtn = node.getElement();
				} else if (now.compareTo(tmp) > 0) {
					tmp = now;
					rtn = node.getElement();
				}
			}
		}
		if (clk == 0) {
			return null;
		} else {
			return rtn;
		}
	}

	@Override
	public Entry<K, V> lowerEntry(K key) {
		Entry<K, V> rtn = new Entry<K, V>();
		int clk = 0;
		K tmp = null;
		for (Position<Entry<K, V>> p : this.positions()) {
			Node<Entry<K, V>> node = (Node<Entry<K, V>>) p;
			K now = node.getElement().key;
			if (now.compareTo(key) < 0) {// key값보다 작을 경우.
				if (tmp == null) {
					tmp = now;
					clk = 1;
					rtn = node.getElement();
				} else if (now.compareTo(tmp) > 0) {
					tmp = now;
					rtn = node.getElement();
				}
			}
		}
		if (clk == 0) {
			return null;
		} else {
			return rtn;
		}
	}

	@Override
	public Entry<K, V> ceilingEntry(K key) {
		Entry<K, V> rtn = new Entry<K, V>();
		int clk = 0;
		K tmp = null;
		for (Position<Entry<K, V>> p : this.positions()) {
			Node<Entry<K, V>> node = (Node<Entry<K, V>>) p;
			K now = node.getElement().key;
			if (now.compareTo(key) == 0) {// key값을 가진 키가 있을 경우.
				return node.getElement();
			} else if (now.compareTo(key) > 0) {// key값보다 작을 경우.
				if (tmp == null) {
					tmp = now;
					clk = 1;
					rtn = node.getElement();
				} else if (now.compareTo(tmp) < 0) {
					tmp = now;
					rtn = node.getElement();
				}
			}
		}
		if (clk == 0) {
			return null;
		} else {
			return rtn;
		}
	}

	@Override
	public Entry<K, V> higherEntry(K key) {
		Entry<K, V> rtn = new Entry<K, V>();
		int clk = 0;
		K tmp = null;
		for (Position<Entry<K, V>> p : this.positions()) {
			Node<Entry<K, V>> node = (Node<Entry<K, V>>) p;
			K now = node.getElement().key;
			if (now.compareTo(key) > 0) {// key값보다 큰 경우.
				if (tmp == null) {
					tmp = now;
					clk = 1;
					rtn = node.getElement();
				} else if (now.compareTo(tmp) < 0) {
					tmp = now;
					rtn = node.getElement();
				}
			}
		}
		if (clk == 0) {
			return null;
		} else {
			return rtn;
		}
	}

	@Override
	public Entry<K, V> get(K key) {
		for (Position<Entry<K, V>> p : this.positions()) {
			Node<Entry<K, V>> node = (Node<Entry<K, V>>) p;
			K now = node.getElement().key;
			if (key.compareTo(now) == 0) {
				return node.getElement();
			}
		}
		return null;
	}

	@Override
    public V put(K key, V value) {
    	Entry<K,V> newEntry = new Entry<K, V>(key, value);//새로운 Entry를 만듬.
    	if(this.size == 0) {
    		this.addRoot(newEntry);
    		return null;
    	}
    	else {
    		Position<Entry<K,V>> p_search = this.root;
    		Node<Entry<K,V>> p_node = (Node<Entry<K,V>>) p_search;
    		V rtn=null;
    		if(this.get(key)!=null) {//트리에 같은 값을 가진 key가 있을 경우.
    			for(Position<Entry<K, V>> p : this.positions()) {
            		Node<Entry<K,V>> node = (Node<Entry<K,V>>) p;
            		K now = node.getElement().key;
            		if(key.compareTo(now)==0) {
            			rtn = node.getElement().value;
            			this.set(p, newEntry);
            			return rtn;
            		}
            	}
    		}
    		else {
    			this.ceilingEntry(key);
    			int check = 0;
    			while(check==0) {
    				int cmp = key.compareTo(p_search.getElement().key);
    					if(this.numChildren(p_search)==0) {
    						check = 1;
    						if(cmp<0) {
    							this.addLeft(p_search, newEntry);
    						}else {
    							this.addRight(p_search, newEntry);
    						}
    					}else if(this.numChildren(p_search)==1) {
    						if(cmp<0) {
    							if(this.left(p_search)!=null) {
    								p_search = this.left(p_search);
    							}else {
    								this.addLeft(p_search, newEntry);
    								check = 1;
    							}
    						}else {
    							if(this.right(p_search)!=null) {
    								p_search = this.right(p_search);
    							}else {
    								this.addRight(p_search, newEntry);
    								check = 1;
    							}
    						}
    					}else {
    						if(cmp<0) {
    							p_search = this.left(p_search);
    						}else {
    							p_search = this.right(p_search);
    						}
    					}
    			}
    		}
    		return rtn;
    	}
	}

	@Override
	public V remove(K key) {
		// 단말노드인경우 : 자기 부모가 null을 가르키게 함, 즉 external로 만듬.
		// 서브트리가 1개밖에 없을 경우: 자기 부모랑 자기 자식이랑 이음.
		// 서브트리가 2개일경우 : 자신의 오른쪽 서브트리의 최소값을 찾아(ceiling entry사용.) 자신의 부모와 연결하고 얘를 자신의 자식과
		// 연결하고 ~~~.
		V rtn;
		Position<Entry<K, V>> p = null;
		Node<Entry<K, V>> node = null;
		for (Position<Entry<K, V>> p_tmp : this.positions()) {
			node = (Node<Entry<K, V>>) p_tmp;
			K now = node.getElement().key;
			if (now.compareTo(key) == 0) {// key값을 가진 키가 있을 경우.
				p = p_tmp;
				break;
			}
		}
		if(p==null) {
			return null;
		}
		rtn = node.getElement().value;
		if (this.numChildren(p) <= 1) {
			this.remove(p);
			return rtn;
		} else {
			Entry<K,V> take = this.ceilingEntry(key);
			Position<Entry<K, V>> take_Position = this.root();
			for (Position<Entry<K, V>> p_search : this.positions()) {
				Node<Entry<K, V>> node_search = (Node<Entry<K, V>>) p;
				K now = node_search.getElement().key;
				if(now.compareTo(take.key)==0) {
					take_Position = p_search;
				}
			}
			node.setElement(take_Position.getElement());
			this.remove(take_Position);
			
		}

		return rtn;
	}

}
