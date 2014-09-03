package net.nod;

import java.util.AbstractQueue;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
	
	public PriorityQueue(int initialSize, Comparator<? super E> comparator){
		this(DEFAULT_BASE, initialSize, comparator);
	}
	
	public PriorityQueue(Comparator<? super E> comparator){
		this(DEFAULT_BASE, DEFAULT_SIZE, comparator);
	}
	
	@SuppressWarnings("unchecked")
	private E[] allocateArray(int initialSize){
		return (E[]) new Object[initialSize];
	}
	
	private void grow(){
		int newSize = array.length + array.length / 2;
		array = Arrays.copyOf(array, newSize);
	}
	
	public boolean offer(E e) {
		if(size >= array.length){
			grow();
		}
		bubbleUp(e, size);
		size++;
		return true;
	}
	
	private void bubbleUp(E e, int i){
		E parent;
		while(i > 0 && comparator.compare(e, parent = array[(i - 1) / base]) < 0){
			array[i] = parent;
			i = (i - 1) / base;
		}
		array[i] = e;
	}
	
	public E poll() {
		if(size == 0){
			return null;
		}
		E r = array[0];
		array[0] = null;
		size--;
		if(size != 0){
			sinkDown(0, array[size]);
		}	
		return r;
	}

	private void sinkDown(int i, E e){
		while(i < size - 1){
			int j = i * base + 1;
			int t = Math.min((i + 1) * base, size);
			int min = j;
			for(; j <= t; j++){
				if(comparator.compare(array[j], array[min]) < 0){
					min = j;
				}
			}
			if(comparator.compare(e, array[min]) > 0){
				array[i] = array[min];
				i = min;
			}
		}
		array[i] = e;
		array[size] = null;
	}
	
	public E peek() {
		return array[0];
	}
	
	@Override
	public Iterator<E> iterator() {
		return new It();
	}
	
	private class It implements Iterator<E> {
		
		private int position = 0;
		
		@Override
		public boolean hasNext() {
			return position < array.length && array[position] != null;
		}

		@Override
		public E next() {
			if(array[position] == null){
				throw new NoSuchElementException();
			}
			return array[position++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	//TODO
	private void removeAt(int i){
		
	}
	
	private int indexOf(E e){
		for(int i = 0; i < array.length; i++){
			if(array[i] == e){
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		for(int i = 0; i < array.length; i++) {
			array[i] = null;
		}
		size = 0;
	}
}
