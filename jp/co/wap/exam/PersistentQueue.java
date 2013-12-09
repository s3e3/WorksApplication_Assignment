package jp.co.wap.exam;

import java.util.NoSuchElementException;

public class PersistentQueue<E> {
	
	//linked list class...
    private class Node<E> {
        final E data;
        final Node parent;
        public Node(E data,Node parent) {
            this.data=data;
            this.parent = parent;
        }
    }
	
	//head - points towards the head of the queue.
	//tail - points towards the tail of the queue.
	//neck - points towards the next node after head i.e the node whose parent is head.
	final Node<E> tail;
    final Node<E> head;
	Node<E> neck;
    final int size;
                
	//Initializes an empty queue........//
    public PersistentQueue() {
        this.head=null;
        this.tail=null;
		this.neck=null;
        this.size=0;
	}
    	
    private PersistentQueue(Node<E> head,Node<E> neck,Node<E> tail,int size) {
        this.head=head;
        this.tail=tail;
		this.neck=neck;
        this.size=size;
    }
	
    public PersistentQueue<E> enqueue(E e) {
        if(e==null)    throw new IllegalArgumentException();
        
		Node<E> node=new Node<E>(e,this.tail);
        
		if(this.size==0)       return new PersistentQueue(node,null,node,this.size+1);
		else                   return new PersistentQueue(this.head,this.neck,node,this.size+1);
        
    }

    public PersistentQueue<E> dequeue() {
        if(this.size==0)     throw new NoSuchElementException();
       
        if(this.size==1)     return new PersistentQueue();
        
        if(this.size==2)     return new PersistentQueue(this.tail,null,this.tail,this.size-1);
        
		//when head is removed head will point to the neck of the queue.
        if(this.neck==null){
			Node<E> node=this.tail;
            
			for(int i=0;i<size-3;i++)	node=node.parent;
			
            this.neck=node.parent;
            return new PersistentQueue(this.neck,node,this.tail,this.size-1);
        }
        
		return new PersistentQueue(this.neck,null,this.tail,this.size-1);
    }

    public E peek(){
        if(this.size==0)    throw new NoSuchElementException();
                               
        return this.head.data;
    }

    public int size(){
        return this.size;
    }
	
	public void Reverse(){
	
	}
	public void print(){
		System.out.println("Print: "+size);
		Node<E> node=tail;
		for(int i=0;i<size;i++){
			System.out.print(node.data);
			node=node.parent;
		}
		System.out.println(" ");
	}
}
