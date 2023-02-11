package seat;

import java.util.Scanner;

public class SeatService {
	private SeatDao dao;

	public SeatService() {
		dao = new SeatDao();
	}

	// 영화예약 이건 사용 안했다.
	public void reserve(Scanner sc) {
		System.out.println("영화 예약");
		// TODO //상영중인 영화정보 출력하고 입력받기(switch문) 필요할듯
		System.out.print("상영관 입력");
		int thN = sc.nextInt();
		System.out.print("상영날짜 입력");
		String scday = sc.next();
		System.out.print("상영시간 입력");
		String sctime = sc.next();
		System.out.print("상영관 열 입력");
		String row = sc.next();
		String val = dao.rowValue(row, thN, scday, sctime); // 열/상영관/상영날짜/상영시간
		System.out.println(val);
		System.out.println("좌석을 선택하시오");
		int s = sc.nextInt();
		while (val.charAt(s - 1) == '1') {
			System.out.println("이미 예약된 좌석. 다시 입력하시오");
			s = sc.nextInt();
		}
		byte[] code = val.getBytes();
		code[s - 1] = '1';
		String newVal = new String(code);
		System.out.println("선택하신 좌석은 " + row + "열 " + s + "번 좌석입니다. ");
		System.out.println(newVal);
		dao.update(row, newVal, scday, sctime, thN);
//		dao.update(row, newVal, thN);
		System.out.println("예약이 완료되었습니다.");
	}
}
