import java.io.*;
import java.util.*;



class Customer
{ public int id;
  public String state;
  public int arrivetime;
  public int exitTime;
  public int queueNo;
  public int number;
  public int ordertime;
  public Customer(int ID)
  {id=ID;
   queueNo=0;
   number=0;
   state= "WaitingForTurn";
   arrivetime=0;
   exitTime=0;
   ordertime=0;
   
	  
  }
	
}

class AVLNode
{
	public AVLNode left;
	public AVLNode right;
	public Customer node;
	public int height;
	
	public AVLNode(Customer ID)
	{
		node=ID;
		//left=null;
		//right=null;
		height=1;
	}
}

class Queue{
	public int priority;
	//public int size;
	public int prevtime;
	public int k;
	public Vector<Customer> customers;
	public Queue(int id){
		k=id;
		priority=id;
		customers= new Vector<Customer>();
		prevtime=0;
	}
	
}
class minHeap {
    public Queue heap[];
   public int size;
    public int Maxsize;
  
    public int front ;
  
    public minHeap(int maxsize)
    {   front=1;
        Maxsize = maxsize;
        size=0;
        heap = new Queue[Maxsize + 1];
        heap[0]=new Queue(Integer.MIN_VALUE);
		
    }
}

class sort implements Comparator<Customer>
{
	public int compare(Customer a, Customer b)
	{
		if(a.ordertime==b.ordertime)
		{
			return (b.queueNo-a.queueNo);
		}
		return (a.ordertime-b.ordertime);
	}
}

class sortHeap implements Comparator<Queue>
{  public int compare(Queue a, Queue b)
	{
		if(a.customers.size()==b.customers.size())
		{
			return (a.priority-b.priority);
		}
		return (a.customers.size()-b.customers.size());
	}
	
}
	


	
	
	


    



