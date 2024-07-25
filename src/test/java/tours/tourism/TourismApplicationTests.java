package tours.tourism;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import tours.tourism.user.ChangePasswordRequestDto;
import tours.tourism.user.CreateRequestDto;
import tours.tourism.user.LoginRequestDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TourismApplicationTests {

	@LocalServerPort
	int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@Test
	void 비밀번호_변경_성공() {
		// given
		String 이메일 = "doraemon@gmail.com";
		String 기존_비밀번호 = "dora123";
		String 새_비밀번호 = 기존_비밀번호 + "xxx";
		RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.body(new CreateRequestDto(이메일, "도라에몽", 기존_비밀번호))
				.when()
				.post("/join")
				.then().log().all()
				.statusCode(200);

		String accessToken = RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.body(new LoginRequestDto(이메일, 기존_비밀번호))
				.when()
				.post("/login")
				.then().log().all()
				.statusCode(200)
				.extract()
				.jsonPath()
				.getString("accessToken");

		// when
		RestAssured.given().log().all()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
				.contentType(ContentType.JSON)
				.body(new ChangePasswordRequestDto(기존_비밀번호, 기존_비밀번호,새_비밀번호))
				.when()
				.patch("/password")
				.then().log().all()
				.statusCode(200);

		// then
		RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.body(new LoginRequestDto(이메일, 기존_비밀번호))
				.when()
				.post("/login")
				.then().log().all()
				.statusCode(500);

		RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.body(new LoginRequestDto(이메일, 새_비밀번호))
				.when()
				.post("/login")
				.then().log().all()
				.statusCode(200);
	}



}
