package com.tellme.demo;
 


import com.tellme.demo.users.Customer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
public class CustomerQueue {

	static Queue<Customer> queue = new LinkedList<>();

	private static CustomerQueue queueInstance = null;
	public static CustomerQueue getStreamInstance() {
 
		if (queueInstance == null) {
			queueInstance = new CustomerQueue();
		}
		return queueInstance;
	}
 
	public Queue<Customer> get() {
		return queue;
	}

	public void add(Customer value) {
		synchronized (queue) {
			queue.add(value);
		}
	}
	public void addAll(List<Customer> list) {
			queue.addAll(list);
		}
 
	// Removes a single instance of the specified element from this collection
	public void remove(String value) {
		synchronized (queue) {
			queue.remove(value);
		}
	}
 
	// Retrieves and removes the head of this queue, or returns null if this
	// queue is empty.
	public Customer poll() {
		Customer data = queue.poll();
		return data;
	}
 
	// Returns true if this collection contains no elements
	public boolean isEmpty() {
		return queue.isEmpty();
	}
 

	public int getTotalSize() {
		return queue.size();
	}
}