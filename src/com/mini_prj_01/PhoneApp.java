package com.mini_prj_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class PhoneApp {

	public static final String TARGET = System.getProperty("user.dir") + "\\files\\members.txt";

	public static void main(String[] args) throws IOException {
		BufferedReader menu = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(TARGET)));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(TARGET, true)));
		List<Member> list = new LinkedList<>();

		String name, hp, tel;

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
		System.out.println("*                전화번호 관리 프로그램                *");

		System.out.println("**************************************************");
		System.out.println("**************************************************");
		System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
		System.out.println("--------------------------------------------------");
		System.out.print("메뉴번호: ");
		int select = Integer.parseInt(menu.readLine());

		switch (select) {
		case 1:
			list.stream().forEach(System.out::println);
			break;
		}

	}

}
