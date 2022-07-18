package moine.domain;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class LectureCrawlingMain {
    public static void getLectureCrawlingList() {

        // 타겟 url
        // 라임 사이버 문화센터 > 취미교양 탭
        final String originURL = "https://www.dongacc.com/online/category.htm?ca_code=11";
        Connection conn = Jsoup.connect(originURL);

        try {
            Document document = conn.get();
            String thead = getTableHeader(document); // 칼럼명
            String tbody = getTableBody(document);   // 데이터 리스트

            System.out.println(thead);
            System.out.println(tbody);

        } catch (IOException ignored) {
        }
    }

    // 테이블 헤더
    // 분류 과정명 모바일 강사 수강기간 수강료 추천 맛보기 자세히
    public static String getTableHeader(Document document) {
        // table.type_2의 type_2는 함수나 속성이 아니라 개발자도구로 보면 이름 자체임. class
        Elements stockTableBody = document.select("table thead tr");
        StringBuilder sb = new StringBuilder();
        for (Element element : stockTableBody) {
            for (Element td : element.select("th")) {
                sb.append(td.text());
                sb.append("   ");
            }
            break;
        }
        return sb.toString();
    }

    public static String getTableBody(Document document) {
        Elements stockTableBody = document.select("table tbody tr");
        StringBuilder sb = new StringBuilder();
        String category = "";
        String course_url="";

        for (Element element : stockTableBody) {
//      if (element.attr("onmouseover").isEmpty()) {
//        continue;
//      }

            for (Element td : element.select("td")) {
                //
                if(!(td.attr("rowspan").isEmpty())){
                    category = td.text(); // rowspan 속성이 존재하면 분류 가져오기
                    sb.append(category);
                }

                String text="";

                if(td.select("a").attr("href").isEmpty()){
                    text = td.text();
                }else if(td.select("a").attr("href") != "#"){
                    if(course_url.isEmpty()){
                        // url : 과정명, 자세히 둘다 가지고 있음
                        text = td.text();
                        course_url = "https://www.dongacc.com/online/"+td.select("a").attr("href");

                    }

                }

                sb.append(text);

                sb.append("   ");
            }
            sb.append(course_url);
            course_url = "";
            sb.append(System.getProperty("line.separator")); //줄바꿈
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        getLectureCrawlingList();
    }

}
