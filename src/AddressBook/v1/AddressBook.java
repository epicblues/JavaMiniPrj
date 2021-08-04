package AddressBook.v1;

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

public class AddressBook {

	public static final String TARGET = System.getProperty("user.dir") + "\\files\\members.txt";

	public static void main(String[] args) throws IOException {
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("**************************************************");
		System.out.println("*                전화번호 관리 프로그램                *");

		System.out.println("**************************************************");
		boolean on = true;
		while (on) {

			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(TARGET)));

			List<Member> list = new LinkedList<>();

			String name = null, hp = null, tel = null;

			while (true) { // 파일 읽어서 list에 등록하기.
				try {
					StringTokenizer st = new StringTokenizer(br.readLine(), ",");
					name = st.nextToken();
					hp = st.nextToken();
					tel = st.nextToken();
					list.add(new Member(name, hp, tel));
				} catch (NullPointerException e) {
					break;
				}

			}

		
			System.out.println("**************************************************");
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
			System.out.println("--------------------------------------------------");
			System.out.print("메뉴번호: ");
			int select = Integer.parseInt(console.readLine());

			switch (select) {
			case 1:
				
				for (int i = 0; i < list.size(); i++) {
					System.out.printf("%d. %s\t%s\t%s%n", i+1, list.get(i).getName(), list.get(i).getHp(),
							list.get(i).getTel());
					
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
				list.add(new Member(name, hp, tel));
				System.out.println("\n[등록되었습니다.]");
				
				System.out.println();
				break;
			case 3:
				System.out.println("<삭제>");
				System.out.print(">번호: ");
				int delNum = Integer.parseInt(console.readLine());
				list.remove(delNum - 1);
				System.out.println("[삭제되었습니다.]");
				
				System.out.println();
				break;
			case 4:
				System.out.println("<4. 검색>");
				System.out.print(">이름: ");
				String inputName = console.readLine();
				int index = 1;
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getName().contains(inputName)) {
						System.out.printf("%d. %s\t%s\t%s%n", index, list.get(i).getName(), list.get(i).getHp(),
								list.get(i).getTel());
						index++;
					}
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

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(TARGET, false)));
			for (Member member : list) {

				if (list.indexOf(member) == list.size() - 1) {
					bw.write(member.getName() + "," + member.getHp() + "," + member.getTel());
				} else {
					bw.write(member.getName() + "," + member.getHp() + "," + member.getTel() + "\n");
				}
			}

			bw.flush();
			bw.close();
			br.close();
		}
		

	}

}
