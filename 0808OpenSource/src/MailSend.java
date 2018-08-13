import org.apache.commons.mail.SimpleEmail;

public class MailSend {

	public static void main(String[] args) {
		try {
			//텍스트 메일을 보낼 수 있는 클래스의 객체 만들기
			SimpleEmail email = new SimpleEmail();
			
			//서버설정
			email.setAuthentication("dltmdghks94","hwan55911");
			email.setHostName("smtp.naver.com");
			email.setSmtpPort(587);
			//메일 보안 설정 옵션 : 메일이 암호화 되서 전송됩니다.
			email.setTLS(true);
			email.setSSL(true);
			email.setFrom("dltmdghks94@naver.com","관리자");
			email.setCharset("utf-8");
			
			//받는설정
			email.addTo("dltmdghks94@naver.com");
			email.setSubject("메일보내기연습");
			email.setMsg("메일이 갈까 ??");
			
			//보내기
			email.send();
			System.out.println("메일 보내기 성공");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
