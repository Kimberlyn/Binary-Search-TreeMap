/**
 *	TreeMap implemented as Binary Search Tree
 */
import java.lang.*;

public class MyTreeMap<K extends Comparable<K>,V> implements MyMap<K,V> 
{
	private BinaryTree<Element> map;
	java.util.Set<K> keys;  // to return keys in order

	public boolean containsKey(K key) {

	if (search(new Element(key, null), map) != null) {
	return true;
	} else {
	return false;
	}

	}
	public V put(K key, V value) {
	Element element = insert(new Element(key, value));
	if (element != null) {
		return element.value;
	} else {
		return null;
	}
	}

	public V get(K key) {
	Element element = search(new Element(key, null), map);
	if (element != null) {
		return element.value;
	} else {
		return null;
	}
	}
	public V remove(K key) {
	Element element = delete(map, new Element(key, null), null);
		if (element != null) {
		return element.value;
		} else {
		return null;
		}
	}

	public java.util.Set<K> keySet() {
	keys = new java.util.TreeSet();
	inorder(map);
	return keys;
	}

        // Element class
	public class Element {
	K key;
	V value;
	public Element(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public int compareTo(Element that) {
		return this.key.compareTo(that.key);
	}
	public String toString() {
		return (key.toString());
	}
}

// private methods implementing BST operations search, insert, delete, inorder
// reference: Wikipedia article on Binary Search Tree
	private Element search(Element element, BinaryTree<Element> tree) {

		if (tree == null) {
		return null;
		}
		Element r = tree.getRoot();
		if (element.compareTo(r) == 0) {
		return r;
		} else if (element.compareTo(r) < 0) {
		return search(element, tree.getLeft());
		} else {
		return search(element, tree.getRight());
		}

	}
	private Element insert(Element element) {
	if (map == null) {
	map = new BinaryTree<>(element);
        return null;
        } else {
        return insert(element, map);
        }
	}

	private Element insert(Element element, BinaryTree<Element> tree) {

        if(element.compareTo(tree.getRoot()) == 0){
            tree.setRoot(element);
            return tree.getRoot();
        }
        else if(element.compareTo(tree.getRoot()) < 0){
            if(tree.getLeft()==null){
                tree.setLeft(new BinaryTree<>(element));
                return null;
            }
            else{
                return insert(element, tree.getLeft());
            }
        }
        else if(element.compareTo(tree.getRoot()) > 0){
            if(tree.getRight() == null){
                tree.setRight(new BinaryTree<>(element));
                return null;
            }
            else{
                return insert(element, tree.getRight());
            }
        }
        return null;
    }
	private Element delete(BinaryTree<Element> tree, Element element, BinaryTree<Element> parent) {

	if (tree == null) {
	return null;
	} else {
	Element r = tree.getRoot();
	if (element.compareTo(r) < 0) {
	return delete(tree.getLeft(), element, tree);
	} else if (element.compareTo(r) > 0) {
	return delete(tree.getRight(), element, tree);
	} else {
		if (tree.isLeaf()) {
			if (element.compareTo(parent.getRoot()) < 0) {
			parent.setLeft(null);
			} else {
			parent.setRight(null);
			}
		} else if (tree.getLeft() != null && tree.getRight() != null) {
		Element successor = successor(tree);
		delete(tree, successor, parent);
		tree.setRoot(successor);
		} else if (tree.getLeft() == null) {
    			promote(tree, parent, tree.getRight());
                } else {
                	promote(tree, parent, tree.getRight()); 
                }
        return r;
	}
  }
}
    //method that gives the next inorder successor
    private Element successor(BinaryTree<Element> tree) {
        while (tree.getLeft() != null) {
            tree = tree.getLeft();
        }
        return tree.getRoot();
    }

        // make newChild the appropriate (left or right) child of parent, if parent exists
	private void promote(BinaryTree<Element> tree, BinaryTree<Element> parent, BinaryTree<Element> newChild) {

	if (parent != null) {

		if (newChild.getRoot().compareTo(parent.getRoot()) < 0) {
        	parent.setLeft(newChild);
		} else {
                parent.setRight(newChild);
		}

	}
}


	private void inorder(BinaryTree<Element> tree) {

		if(tree != null){
            	inorder(tree.getLeft());
            	keys.add(tree.getRoot().key);
            	inorder(tree.getRight());
        	}
       }

} // ends class
