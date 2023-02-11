//package reservation;
//
//import java.util.Scanner;
//
//public class Menu {
//	private ReservationService rs;
//	
//	public Menu() {
//		rs = new ReservationService();
//	}
//	
//	public void run(Scanner sc) {
//		boolean flag=true;
//		int m = 0;
//		while(flag) {
//			System.out.println("1.영화 제목으로 예약 2.상영관으로 예약 3.예약정보 확인 4.예약취소 5.종료");
//			m = sc.nextInt();
//			switch(m) {
//			case 1:
//				rs.reserveByMovie(sc);
//				break;
//			case 2:
//				rs.reserveByTheater(sc);
//				break;
//			case 3:
//				rs.printReservation(sc);
//				break;
//			case 4:
//				rs.cancelReservation(sc);
//				break;
//			case 5:
//				flag=false;
//				break;
//			}
//		}
//	}
//	
//}
