/***
 * 
 * @apiNote Lab-05_DoublyLinkedCollection
 * 
 * @author Zachary Evans
 *
 */

public class DoublyLinkedCollection<E> implements CollectionInterface<E> {
	//====================================================================================== Internal Classes
	private class Node {
		E data;
		Node next;
		Node prev;
		
		public Node(E data) {
			this(data, null, null);
		}
		
		public Node(E data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
		//============================================= Node methods
		void remove() {
			if (prev != null) {
				prev.next = next;
			}
			if (next != null) {
				next.prev = prev;
			}
			
		}
		void removeHead() {
			head = head.next;
		}

	
		Node insertLeft(Node n) {
			n.next = this;
			n.prev = prev;
			if (prev != null) prev.next = n; 
			prev = n;
			return n;
		}
		
		Node insertRight(Node n) {
			n.prev = this;
			n.next = next;
			if (next != null) next.prev = n; 
			next = n;
			return n;
		}
		
		Node find(E data) {
			if (this.data.equals(data)) {
				return this;
			}
			if (next == null) {
				return null;
			}
			return next.find(data);
		}
		
		Node get(int index) {
			if (index == 0) {
				return this;
			}
			if (next == null) return null;
			return next.get(--index);
		}
		
		public String toString() {
			if (next == null) return data.toString();
			return data + ", " + next.toString();
		}
	
	}
		
	
	//====================================================================================== Properties
	private Node head;
	private Node tail;
	private int size;
	
	//====================================================================================== Constructors
	public DoublyLinkedCollection() {
		clear();
	}
	
	//====================================================================================== Methods
	@Override
	public boolean add(E e) {
		return add(e, size);		
	}

	@Override
	public boolean add(E e, int index) {
		// You need to check the bounds first
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		if (isEmpty()) {
			head = tail = new Node(e);
			
		}
		else {
			if (index == size) {
				tail = tail.insertRight(new Node(e));
			}
				
			else if (index == 0) {
				head = head.insertLeft(new Node(e));
			}
			
			else {
				head.get(index).insertLeft(new Node(e));
			}
		}
		size++;
		return true;		
	}

	@Override
	public void clear() {
		head = tail = null;
		size = 0;
	}

	@Override
	public boolean contains(E o) {
		if (head.find(o).data.equals(o)) return true;
		return false;		
	}

	@Override
	public E get(int index) {
		// You need to check the bounds first
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		return head.get(index).data;		
	}

	@Override
	public int indexOf(E o) {
		if (!isEmpty()) {
			Node tmp = head;
			int i = 0;
			while (tmp.next != null) {
				if (tmp.data == o) {
					return i; 
				}
				tmp = tmp.next;
				i++;
			}
			//remember that there can be edge cases in these methods
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean remove(int index) {
		// You need to check the bounds first
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		//Check if this node is head or tail and reset head and tail accordingly
		if (index == 0) {
			head.removeHead();
		}
		else if (index == size-1) {
			head.get(index).remove();
		}
		else {
		head.get(index).remove();
		//change up find the node at that index and call remove on it
		}
		size--;
		return true;		
	}

	@Override
	public boolean remove(E o) {
		if (isEmpty()) {
			return false;
		}
		head.find(o).remove();
		size--;
		return true;
	}

	@Override
	public boolean remove() {
		return remove(size-1);		// remove method will remove the last node
	}

	@Override
	public int size() {
		return size;		
	}

	@Override
	public String toString() {
		if (isEmpty()) return "[]";
		return "[" + head.toString() + "]";
	}
}
