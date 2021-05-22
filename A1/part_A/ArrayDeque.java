public class ArrayDeque implements DequeInterface {
	int N = 1;
    Object[] array;
    int  f;
    int  r;
	Object o;
	public ArrayDeque()
    {
        array = new Object[N];
        f = 0;
        r = 0;
        
    }
	
	boolean isFull()
    {
        
		return (size()==N-1);
		
    }

  public void insertFirst(Object o){
    //you need to implement this
    // check whether Deque full or not
        if (isFull())
        {   
            N=2*N;
			Object[] temp=new Object[N];
			for(int i=0; i<array.length-1; i++)
			{
				temp[i]=array[(f+i)%array.length];
				
				
			}
			f=0;
			r=array.length-1;
			
			array=temp;
			
			
			
        }
  
        
		
		f = (f-1+N)%N ;
		array[f] = o ;

		
		
	
	
    
  }
  
  public void insertLast(Object o){
    //you need to implement this
	if (isFull())
        {   
            N=2*N;
		    Object[] temp=new Object[N];
			for(int i=0; i<array.length-1; i++)
			{ temp[i]=array[(f+i)%array.length];
			 
			}
            r=array.length-1;			
			
			array=temp;
			f=0;
			
			
            
        }
	
  
        
		
		
        array[r] = o ;
		r=(r+1)%N;
		

    
  }
  
  public Object removeFirst() throws EmptyDequeException{
    //you need to implement this
	// check whether Deque empty or not
        if (isEmpty())
        {
            throw new EmptyDequeException("Queue Underflow\n");
			
            
        }
        
		 
        
        else
		
		 
		 { o=array[f];
		 f=(f+1)%N;
		
	
        return o; 
}}
  
  public Object removeLast() throws EmptyDequeException{
    //you need to implement this
	if (isEmpty())
        {
            throw new EmptyDequeException(" Underflow");
            
        }
		
       
    else   
		    
			{r = (r-1+N)%N ;
			o=array[r]; 
	 
		
        return o;
  }}

  public Object first() throws EmptyDequeException{
    //you need to implement this
	// check whether Deque empty or not
        if (isEmpty())
        {
            throw new EmptyDequeException(" Underflow");
            
        }
        return array[f];
    
  }
  
  public Object last() throws EmptyDequeException{
    //you need to implement this
	// check whether Deque empty or not
        if(isEmpty())
        {
            throw new EmptyDequeException(" Underflow\n");
            
        }
		int curr=(r+N-1)%N;
        return array[curr];
	
    
  }
  
  public int size(){
    
	return (N+r-f)%N;
  }
  
  public boolean isEmpty(){
    //you need to implement this
	return (f==r);
    
  }

  public String toString(){
    //you need to implement this
    String answer = "[";
	  if(f!=r){
	  answer=answer + array[f%N];
	  
		  for(int i=1; i<size(); i=i+1)
		  {
			  answer=answer + ", " + array[(f+i)%N];
		  }
          
	  
	  } 
	  answer = answer + "]";
	  return answer;
	  
  }  
}