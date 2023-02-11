package theater;

import java.util.ArrayList;
import java.util.Scanner;
import seat.SeatDao;

public class TheaterService {
	private TheaterDao dao;
	private SeatDao sdao;

	public TheaterService() {
		dao = new TheaterDao();
		sdao = new SeatDao();
	}

	// 상영관 추가
	// TODO : 상영관명 중복 확인, 정원 수정 어떻게 할것인지 고민 s
	public void insert(Scanner sc) {
		System.out.println("===== 상영관 추가 =====");
		System.out.print("상영관 이름 입력 :");
		String name = sc.next();
		System.out.print("상영관 정원 입력 :");
		int max = sc.nextInt();
		System.out.print("잔여석 입력(정원과같은 숫자 입력) :"); // 굳이 입력안하게해도되는
		int remain = sc.nextInt();
		System.out.print("한달에 하루만 여는 특별 상영관의 영화 상영 날짜(yyyy-mm-dd 형식입력 :");
		String r_date = sc.next();
		System.out.print("한달에 하루만 여는 특별 상영관의 영화 상영 시간 :");
		String r_time = sc.next();
		TheaterVo tVo = new TheaterVo(0, name, max, remain);
		dao.insertTheater(tVo);
		sdao.insert(tVo.getTheaterN(), r_date, r_time);
	}

	// 상영관 삭제
	public void delete(Scanner sc) {
		System.out.println("===== 상영관 삭제 =====");

		ArrayList<TheaterVo> list = dao.selectAllTheater();
		boolean flag = true;
		while (flag) {
			if (list.isEmpty()) {
				System.out.println("현재 등록된 상영관이 없습니다.");
				flag = false;
			} else {
				for (TheaterVo vo : list) {
					System.out.println(vo);
				}

				System.out.print("삭제할 상영관 번호 입력 :");

//				String name = sc.next();
				int name = sc.nextInt();

				System.out.print("삭제할 상영관 번호 입력:");
				dao.deleteTheater(name);
				break;
			}
		}
	}

	// 상영관 정보조회
	public void infoSeat(Scanner sc) {
		System.out.println("===== 상영관 현황 조회(전체좌석/예약된좌석/예약가능한좌석수 =====");
		ArrayList<TheaterVo> list = dao.selectAllTheater();
		boolean flag = true;
		while (flag) {
			if (list.isEmpty()) {
				System.out.println("현재 등록된 상영관이 없습니다.");
				break;
			}
			for (TheaterVo vo : list) {
				System.out.println(vo);
			}
			System.out.print("조회할 상영관 번호 입력 :");

			int num = sc.nextInt();

			int total = dao.totalSeat(num);   

			if (total == 0) {
				System.out.println("해당 상영관은 등록되지 않았습니다. 먼저 등록해주세요.");
			} else {
				String str = dao.remainSeat(num);  //리메인시트자체가 abcde다..합친거니
				int len = str.replaceAll("1", "").length();  //1이없어진만큼 나온길이수.. 1좌석 예약됐으면 0은 1개 줄어드니 80-1 =79
				int resev = total - len;
				int rem = total - resev;
				System.out.println(num + "번 상영관의 전체좌석수 : " + total);
				System.out.println(num + "번 상영관의 예약된좌석수 : " + resev);
				System.out.println(num + "번 상영관의 예약가능한 좌석수 : " + rem);
				break;
			}
		}
	}
}
