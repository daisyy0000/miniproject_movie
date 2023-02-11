package theater;
public class TheaterVo {

	private int theaterN;
	private String Id; //상영관 이름
	private int totalSeat; //총 좌석수
	private int remainSeat; //잔여좌석 
	
	public TheaterVo() {}
	public TheaterVo(int theaterN, String Id, int totalSeat, int remainSeat) {
		this.theaterN = theaterN;
		this.Id = Id;
		this.totalSeat = totalSeat;
		this.remainSeat = remainSeat;
	}
	
	public int getTheaterN() {
		return theaterN;
	}
	public void setTheaterN(int theaterN) {
		this.theaterN = theaterN;
	}
	public String getId() {
		return Id;
	}
	public void setId(String Id) {
		this.Id = Id;
	}
	public int getTotalSeat() {
		return totalSeat;
	}
	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}
	public int getRemainSeat() {
		return remainSeat;
	}
	public void setRemainSeat(int remainSeat) {
		this.remainSeat = remainSeat;
	}
	@Override
	public String toString() {
		return "상영관번호:" + theaterN + "| 상영관:" + Id +"| 총좌석수:"
				+ totalSeat + "| 남은좌석수:" + remainSeat + "|";
	}
}
