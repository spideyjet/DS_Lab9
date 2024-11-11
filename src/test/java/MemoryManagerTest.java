import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MemoryManagerTest {

	
	@Test
	public void Setup() 
	{
	MemoryManager A = new MemoryManager(100);
	
	
	}
	
	@Test
	public void MemoryManagerTesting()
	{
		MemoryManager A = new MemoryManager(100);

		MemoryAllocation Amy = A.requestMemory(10, "Amy");
		MemoryAllocation Justin = A.requestMemory(20, "Justin");
		MemoryAllocation Jerry = A.requestMemory(70, "Jerry");
		MemoryAllocation Jackson = A.requestMemory(15, "Jackson");
		
		
		
		assertEquals("Amy", Amy.getOwner());
		assertEquals(10, Amy.getLength());
		assertEquals(0,Amy.getPosition());
		
		assertEquals("Justin", Justin.getOwner());
		assertEquals(20, Justin.getLength());
		assertEquals(10,Justin.getPosition());
		
		assertEquals("Jerry", Jerry.getOwner());
		assertEquals(70, Jerry.getLength());
		assertEquals(30,Jerry.getPosition());
		
		assertNull(Jackson);
		
		A.returnMemory(Amy);
		
		MemoryAllocation Garrett = A.requestMemory(10, "Garrett");
		
		assertEquals("Garrett", Garrett.getOwner());
		assertEquals(10, Garrett.getLength());
		assertEquals(0,Garrett.getPosition());
		
		assertEquals("Justin", Justin.getOwner());
		assertEquals(20, Justin.getLength());
		assertEquals(10,Justin.getPosition());
		
		assertEquals("Jerry", Jerry.getOwner());
		assertEquals(70, Jerry.getLength());
		assertEquals(30,Jerry.getPosition());
		
		A.returnMemory(Justin);
		A.returnMemory(Jerry);
		
		MemoryAllocation Chet = A.requestMemory(50, "Chet");
		MemoryAllocation Nick = A.requestMemory(10, "Nick");
		
		assertEquals("Garrett", Garrett.getOwner());
		assertEquals(10, Garrett.getLength());
		assertEquals(0,Garrett.getPosition());
		
		assertEquals("Chet", Chet.getOwner());
		assertEquals(50, Chet.getLength());
		assertEquals(10,Chet.getPosition());
		
		assertEquals("Nick", Nick.getOwner());
		assertEquals(10, Nick.getLength());
		assertEquals(60,Nick.getPosition());
		
		A.returnMemory(Chet);
		A.returnMemory(Nick);
		
		MemoryAllocation Avery = A.requestMemory(91, "Avery");
		MemoryAllocation Aden = A.requestMemory(90, "Aden");
		
		assertEquals("Garrett", Garrett.getOwner());
		assertEquals(10, Garrett.getLength());
		assertEquals(0,Garrett.getPosition());
		
		assertNull(Avery);
		
		assertEquals("Aden", Aden.getOwner());
		assertEquals(90, Aden.getLength());
		assertEquals(10,Aden.getPosition());
		
	}

}
