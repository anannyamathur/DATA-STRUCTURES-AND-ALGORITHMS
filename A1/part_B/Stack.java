// You should utilise your implementation of ArrayDeque methods to implement this
public class Stack implements StackInterface {	
    DequeInterface stack= new ArrayDeque();
	public void push(Object o){
    	//you need to implement this
		stack.insertLast(o);
    
  	}

	public Object pop() throws EmptyStackException{
    	//you need to implement this
		try
		{
		
    	       
			   
		return stack.removeLast();
		}
		catch(EmptyDequeException e)
		{
			throw new EmptyStackException("Stack Underflow");
		}
	}

	public Object top() throws EmptyStackException{
    	try{
		
    	     
		return stack.last();
		}
		catch(EmptyDequeException e)
		{
			throw new EmptyStackException("Nothing to show");
		}
	}

	public boolean isEmpty(){
    	//you need to implement this
    	return stack.isEmpty();
	}

    public int size(){
    	//you need to implement this
    	return stack.size();
    }
}