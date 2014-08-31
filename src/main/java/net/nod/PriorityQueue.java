package net.nod;

import java.util.AbstractQueue;
import java.util.Comparator;
import java.util.Iterator;

public class PriorityQueue<E> extends AbstractQueue<E> {
	
	private static final int DEFAULT_SIZE = 16;

	private static final int DEFAULT_BASE = 2;
	
	private E[] array;
	
	private int size;
	
	private final int base;
	
	private final Comparator<? super E> comparator;
	
	public PriorityQueue(int base, int initialSize, Comparator<? super E> comparator){
		this.base = base;
		this.array = allocateArray(initialSize);
		this.comparator = comparator;
	}
	
	public PriorityQueue(Comparator<? super E> comparator){
		this(DEFAULT_BASE, DEFAULT_SIZE, comparator);
	}
	
	@SuppressWarnings("unchecked")
	private E[] allocateArray(int initialSize){
		return (E[]) new Object[initialSize];
	}
	
	public boolean offer(E e) {
		bubbleUp(e, size);
		size++;
		return true;
	}
	
	private void bubbleUp(E e, int i){
		E parent;
		while(i > 0 && comparator.compare(e, parent = array[i / base]) < 0){
			array[i] = parent;
			i = i / base;
		}
		array[i] = e;
	}
	
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	public E peek() {
		return array[0];
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

}
