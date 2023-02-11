

package reservation;

import java.util.ArrayList;
import java.util.Scanner;

import member1.Member1;
import member1.Member1Service;
import movie.MovieDao;
import movie.MovieService;
import movie.MovieVo;
import seat.SeatDao;

public class ReservationService {

	private MovieDao movDao;
	private SeatDao sdao;
	private ReservationDao dao;
	private MovieService movService;

	public ReservationService() {
		dao = new ReservationDao();
		sdao = new SeatDao();
		movDao = new MovieDao();
		movService = new MovieService();
	}

	// 영화 예매시 쓰일 영화 번호, 좌석 입력하는 method
	public void reserve(Scanner sc) {
		System.out.println("예매할 영화의 번호를 입력하시오");
		int movieId = sc.nextInt();
		MovieVo mVo = movDao.selectByNum(movieId);
		int theaterN = mVo.getTheaterN();
		String[] runDate = mVo.getRunD().split(" ", 0);
		for (String a : runDate) {
			System.out.println(a);
		}
	    //공백기준으로 다나눠서 배열에다가 담을것. 2022-11-02 00:00 date타입때문에)
		String runD = runDate[0].replaceAll("-", ""); //replaceAll()는 대상 문자열을 원하는 문자 값으로 변환하는 함수이다. 20221102
		String startT = mVo.getStartT();
		String rowN = "";
		int seatN = 0;
		String r = "";
		String str = "";
		boolean flag = true;
		while (flag) {
			System.out.println("좌석 열을 입력하시오(a,b,c,d,e 중 택1).");
			rowN = sc.next();
			if (!rowN.equals("a") && !rowN.equals("b") && !rowN.equals("c") && !rowN.equals("d") && !rowN.equals("e")) {
				System.out.println("존재하지 않는 열입니다. 다시 입력하세요");
			} else {
				r = sdao.rowValue(rowN, theaterN, runD, startT);
				System.out.println(rowN + "열의 좌석현황(예약 가능 좌석:0, 예약 불가 좌석:1) : " + r);
				if (r.equals("1111111111111111")) {
					System.out.println("모든 자리가 예약되었습니다. 다시 선택하세요");
				} else {
					flag = false;
					break;
				}
			}
		}
		flag = true;
		while (flag) {
			System.out.println("좌석 번호를 입력하시오(1~16 중 택1).");
			seatN = sc.nextInt();
			if (seatN < 1 || seatN > 16) {
				System.out.println("존재하지 않는 좌석입니다. 다시 입력하세요");
			} else {
				r = sdao.rowValue(rowN, theaterN, runD, startT); //00000000
				char[] c = r.toCharArray();//문자열 한글자씩 배열로 만들어주는것.  [0][0] 이런식으로
				if (c[seatN - 1] == '1') {  
					System.out.println("이미 예약된 좌석입니다. 다시 입력하세요");
				} else {
					c[seatN - 1] = '1'; //예약됐으면 1로 바꿔줘야하니깐
					for (int i = 0; i < c.length; i++) {
						str += c[i] + "";
					}
					System.out.println(str);
					flag = false;
				}
			}
		}
		ReservationVo vo = new ReservationVo(0, movieId, Member1Service.loginId, theaterN, runD, startT, rowN, seatN);
		dao.insert(vo);
		sdao.update(rowN, str, runD, startT, theaterN); //???
//		sdao.update(rowN, str, theaterN);
		System.out.println("예약 완료");
	}

	// 영화 예매1
	// 1. MovieService class에서 영화 제목으로 검색하는 method 가져와서 원하는 영화 리스트 출력
	// 2. MovieId 입력 후 좌석 정해서 예약 완료
	public void reserveByMovie(Scanner sc) {
		if (Member1Service.loginId.equals(null)) {
			System.out.println("비회원은 예약 불가. 상영 중인 영화 목록 확인만 가능");
		} else {
			if (!movService.readByTitle(sc)) {
				System.out.println("해당 키워드를 가진 영화가 없습니다.");
			} else {
				reserve(sc);
			}
		}
	}

	// 영화 예매2
	// 1. MovieService class에서 상영관 검색하는 method 가져와서 해당 장소에서 상영 중인 영화 리스트 출력
	// 2. MovieId 입력 후 좌석 정해서 예약 완료
	public void reserveByTheater(Scanner sc) {
		if (Member1Service.loginId.equals(null)) {
			System.out.println("비회원은 예약 불가. 상영 중인 영화 목록 확인만 가능");
		} else {
			if (!movService.readByTheater(sc)) {
				System.out.println("존재하지 않는 상영관입니다.");
			} else {
				reserve(sc);
			}
		}
	}

	// 티켓 번호로 예약 조회
	public void printReservation() {
		System.out.println("====예약 확인====");
		String loginId = Member1Service.loginId;
		if (loginId == null) {
			System.out.println("로그인이 필요합니다");
		} else {
			ArrayList<ReservationVo> list = dao.select(loginId);

			if (list.isEmpty()) {
				System.out.println("예약 정보가 없습니다");
			} else {
				for (ReservationVo vo : list) {
					System.out.println(vo);
				}
			}
		}
	}

	// 예약 취소
	public void cancelReservation(Scanner sc) {
		System.out.println("<<예약 취소>>");
		String loginId = Member1Service.loginId;
		if (loginId == null) {
			System.out.println("로그인이 필요합니다");
		} else {
			ArrayList<ReservationVo> list = dao.select(loginId);

			if (list.isEmpty()) {
				System.out.println("예약 정보가 없습니다");
			} else {
				for (ReservationVo v : list) {
					System.out.println(v);
				}
				System.out.println("취소할 티켓 번호 입력:");
				int ticketN = sc.nextInt();
				ReservationVo vo = dao.selectByNum(ticketN);
				if (vo == null) {
					System.out.println("티켓 번호를 찾을 수 없습니다");
				} else {
					String[] runDate = vo.getRunD().split(" ", 0);
					String runD = runDate[0].replaceAll("-", "");
					String r = sdao.rowValue(vo.getRowN(), vo.getTheaterN(), runD, vo.getStartT());
					System.out.println("==========선택된 예약 정보==========");
					System.out.println(vo);
					System.out.println("예약을 취소하시겠습니까? 1.예 2.아니오");
					int num = sc.nextInt();
					switch (num) {
					case 1:
						dao.delete(ticketN);
						char[] c = r.toCharArray();
						c[vo.getSeatN() - 1] = '0';
						String str = "";
						for (int i = 0; i < c.length; i++) {
							str += c[i] + "";
						}
						System.out.println(vo.getRowN() + "열의 좌석현황(잔여좌석:0, 예약좌석:1) : " + str);
						sdao.update(vo.getRowN(), str, runD, vo.getStartT(), vo.getTheaterN());
//						sdao.update(vo.getRowN(), str, vo.getTheaterN());
						System.out.println("취소 완료");
						break;
					case 2:
						System.out.println("취소되지 않았습니다");
						break;
					default:
						System.out.println("잘못된 입력. 취소되지 않았습니다");
						break;
					}
				}
			}
		}
	}
}

// 상영관선택
// 영화선택
// 시간대선택
// 좌석선택
// syso "예약하시겠습니까?" y/n "예약되었습니다"
