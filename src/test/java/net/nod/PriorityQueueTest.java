package net.nod;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {
	
	private final static Comparator<Integer> COMPARATOR = new Comparator<Integer>() {
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}
	};
	
	PriorityQueue<Integer> testedInstance;
	
	@Before
	public void setUp(){
		testedInstance = new PriorityQueue<Integer>(COMPARATOR);
	}
	
	@Test
	public void shouldPutSmallestItemToTheHead(){
		testedInstance.add(10);
		testedInstance.add(2);
		testedInstance.add(5);
		testedInstance.add(15);
		
		Assert.assertEquals("Wrong head of queue", 2, testedInstance.peek().intValue());
	}
	
	@Test
	public void shouldReorderAfterPoll(){
		testedInstance.add(10);
		testedInstance.add(2);
		testedInstance.add(5);
		testedInstance.add(15);
		
		Assert.assertEquals("Wrong poll result", 2, testedInstance.poll().intValue());
		Assert.assertEquals("Wrong head of queue", 5, testedInstance.peek().intValue());
	}
}