public class MMBurgers implements MMBurgersInterface {
	
	
	AVLNode head;


//_________________________________________________
private int height(AVLNode node)
{
	if(node==null)
	{
		return 0;
	}
	return node.height;
}

private int maximum(int val1, int val2)
{
	if(val1>val2)
	{
		return val1;
	}
	return val2;
}

private AVLNode rotateR(AVLNode y) //rotate right
{
	AVLNode x=y.left;
	AVLNode z=x.right;
	x.right=y;
	y.left=z;
	y.height=maximum(height(y.left), height(y.right)) +1;
	x.height= maximum(height(x.left), height(x.right)) +1;
	
	
	return x;
}

private AVLNode rotateL(AVLNode x)   //left rotate
{
	AVLNode y=x.right;
	AVLNode z=y.left;
	
	y.left=x;
	x.right=z;
	
	x.height= maximum(height(x.left), height(x.right)) +1;
	y.height=maximum(height(y.left), height(y.right)) +1;
	
	return y;
}

private int balFact(AVLNode node) //balance factor
{
	if(node==null)
	{
		return 0;
	}
	
	return height(node.left)-height(node.right);
}




private Customer find(AVLNode root, int val)
{
	if(root==null)
	  return null;
	if(root.node.id==val)
	  return root.node;
	if(root.node.id<val)
	  return find(root.right, val);
	return find(root.left, val);
}


private void insert(Customer ins)
{
	head= insert2(head, ins);
	
}

private AVLNode insert2(AVLNode root, Customer ins)
{
	if(root==null)
	{
		root= new AVLNode(ins);
		return root;
	}
	if(root.node.id<ins.id)
	{
		root.right=insert2(root.right, ins);
		
	}
	else if(root.node.id>ins.id)
	{
		root.left=insert2(root.left, ins);
	}
	else
	    return root;
	root.height= maximum(height(root.left),height(root.right))+1;
	int bal= balFact(root);
	
	if(bal>1 && ins.id<root.left.node.id)
	{  
		return rotateR(root);
	}
	
	if(bal<-1 && ins.id>root.right.node.id )
	{
		return rotateL(root);
	}
	
	if(bal>1 && ins.id>root.left.node.id)
	{
		root.left=rotateL(root.left);
		return rotateR(root);
	}
	
	if(bal<-1 && ins.id< root.right.node.id)
	{
		root.right= rotateR(root.right);
		return rotateL(root);
	}
	return root;
}

private AVLNode min_key(AVLNode node)
{
	AVLNode temp=node;
	while(temp.left != null)
	{
		temp=temp.left;
	}
	return temp;
}

private void delete(Customer del)
{
	head= del2(head, del);
}
private AVLNode del2(AVLNode node, Customer del)
{
	if(node==null)
	{
		return node;
	}
	if(del.id<node.node.id)
	{
		node.left=del2(node.left, del);
	}
	else if (del.id>node.node.id)
	{
		node.right= del2(node.right, del);
	}
	else
	{
		if( node.right==null || node.left==null)
		{
			AVLNode val=null;
			if(val==node.right)
			{
				val=node.left;
			}
			else{
				val=node.right;
			}
			
			if(val==null)
			{
				val=node;
				node=null;
			}
			else
			{
				node=val;
			}
			
			
		}
		
		
		else
		{
			AVLNode val= min_key(node.right);
			
			node.node=val.node;
			
			node.right=del2(node.right, val.node);
			
		}
	}
	if(node==null)
	{
		return node;
	}
	node.height= maximum(height(node.left),height(node.right))+1;
	int bal= balFact(node);
	
	if(bal>1 && balFact(node.left)>=0)
	{  
		return rotateR(node);
	}
	if(bal>1 && balFact(node.left)<0)
	{
		node.left=rotateL(node.left);
		return rotateR(node);
	}
	
	if(bal<-1 && balFact(node.right)<=0 )
	{
		return rotateL(node);
	}
	
	
	
	if(bal<-1 && balFact(node.right)>0)
	{
		node.right= rotateR(node.right);
		return rotateL(node);
	}
	return node;
	
}
//_________________________________________________________________

/*minHeap heaps; 
private int parent(int pos)
    {
        return pos / 2;
    }
  
    
private int left(int pos)
    {
        return (2 * pos);
    }
  
   
private int right(int pos)
    {
        return (2 * pos) + 1;
    }
  
    
private boolean isLeaf(int pos)
    {
        return (pos >= (heaps.size / 2) && pos <= heaps.size); 
           
    }
  
    
private void swap(int pos1, int pos2)
    {
        Queue temp;
        temp = heaps.heap[pos1];
        heaps.heap[pos1] = heaps.heap[pos2];
        heaps.heap[pos2] = temp;
    }
  
    
private void buildHeap(int pos)
    {
  
        
        if (!isLeaf(pos)) {
			if(heaps.heap[pos].customers.size() > heaps.heap[left(pos)].customers.size() || heaps.heap[pos].customers.size() > heaps.heap[right(pos)].customers.size()) {
  
                
                if (heaps.heap[left(pos)].customers.size() < heaps.heap[right(pos)].customers.size()) {
                    swap(pos, left(pos));
                    buildHeap(left(pos));
                }
  
                
                else if(heaps.heap[left(pos)].customers.size() == heaps.heap[right(pos)].customers.size()) 
				{
					if(heaps.heap[left(pos)].priority < heaps.heap[right(pos)].priority)
                    {swap(pos, left(pos));
                    buildHeap(left(pos));
						
					}
					else
					{swap(pos, right(pos));
                    buildHeap(right(pos));
					}
                }
				else{
					swap(pos, right(pos));
                    buildHeap(right(pos));
				}
            }
			
			
			else if(heaps.heap[pos].customers.size() == heaps.heap[left(pos)].customers.size() || heaps.heap[pos].customers.size() == heaps.heap[right(pos)].customers.size())
            {if (heaps.heap[pos].priority > heaps.heap[left(pos)].priority || heaps.heap[pos].priority > heaps.heap[right(pos)].priority) 
  
                { 
                if (heaps.heap[left(pos)].priority < heaps.heap[right(pos)].priority) {
                    swap(pos, left(pos));
                    buildHeap(left(pos));
                }
  
                
                else {
                    swap(pos, right(pos));
                    buildHeap(right(pos));
                }
				}
            }
			}
			
        }
}
 */   
	
/*private void insertHeap(Queue item)
    {
        if (heaps.size <heaps.Maxsize) {
			
            
        //heaps.size=heaps.size+1;
        heaps.heap[++heaps.size] = item;
        int current = heaps.size;
  
        while ((heaps.heap[current].customers.size() < heaps.heap[parent(current)].customers.size())||(heaps.heap[current].priority < heaps.heap[parent(current)].priority)) {
            swap(current, parent(current));
            current = parent(current);
        }
		}
		
    }

 private void minheap()
    {
        for (int pos = (heaps.size / 2); pos >= 1; pos=pos-1) {
            buildHeap(pos);
        }
    }
  
    
 private Queue remove()
    {
        Queue pop = heaps.heap[heaps.front];
        heaps.heap[heaps.front] = heaps.heap[heaps.size];
		heaps.size=heaps.size-1;
        buildHeap(heaps.front);
        return pop;
    }
*/
//__________________________________________________________________	
	
