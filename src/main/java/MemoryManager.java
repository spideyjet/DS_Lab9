public class MemoryManager
{
   protected MemoryAllocation head;
    
   protected final String Free = "Free";


    /* size- how big is the memory space.  
     *  Memory starts at 0
     *
     */
   public MemoryManager(long size)
   {
	   MemoryManager Free = new MemoryManager(100);
   }



    /**
       takes the size of the requested memory and a string of the process requesting the memory
       returns a memory allocation that satisfies that request, or
       returns null if the request cannot be satisfied.
     */
    
   public MemoryAllocation requestMemory(long size,String requester)
   {
      MemoryAllocation curr = head;
      
      while(curr!= null);
      {
    	  if(curr.getOwner().equals(Free) && curr.getLength() >= size)
    	  {
    		  long remainingSize = curr.getLength() - size;
    		  curr.owner = requester;
    		  curr.len = size;
    		  
    		  if(remainingSize > 0)
    		  {
    			  MemoryAllocation newFreeBlock = new MemoryAllocation(Free, curr.pos);
    			  newFreeBlock.next = curr.next;
    			  
    			  if(curr.next != null)
    			  {
    				  curr.next.prev = newFreeBlock;
    			  }
    			  curr.next = newFreeBlock;
    			  newFreeBlock.prev = curr;
    		  }
    		  return curr;
    			  
    		  }
    	  curr = curr.next;
    	  }
      return null;
   }
    		 
    		  
    		 
      
   


    
    /**
       takes a memoryAllcoation and "returns" it to the system for future allocations.
       Assumes that memory allocations are only returned once.       

     */
   public void returnMemory(MemoryAllocation mem)
   {
	   mem.owner = Free;
	   
	   if(mem.prev != null && mem.prev.getOwner().equals(Free))
	   {
		   mem.prev.len += mem.len;
		   mem.prev.next = mem.next;
		   
		   if(mem.next != null)
		   {
			   mem.next.prev = mem.prev;
		   }
		   mem = mem.prev;
	   }
	   
	   if(mem.next != null && mem.next.getOwner().equals(Free))
	   {
		   mem.len += mem.next.len;
		   mem.next = mem.next.next;
		   
		   if(mem.next != null)
		   {
			   mem.next.prev = mem;
		   }
	   }
   }
    



}
