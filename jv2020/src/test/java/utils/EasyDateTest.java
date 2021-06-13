import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


public class EasyDateTest {
	
	private static EasyDate easyDateTest1;
	private EasyDate easyDateTest2;
	
	@BeforeAll
	public static void iniciarlizeGlobalData() {
		easyDateTest1 = new EasyDate(2021, 1, 1, 12, 0, 0);
	}
	
	@BeforeEach
	public void initializeTestData() {	
		this.easyDateTest2 = new EasyDate();
	}
	
	@AfterEach
	public void clearTestData() {
		this.easyDateTest2 = null;
	}
	
	@AfterAll
	public static void clearGlobalData() {
		easyDateTest1 = null;
	}
	
	// Test con datos validos
	
	@Test
	public void isAfterTest() {
		assertEquals(false, easyDateTest1.isAfter(easyDateTest2));
	}
	
	@Test
	public void isBeforeTest() {
		assertEquals(true, easyDateTest1.isBefore(easyDateTest2));
	}
	
	@Test
	public void plusYearsTest() {
		assertEquals(2022, easyDateTest1.plusYears(1).getYear());
	}
	
	@Test
	public void minusYearsTests() {
		assertEquals(2020, easyDateTest1.minusYears(1).getYear());
	}
	
	
	
	
	
	
	
	
	
	
	
	