	int flag=0;
	int patty;
	int burgers;
    int Rburger=0;
	int Gburger=0;
    int futuretime=0;
	int n=0;
	int time=0;
	
	int call1=0;
	int K;
	int M;
	int call2=0;
	int remaining=0;
	int presentTime=0;
	int timenow;
	Vector<Customer> consumer= new Vector<Customer>();
	Vector<Customer> patties= new Vector<Customer>();
	Vector<Queue> queue= new Vector<Queue>();
	Vector<Customer> aux= new Vector<Customer>();
	Vector<Customer> temp= new Vector<Customer>();
	
	private Queue findElement(int id)
	{
		for(int i=0;i<queue.size(); i=i+1)
		{
			if(queue.get(i).priority==id)
			{
				return queue.get(i);
			}
		}
		return null;
	}
	
	
	
	

	
	
    public boolean isEmpty(){
        //your implementation
		boolean bool=Rburger==0 && patties.size()==0 && Gburger==0;
		//System.out.println(bool);
        return bool;
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 
    
    public void setK(int k) throws IllegalNumberException{
        //your implementation
		if(call1==1)
		{
			throw new IllegalNumberException("Has been declared already");
		}
		
		K=k;
		call1=1;
		
		for(int i=1; i<=k; i=i+1)
		{  Queue q= new Queue(i);
			queue.add(q);
		}
		Collections.sort(queue, new sortHeap());
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    }   
    
    public void setM(int m) throws IllegalNumberException{
        //your implementation
		if(call2==1)
		{
			throw new IllegalNumberException("Declared already");
		}
		M=m;
		patty=m;
	
		call2=1;
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 
    
	
	
	
	
	
	
	
	
	
	
	
	
	private void cheftime(int t)
	{
    presentTime=0;
	 
	 if(patties.size()!=0)
		{
			Customer customer=patties.get(0);
			int n=customer.number;
			presentTime=presentTime+customer.ordertime;
		if(presentTime<=t)
		{while(customer.number>0)
			{
				if(customer.number<=M)
			{   
			    M=M-customer.number;
				Gburger=patty-M;
			    
				
			
				presentTime=presentTime+10;
				customer.exitTime=presentTime+1;
				
				if(presentTime<=t)
				{Gburger=Gburger-customer.number;
			     Rburger=0;
				
				}
				customer.number=0;
			
				
				
				
				customer.state= "Left";
				
				
			  
				
			}
			else{
				
				if(M==0)
				{
					if(temp.size()==0)
					{
						presentTime=customer.ordertime;
					
						M=patty;
						
						Gburger=patty;
						Rburger=customer.number;
						
						
					}
					
					else
					{
						presentTime=temp.get(0).exitTime-1;
						
						M=temp.get(0).number;
						Gburger=0;
						Rburger=customer.number;
						temp.remove(temp.get(0));
						
					}
				}
				else
				{ 
					presentTime=presentTime+10;
					customer.number=customer.number-M;
				M=0;
				Gburger=patty;
					Rburger=customer.number;
				
				
				
				}
			   }
				
			    
				
			}
			patties.remove(patties.get(0));
		if(patties.size()!=0)
				{if(patties.get(0).ordertime<customer.exitTime)
				{ temp.add(customer);
					
					cheftime(t);
				}
				else
				{M=M+n;
				cheftime(t);
				
				}
				}
				else
				{
					M=M+n;
					//Gburger=0;
					//Rburger=0;
					
				}
				
			
			
			
		}
		}
		else
		{
			Gburger=0;
              Rburger=0;
		}
		
	 }
	
	
	
	
	private void cheftime()
	{
    presentTime=0;
	 
	 if(patties.size()!=0)
		{
			Customer customer=patties.get(0);
			int n=customer.number;
			presentTime=presentTime+customer.ordertime;
		
			while(customer.number>0)
			{
				if(customer.number<=M)
			{   
			    M=M-customer.number;
				
			    
				customer.number=0;
				
			
				presentTime=presentTime+10;
				
			
				
				
				customer.exitTime=presentTime+1;
				customer.state= "Left";
				
			
				
				
			}
			else{
				
				if(M==0)
				{
					if(temp.size()==0)
					{
						presentTime=customer.ordertime;
						M=patty;
						
					}
					
					else
					{
						presentTime=temp.get(0).exitTime-1;
						M=temp.get(0).number;
						
						temp.remove(temp.get(0));
					}
				}
				else
				{ 
					presentTime=presentTime+10;
					customer.number=customer.number-M;
				
				
				M=0;
				
				}
			   }
				
			  			  
				
			}
			patties.remove(patties.get(0));
		if(patties.size()!=0)
				{if(patties.get(0).ordertime<customer.exitTime)
				{ temp.add(customer);
					
					cheftime();
				}
				else
				{M=M+n;
				cheftime();
				
				}
				}
				else
				{
					M=M+n;
					
					
				}
				
			  
			
			
			
		}
		
		Gburger=0;
              Rburger=0;
		
	 }
	
	
	
	
	private void setordertime()
	{     
		while(consumer.size()!=0)
		{
			Customer customer=consumer.get(0);
			Queue q= findElement(customer.queueNo);
			if(q.prevtime>=customer.arrivetime)
			{
				customer.ordertime=q.prevtime+customer.queueNo;
				q.prevtime=customer.ordertime;
			}
			else{
				customer.ordertime=customer.arrivetime+customer.queueNo;
				q.prevtime=customer.ordertime;
			}
			patties.add(customer);
			
				
				q.customers.remove(customer);
				
				
				Collections.sort(queue, new sortHeap());
			consumer.remove(consumer.get(0));
		}
		Collections.sort(patties, new sort());		 
				
		    
		
	}
	
	
	
	
	
	
	
	
	
	
	
    public void advanceTime(int t) throws IllegalNumberException{
        //your implementation
		if(timenow>t)
		{
			throw new IllegalNumberException("Illegal");
		}
		
		setordertime();
		cheftime(t);
		
		
	
		timenow=t;
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public void arriveCustomer(int id, int t, int numb) throws IllegalNumberException{
        //your implementation
		if(numb<0)
		{
			throw new IllegalNumberException("Illegal");
		}
		if(t<timenow)
		{
		    throw new IllegalNumberException("Illegal");	
		}
		setordertime();
        Customer customer= new Customer(id);
		customer.number=numb;
		customer.arrivetime=t;
		
		Queue q= queue.get(0);
		customer.queueNo=q.priority;
		insert(customer);
		q.customers.add(customer);
		
	    Collections.sort(queue, new sortHeap());
		
		consumer.add(customer);
		aux.add(customer);
		timenow=t;
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public int customerState(int id, int t) throws IllegalNumberException{
        //your implementation
		if(t<timenow)
		{
		    throw new IllegalNumberException("Illegal");	
		}
		setordertime();
		cheftime(t);
		
		
		int ans;
		
		Customer customer=find(head, id);
		timenow=t;
		if(customer==null)
		{
			
			throw new IllegalNumberException("Illegal");
		}
		else if(customer.arrivetime>t)
		{   
			ans= 0;
		}
		else if(customer.ordertime>t)
		{
			ans= (customer.queueNo);
		}
		else if(customer.exitTime>=t)
		{  
			ans= (K+1);
		}
		else
		{
			ans=(K+2);
		}
		//System.out.println(ans);
		return ans;
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public int griddleState(int t) throws IllegalNumberException{
        //your implementation
		if(t<timenow)
		{
		    throw new IllegalNumberException("Illegal");	
		}
		timenow=t;
		setordertime();
		cheftime(t);
		
		
		int ans= Gburger;
		//System.out.println(ans);
		return ans;
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public int griddleWait(int t) throws IllegalNumberException{
        //your implementation
		if(t<timenow)
		{
		    throw new IllegalNumberException("Illegal");	
		}
		timenow=t;
	    setordertime();
		cheftime(t);
		
		int ans=Rburger;
		//System.out.println(ans);
		return ans;
		
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 
    
    public int customerWaitTime(int id) throws IllegalNumberException{
        //your implementation
		setordertime();
        cheftime();
		Customer custom=find(head,id);
		if(custom==null)
		{
			throw new IllegalNumberException("Illegal");
		}
		
		int waitTime= custom.exitTime-custom.arrivetime;
		//System.out.println(waitTime);
		return waitTime;
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 
    float sum=0;
	public float avgWaitTime(){
        //your implementation
		setordertime();
		cheftime();
		for(int i=0; i<aux.size();i=i+1)
		{
			sum=sum+ (float)(aux.get(i).exitTime-aux.get(i).arrivetime);
		}
		float ans= (float)(sum/aux.size());
		ans= (float)(Math.round(ans*100.0)/100.0);
		//System.out.println(ans);
		return ans;
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    
}
