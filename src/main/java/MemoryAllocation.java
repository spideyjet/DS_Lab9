public class MemoryAllocation
{
    //package access-reachable by memory manager
    //but not those that would use this module
    String owner;  //which process owns this memory
    long pos;      //where does it start
    long len;      //how long is the memory
    MemoryAllocation next;
    MemoryAllocation prev;



    //You might want to add additional data/methods here


    //feel free to alter the constructor if you need/want to
    public MemoryAllocation(String owner, long pos, long len)

    {
	this.owner = owner;
	this.pos = pos;
	this.len=len;
	this.next = null;
	this.prev = null;
    }
    
    

    public String getOwner()
    {
	return owner;
    }

    public long getPosition()
    {
	return pos;
    }

    public long getLength()
    {
	return len;
    }
    


    public String toString()
    {
	return "Alloc "+owner+" at "+pos+" for "+len; 
    }

}
