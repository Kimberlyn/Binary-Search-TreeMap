import java.lang.*;

 public class BinaryTree<E> {
	private E root;
	private BinaryTree<E> left, right;

// Constructors
	public BinaryTree(E root, BinaryTree<E> left, BinaryTree<E> right){
	this.root = root;
	this.left = left;
	this.right = right;
	}
	public BinaryTree(E root){
	this(root,null,null);
	}


// Methods
  	public E getRoot(){
	return (E)this.root;
	}
  	public BinaryTree<E> getLeft(){
	return this.left;
	}
  	public BinaryTree<E> getRight(){
	return this.right;
	}
  	public E setRoot(E element){
	Object local = this.root;
	this.root = element;
	return (E)local;
	}
	public BinaryTree<E> setLeft(BinaryTree<E> tree){
	BinaryTree leftBinaryTree = this.right;
        this.left = tree;
        return leftBinaryTree;
	}
	public BinaryTree<E> setRight(BinaryTree<E> tree){
	BinaryTree rightBinaryTree = this.right;
	this.right = tree;
	return rightBinaryTree;
	}
 	public String toString(){
	StringBuilder buf = new StringBuilder("" + this.root);
        if (!isLeaf()) {
        buf.append("(");
        if (this.left != null)
        buf.append (this.left);
        if (this.right != null)
        buf.append (this.right);
        buf.append(")");
        }
        return buf + "";
        }




  	public boolean isLeaf() {
	return (this.left == null && this.right == null);
	}


}
