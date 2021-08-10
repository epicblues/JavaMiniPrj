package AddressBook.v2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class PhoneBook {

	public static void main(String[] args) throws IOException {
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("**************************************************");
		System.out.println("*                전화번호 관리 프로그램                *");

		System.out.println("**************************************************");
		boolean on = true;
		while (on) {

			// PhonㄷBookVO 리스트를 받아서 처리한다.
			PhoneBookDAO dao = new PhoneBookDAOImpl();
			List<PhoneBookVO> list = null;
			Iterator<PhoneBookVO> iter = null;
			String name = null, hp = null, tel = null;
			
			System.out.println("**************************************************");
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
			System.out.println("--------------------------------------------------");
			System.out.print("메뉴번호: ");
			int select = Integer.parseInt(console.readLine());

			switch (select) {
			case 1:
				
				dao = new PhoneBookDAOImpl();
				list = dao.getList();
				iter = list.iterator();

				while (iter.hasNext()) {
					PhoneBookVO vo = iter.next();
					System.out.printf("%d. %s\t%s\t%s%n", vo.getId(), vo.getName(), vo.getHp(), vo.getTel());
				}

				System.out.println();
				break;
			case 2:

				System.out.println("<등록>");
				System.out.print(">이름: ");
				name = console.readLine();
				System.out.print(">휴대전화: ");
				hp = console.readLine();
				System.out.print(">집전화: ");
				tel = console.readLine();
				PhoneBookVO vo = new PhoneBookVO(name, hp, tel);
				boolean success = dao.insert(vo);

				System.out.println(success ? "\n[등록되었습니다.]" : "\n [등록 실패!]");

				System.out.println();
				break;
			case 3:
				System.out.println("<삭제>");
				System.out.print(">번호: ");
				Long delNum = Long.parseLong(console.readLine());
				boolean delSuccess = dao.delete(delNum);
				System.out.println(delSuccess ? "[삭제되었습니다.]" : "[삭제 실패!]");
				System.out.println();
				break;
			case 4:
				System.out.println("<4. 검색>");
				System.out.print(">이름: ");
				String inputName = console.readLine();

				list = dao.search(inputName);
				if (list.size() == 0) {
					System.out.println("검색된 레코드가 없습니다.");
					break;
				}

				iter = list.iterator();

				while (iter.hasNext()) {
					PhoneBookVO searchedVO = iter.next();
					System.out.printf("%d. %s\t%s\t%s%n", searchedVO.getId(), searchedVO.getName(), searchedVO.getHp(),
							searchedVO.getTel());
				}

				System.out.println();
				break;
			case 5:
				on = false;

				System.out.println("**************************************************");
				System.out.println("*                     감사합니다                    *");
				System.out.println("**************************************************");
				break;
			default:
				System.out.println("[다시 입력해주세요]");
			}

		}

	}

}
