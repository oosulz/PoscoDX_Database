package emaillist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmaillistDaoTest {
	private static Long count = 0l;

	@BeforeAll
	public static void setup() {
		count = new EmaillistDao().count();
	}
	
	@Test
	@Order(1)
	public void insertTest() {
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName("둘");
		vo.setLastName("리");
		vo.setEmail("doolyWgmail.com2");

		boolean result = new EmaillistDao().insert(vo);

	
		assertTrue(result);

	}
	@Test
	@Order(2)
	public void findAllTest() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		assertEquals(count+1,list.size());
	}
	
	@Test
	@Order(3)
	public void deleteByEmailTest() {
		boolean result = new EmaillistDao().deleteByEmail("doolyWgmail.com2");
		assertTrue(result);
	}
	

	

}
