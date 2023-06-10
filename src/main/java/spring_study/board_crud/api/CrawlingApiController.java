package spring_study.board_crud.api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CrawlingApiController {

    @GetMapping("/api/h1crawling")
    public String geth1Data() {
        try {
            // 크롤링할 사이트의 URL 설정
            String url = "https://example.com/";
            
            // jsoup을 사용하여 웹 페이지에 접근하여 데이터 크롤링
            Document doc = Jsoup.connect(url).get();
            
            // 원하는 데이터 추출 (예시: 제목 요소 추출)
            Elements titleElement = doc.select("h1");
            String title = titleElement.text();
            
            // API 응답으로 반환할 데이터 형식 구성 (예시: JSON 형식)
            String jsonData = "{ \"title\": \"" + title + "\" }";
            
            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during data crawling.";
        }
    }

        @GetMapping("/api/pcrawling")
    public String getpData() {
        try {
            // 크롤링할 사이트의 URL 설정
            String url = "https://example.com/";
            
            // jsoup을 사용하여 웹 페이지에 접근하여 데이터 크롤링
            Document doc = Jsoup.connect(url).get();
            
            // 원하는 데이터 추출 (예시: 제목 요소 추출)
            Elements titleElement = doc.select("p");
            String title = titleElement.text();
            
            // API 응답으로 반환할 데이터 형식 구성 (예시: JSON 형식)
            String jsonData = "{ \"title\": \"" + title + "\" }";
            
            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during data crawling.";
        }
    }
}