package spring_study.board_crud.api;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//지금 이거 새벽 3시에 작성하고 있답니다.
//크롬 드라이버입니다. 버전이 바뀔 때 마다 크롬 드라이버 새로 깔아줘야 합니다.
//와 매우 개떡같은 모듈이에요 근데 이거 아니면 동적 웹 크롤링을 못한데요.
//이런 시부랄 현재 버전은 ChromeDriver 114.0.5735.90 버전을 사용하고 있어요.

@RestController
public class SeleniumApiController {

    @GetMapping("/api/crawling")
    public ResponseEntity<List<String>> crawlData() {
        // 크롤링 작업 수행
        List<String> crawledData = performCrawling();

        // 프론트엔드에 데이터 전달
        return ResponseEntity.ok(crawledData);
    }

    private List<String> performCrawling() {

        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
        // Selenium을 사용하여 크롤링 로직 작성
        WebDriver driver = new ChromeDriver();
        try {
            // 크롤링할 사이트 접속
            driver.get("https://example.com");

            // 필요한 요소를 식별하여 데이터 크롤링
            WebElement element = driver.findElement(By.cssSelector("body > div > h1']"));
            String data = element.getText();

            // 크롤링한 데이터 반환
            List<String> crawledData = new ArrayList<>();
            crawledData.add(data);

            return crawledData;
        } finally {
            // 크롤링이 끝나면 WebDriver를 종료
            driver.quit();
        }
    }
}
