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
	   head = new MemoryAllocation(Free, 0, size);
   }



    /**
       takes the size of the requested memory and a string of the process requesting the memory
       returns a memory allocation that satisfies that request, or
       returns null if the request cannot be satisfied.
     */
    
   public MemoryAllocation requestMemory(long size,String requester)
   {
      MemoryAllocation curr = head;
      
      while(curr!= null)
      {
    	  if(curr.getOwner().equals(Free) && curr.getLength() >= size)
    	  {
    		  if(curr.getLength() > size)
    			  {
    			  MemoryAllocation allocated = new MemoryAllocation(requester, curr.getPosition(), size);
    			  MemoryAllocation remainingFree = new MemoryAllocation(Free, curr.getPosition() + size, curr.getLength() - size);
    			  
    			  allocated.next = remainingFree;
    			  remainingFree.prev = allocated;
    			  
    			  if(curr.prev != null)
    			  {
    				  curr.prev.next = allocated;
    				  allocated.prev = curr.prev;
    			  }
    				  else
    				  {
    					  head = allocated;
    				  }
    			  remainingFree.next = curr.next;
    			  if (curr.next != null)
    			  {
    				  curr.next.prev = remainingFree;
    			  }
    			  return allocated;
    			  }
    		  else
    		  {
    			 MemoryAllocation allocated = new MemoryAllocation(requester, curr.getPosition(), size);
    			 
    			 if(curr.prev != null)
    			 {
    				 curr.prev.next = allocated;
    			 }
    			 else
    			 {
    				 head = allocated;
    			 }
    			 allocated.prev = curr.prev;
    			 
    			 if(curr.next != null)
    			 {
    				 curr.next.prev = allocated;
    			 }
    			 allocated.next = curr.next;
    			 
    			 return allocated;
    		  }
    			  
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
	   MemoryAllocation freeMem = new MemoryAllocation(Free, mem.getPosition(), mem.getLength());
	   
	   freeMem.prev = mem.prev;
	   freeMem.next = mem.next;
	   
	   if(mem.prev != null)
	   {
		   mem.prev.next = freeMem;
	   }
	   else
	   {
		   head = freeMem;
	   }
	   
	   if(mem.next != null)
	   {
		   mem.next.prev = freeMem;
	   }
	   
	   recursiveMergeAdjacent(freeMem);
   }


	private void recursiveMergeAdjacent(MemoryAllocation freeMem) 
	{
		if (freeMem.prev != null && freeMem.prev.getOwner().equals(Free))
			{
			freeMem = new MemoryAllocation(Free, freeMem.prev.getPosition(), freeMem.prev.getLength() + freeMem.getLength());
			
			freeMem.prev = freeMem.prev.prev;
			
			if (freeMem.prev != null)
			{
				freeMem.next.prev = freeMem;
			}
			else
			{
				head = freeMem;
			}
			
			if(freeMem.next != null)
			{
				freeMem.next.prev = freeMem;
			}
			}
			if ((freeMem.prev != null && freeMem.prev.getOwner().equals(Free))
					|| (freeMem.next != null && freeMem.next.getOwner().equals(Free)))
			{
				recursiveMergeAdjacent(freeMem);
			}
		
	}
    



}
