package net.nod;

import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

public class PriorityQueueTest {
	
	PriorityQueue<Integer> testedInstance = new PriorityQueue<Integer>(new Comparator<Integer>() {
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}
	});
	
	@Test
	public void shouldPutSmallestItemToTheHead(){
		testedInstance.add(10);
		testedInstance.add(2);
		testedInstance.add(5);
		testedInstance.add(15);
		
		Assert.assertEquals("Wrong head of queue", 2, testedInstance.peek().intValue());
	}
}
