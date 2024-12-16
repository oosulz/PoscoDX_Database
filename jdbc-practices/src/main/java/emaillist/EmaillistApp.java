package emaillist;

import java.util.List;
import java.util.Scanner;

public class EmaillistApp {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		while (true) {
			System.out.print("(l)ist (d)elete (i)nsert (q)uit > ");
			String command = scanner.nextLine();

			if ("l".equals(command)) {
				doList();
			} else if ("d".equals(command)) {
				doDelete();
			} else if ("i".equals(command)) {
				doInsert();
			} else if ("q".equals(command)) {
				break;
			}
		}
	}

	private static void doInsert() {
		System.out.print("성: ");
		String firstName = scanner.nextLine();

		System.out.print("이름: ");
		String lastName = scanner.nextLine();

		System.out.print("이메일:");
		String email = scanner.nextLine();

		EmaillistVo vo = new EmaillistVo();
		vo.setEmail(email);
		vo.setFirstName(firstName);
		vo.setLastName(lastName);

		boolean result = new EmaillistDao().insert(vo);

		doList();

	}

	private static void doDelete() {
		System.out.println("이메일:");
		String email = scanner.nextLine(); // 입력값에서 공백 제거
		if (email.isEmpty()) {
			System.out.println("유효한 이메일을 입력하세요.");
			return;
		}

		boolean result = new EmaillistDao().deleteByEmail(email);
		if (result) {
			System.out.println("삭제 성공!");
		} else {
			System.out.println("삭제할 이메일을 찾을 수 없습니다.");
		}
		doList();
	}

	private static void doList() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		for (EmaillistVo vo : list) {
			System.out.println(vo.getFirstName() + " " + vo.getLastName() + " " + vo.getEmail());
		}

	}
}
