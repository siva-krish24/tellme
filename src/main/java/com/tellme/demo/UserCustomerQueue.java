package com.tellme.demo;
 


import com.tellme.demo.users.UserCustomerEntity;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

public class UserCustomerQueue {

	static Queue<UserCustomerEntity> queue = new LinkedList<>();

	private static UserCustomerQueue queueInstance = null;
	public static UserCustomerQueue getStreamInstance() {
 
		if (queueInstance == null) {
			queueInstance = new UserCustomerQueue();
		}
		return queueInstance;
	}
	public Stream<UserCustomerEntity> getstream() {
		return queue.stream();
	}


	public Queue<UserCustomerEntity> get() {
		return queue;
	}

	public void add(UserCustomerEntity value) {
		synchronized (queue) {
			queue.add(value);
		}
	}
	public void addAll(List<UserCustomerEntity> list) {
			queue.addAll(list);
		}
 
	// Removes a single instance of the specified element from this collection
	public void remove(UserCustomerEntity value) {
		synchronized (queue) {
			queue.remove(value);
		}
	}
 
	// Retrieves and removes the head of this queue, or returns null if this
	// queue is empty.
	public UserCustomerEntity poll() {
		UserCustomerEntity data = queue.poll();
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