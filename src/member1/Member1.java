package member1;
//비밀번호를 마음대로 get하면 안될 것 같아서
//비밀번호에 대한 get함수는 만들면 안된다던데..?여기서는 그냥?하던대로..?
//hr계정으로 만들면 안되나..?

public class Member1 {
	// 변수 설정
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String tel;

	// 디폴트 생성자
	public Member1() {

	}

	public Member1(String id, String pwd, String name, String email, String tel) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.tel = tel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return " 회원정보[아이디: " + id + ", 비밀번호: " + pwd + ", 이름: " + name + ", 이메일: " + email + ", 전화번호: " + tel + "]";
	}

}
