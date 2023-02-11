package seat;

public class SeatVo {
		//row : 열 0000000000000
		//arg1 : 상영관
		//arg2 : 상영날짜
		//arg3 : 상영시간
	private int seatN;
	private int TheaterN;
	private String rDate;
	private String rTime;
	private String row;
	
	public SeatVo() {}
	public SeatVo(int seatN, int theaterN, String rDate, String rTime, String row) {
		//super();
		this.seatN = seatN;
		TheaterN = theaterN;
		this.rDate = rDate;
		this.rTime = rTime;
		this.row = row;
	}
	
	
	public int getSeatN() {
		return seatN;
	}
	public void setSeatN(int seatN) {
		this.seatN = seatN;
	}
	public int getTheaterN() {
		return TheaterN;
	}
	public void setTheaterN(int theaterN) {
		TheaterN = theaterN;
	}
	public String getrDate() {
		return rDate;
	}
	public void setrDate(String rDate) {
		this.rDate = rDate;
	}
	public String getrTime() {
		return rTime;
	}
	public void setrTime(String rTime) {
		this.rTime = rTime;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	@Override
	public String toString() {
		return "SeatVo [seatN=" + seatN + ", TheaterN=" + TheaterN + ", rDate=" + rDate + ", rTime=" + rTime + ", row="
				+ row + "]";
	}
}


	
	