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
	   
	   if(freeMem.prev != null && freeMem.prev.getOwner().equals(Free))
	   {
		   MemoryAllocation difFreeMem = new MemoryAllocation(Free, freeMem.prev.getPosition(), freeMem.prev.getLength() + freeMem.getLength());
		   difFreeMem.prev = freeMem.prev.prev;
		   difFreeMem.next = freeMem.next;
		   
		   
		   if (difFreeMem.prev != null)
		   {
			   difFreeMem.prev.next = difFreeMem;
		   }
		   else
		   {
			   head = difFreeMem;
		   }
		   difFreeMem.next = mem.next;
		   
		   if(mem.next != null)
		   {
			   mem.next.prev = difFreeMem;
		   }
		   
		   freeMem = difFreeMem;
	   }
		   
		   if(freeMem.next != null && freeMem.next.getOwner().equals(Free))
		   {
			   MemoryAllocation morefreeMem = new MemoryAllocation(Free, freeMem.getPosition(), freeMem.getLength() + freeMem.next.getLength());
			   morefreeMem.next = freeMem.next.next;
			   morefreeMem.prev = freeMem.prev;
			   
			   if(morefreeMem.prev != null)
			   {
				   morefreeMem.prev.next = morefreeMem;
			   }
			   else
			   {
				   head = morefreeMem;
			   }
			   
			   if(morefreeMem.next != null)
			   {
				   morefreeMem.next.prev = morefreeMem;
				   
			   }
			   freeMem = morefreeMem;
		   }
		   
	   }
   }
    




