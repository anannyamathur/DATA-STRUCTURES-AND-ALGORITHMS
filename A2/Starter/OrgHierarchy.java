import java.io.*; 
import java.util.*; 

// Tree node
class Node {
	public int id;
	public Node parent;
	public Vector<Node> children;
	public int level;
	public Node(int ID)
	{ id=ID;
	  children= new Vector<Node>();
	  parent=null;
	  level=1;
		
	}
	
  
}

class AVLNode
{
	public AVLNode left;
	public AVLNode right;
	public Node node;
	public int height;
	
	public AVLNode(Node ID)
	{
		node=ID;
		//left=null;
		//right=null;
		height=1;
	}
}


public class OrgHierarchy implements OrgHierarchyInterface{

//root node
Node root;
int size=0;

AVLNode head;

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



public boolean isEmpty(){
	//your implementation
	return (size==0);
	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
} 

public int size(){
	//your implementation
	return size;
	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

/*

private Node find(Node node, int val)
{   
	Vector<Node> Main= new Vector<Node>();
	Main.add(node);
	while(Main.size()!=0)
	{
		int n=Main.size();
		while(n>0)
		{
			Node temp=Main.get(0);
			Main.remove(Main.get(0));
			if(temp.id==val)
			{
				return temp;
			}
			for(int i=0; i<temp.children.size();i++)
			{
				Main.add(temp.children.get(i));
			}
			n=n-1;
		}
		
	}
	return null;
	
}

*/

private AVLNode rotateR(AVLNode y) //rotate right
{
	AVLNode x=y.left;
	AVLNode z=x.right;
	x.right=y;
	y.left=z;
	
	x.height= maximum(height(x.left), height(x.right)) +1;
	y.height=maximum(height(y.left), height(y.right)) +1;
	
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




private Node find(AVLNode root, int val)
{
	if(root==null)
	  return null;
	if(root.node.id==val)
	  return root.node;
	if(root.node.id<val)
	  return find(root.right, val);
	return find(root.left, val);
}


private void insert(Node ins)
{
	head= insert2(head, ins);
	
}

private AVLNode insert2(AVLNode root, Node ins)
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

private void delete(Node del)
{
	head= del2(head, del);
}
private AVLNode del2(AVLNode node, Node del)
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
	
	if(bal>1 && del.id<node.left.node.id)
	{  
		return rotateR(node);
	}
	
	if(bal<-1 && del.id>node.right.node.id )
	{
		return rotateL(node);
	}
	
	if(bal>1 && del.id>node.left.node.id)
	{
		node.left=rotateL(node.left);
		return rotateR(node);
	}
	
	if(bal<-1 && del.id< node.right.node.id)
	{
		node.right= rotateR(node.right);
		return rotateL(node);
	}
	return node;
	
}

/*
private int depth(Node node)
{int d=0;
 while(node!=null)
 {
	 d=d+1;
	 node=node.parent;
 }
 return d;
 
	
}
*/


public int level(int id) throws IllegalIDException, EmptyTreeException{
	//your implementation
	if(isEmpty())
	{
		throw new EmptyTreeException(" Tree is Empty ");
		
	}
	//Node node= find(root, id);
	
	Node node=find(head, id);
	
	if(node==null)
	{
		throw new IllegalIDException(" ID Illegal ");
	}
	//return depth(node);
	return node.level;
	
	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
} 

public void hireOwner(int id) throws NotEmptyException{
	//your implementation
	if(isEmpty())
	{
		root=new Node(id);
		size=size+1;
		
		
		insert(root);
		
		
	}
	else
	{throw new NotEmptyException(" Tree is Not EMPTY ");
	}
	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

public void hireEmployee(int id, int bossid) throws IllegalIDException, EmptyTreeException{
	//your implementation
	if(isEmpty())
	{
		throw new EmptyTreeException(" Tree is Empty ");
		
	}
	//Node boss= find(root, bossid);
	
	Node boss= find(head, bossid);
	
	if(boss==null)
	{
		throw new IllegalIDException(" ID Illegal ");
	}
	Node employee= new Node(id);
	employee.parent=boss;
	boss.children.add(employee);
	size=size+1;
	employee.level=boss.level+1;
	
	
	insert(employee);
	
	
	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
} 

public void fireEmployee(int id) throws IllegalIDException,EmptyTreeException{
	//your implementation
	if(isEmpty())
	{
		throw new EmptyTreeException(" Tree is Empty ");
		
	}
	//Node employee= find(root, id);
	
	Node employee= find(head, id);
	
	if(employee==null || employee.parent==null)
	{
		throw new IllegalIDException(" ID Illegal ");
	}
	Node boss=employee.parent;
	boss.children.remove(employee);
	size=size-1;
	delete(employee);
	
 	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}
public void fireEmployee(int id, int manageid) throws IllegalIDException,EmptyTreeException{
	//your implementation
	if(isEmpty())
	{
		throw new EmptyTreeException(" Tree is Empty ");
		
	}
	//Node boss= find(root, manageid);
	//Node employee= find(root, id);
	
	Node boss= find(head, manageid);
	Node employee= find(head, id);
	
	if(boss==null || employee==null || employee.parent==null)
	{
		throw new IllegalIDException(" ID Illegal ");
	}
	Node parent=employee.parent;
	for(int i=0; i<employee.children.size(); i++)
	{
		boss.children.add(employee.children.get(i));
		employee.children.get(i).parent=boss;
	}
	parent.children.remove(employee);
	size=size-1;
	delete(employee);
	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
} 

public int boss(int id) throws IllegalIDException,EmptyTreeException{
	//your implementation
	if(isEmpty())
	{
		throw new EmptyTreeException(" Tree is Empty ");
		
	}
	//Node employee= find(root, id);
	
	Node employee= find(head, id);
	
	if(employee==null)
	{
		throw new IllegalIDException("ID is Illegal");
	}
	if(employee.parent==null)
	{
		return -1;
	}
	Node Boss=employee.parent;
	return Boss.id;
	
	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

public int lowestCommonBoss(int id1, int id2) throws IllegalIDException,EmptyTreeException{
	//your implementation
	if(isEmpty())
	{
		throw new EmptyTreeException("Empty Tree");
		
	}
	//Node node1= find(root, id1);
	//Node node2=find(root, id2);
	
	Node node1= find(head, id1);
	Node node2=find(head, id2);
	
	
	if (node1==null || node2==null)
	{
		throw new IllegalIDException("Illegal Id");
	}
	Node node;
	node=null;
	//int depth1=level(id1);
	//int depth2=level(id2);
	int depth1= node1.level;
	int depth2=node2.level;
	int differ= depth1-depth2;
	if(differ<0)
	{ node= node1;
	  node1=node2;
	  node2=node;
	  differ= -1*differ;
	}
	//differ=differ-1; 
	while(differ !=0)
	{
		differ=differ-1;
		node1=node1.parent;
	}
	while(node1!=null && node2 != null)
	{
		if(node1==node2)
		{
			return node1.id;
		}
		node1=node1.parent;
		node2=node2.parent;
	}
	return -1; 
	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

public String toString(int id) throws IllegalIDException, EmptyTreeException{
	//your implementation
	if(isEmpty())
	{
		throw new EmptyTreeException("Tree is Empty");
    }
	
	//Node node= find(root, id);
	
	Node node= find(head, id);
	
	if (node==null)
	{
		throw new IllegalIDException("Illegal ID");
	}
	
	Vector <Node> Main=new Vector<Node>();
	Main.add(node);
	String s= "  ";
	String tostring= "  ";
	
	
	
	while(Main.size()!=0)
	{int n=Main.size();
	   Vector<Integer> str= new Vector<Integer>();
	   while(n>0)
	   {
		Node temp=Main.get(0);
		Main.remove(Main.get(0));
		
		
		str.add(temp.id);
		Collections.sort(str);
	
		tostring= str.toString();
		
		
		
		
		
		
		
		
		
		for(int i=0; i<temp.children.size(); i++)
		{
		 Main.add(temp.children.get(i));
		 
		}
		
		
		
		n=n-1;
		
		
	   }
	   tostring=tostring.replaceAll("\\s", "");
	   tostring= tostring.replaceAll(",", " ");
	   s=s + tostring.substring(1, tostring.length()-1);
	   
	  
	  
	  s=s.trim(); 
	  s=s+ ",";
	}
	
	return s.substring(0, s.length()-1);
	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

}
