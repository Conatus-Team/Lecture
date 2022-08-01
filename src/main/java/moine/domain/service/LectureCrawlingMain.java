package moine.domain.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class LectureCrawlingMain {
//    @Autowired
//    private moine.domain.repository.LectureCrawlingRepository LectureCrawlingRepository;

    public static void getLectureCrawlingList() {

        // 타겟 url
        // 라임 사이버 문화센터 > 취미교양 탭
        final String originURL = "https://www.dongacc.com/online/category.htm?ca_code=11";
        Connection conn = Jsoup.connect(originURL);

//        String[][] Data;
//        Map<String, String[]> Data= new HashMap<>();


        try {
            Document document = conn.get();
//            String thead = getTableHeader(document); // 칼럼명
//            String tbody = getTableBody(document);   // 데이터 리스트


//            getDetail("https://www.dongacc.com/online/course/view.htm?mgt_no=1071"); // 강의 자세히 보기 - 강의 소개
            getDetail("https://www.dongacc.com/online/course/view.htm?mgt_no=3780"); // 강의 자세히 보기 - 강의 소개


//            System.out.println(thead);
//            System.out.println(tbody);


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

        // tr
        for (Element element : stockTableBody) {

            // tr을 하나씩 저장
            ArrayList data = new ArrayList<String>();

            // 크롤링 객체 생성
//            LectureCrawlingEntity lecture = new LectureCrawlingEntity();

            // td
            for (Element td : element.select("td")) {

                // rowspan 속성이 존재하면 분류 가져오기
                if(!(td.attr("rowspan").isEmpty())){
                    category = td.text();
                    sb.append(category);
                    continue;
                }


                String text="";
                if(td.select("a").attr("href").isEmpty()){
                    // td의 글씨
                    text = td.text();
                }else if(td.select("a").attr("href") != "#"){
                    if(course_url.isEmpty()){
                        // 과정명에 있는 url 가져오기
                        text = td.text();
                        course_url = "https://www.dongacc.com/online/"+td.select("a").attr("href");

                    }

                }

                // sb.append(text);
                data.add(text);

                // sb.append("   ");
            }
            // sb.append(course_url);
            data.add(course_url);
            data.add(category);

            course_url = "";
            //sb.append(System.getProperty("line.separator")); //줄바꿈

            System.out.println("data = " + data);

            // [0:과정명, 1:모바일, 2:강사, 3:수강기간, 4:수강료, 5:추천, 6:'', 7:'', 8:url, 9:분류]
            System.out.println("data = " + data.get(9)); // 분류
            System.out.println("data = " + data.get(0)); // 강의명
            System.out.println("data = " + data.get(2)); // 강사명
            System.out.println("data = " + data.get(8)); // url
            System.out.println("data = " + data.get(5)); // 좋아요수
//            System.out.println("data = " + data.get(4)); // 가격

            // DB 저장
//            lecture.setLecture_name((String) data.get(9));
//            lecture.setLecture_name((String) data.get(0));
//            lecture.setLecture_name((String) data.get(2));
//            lecture.setLecture_name((String) data.get(8));
//            lecture.setLecture_name((String) data.get(5));

//            LectureCrawlingRepository.save(lecture);

        }
        return sb.toString();
    }



    public static void getDetail(String detailUrl) {
        Connection conn = Jsoup.connect(detailUrl);

        try {
            Document document = conn.get();
            System.out.println("connect 성공");

//            Elements tabs = document.select("div.tab01.full ")\;
//            for (Element tab:
//                 tabs) {
//                System.out.println("tab = " + tab);
//
//            }

//            Elements elements = document.select("div[style]");
//            System.out.println("tab = " + elements);

            Elements elements = document.select("div.mt20").next();
            System.out.println("elements.size() = " + elements.size());
            // 0:무의미함, 1: 강의소개, 2: 강의목차, 3: 강의추천, 4:강의평가

            // 강의소개
            System.out.println("강의소개");
            String test = elements.get(1).toString();
            System.out.println(test);
            System.out.println();

            // 이미지 경로
            System.out.println("이미지 경로");
            if (!elements.get(1).select("img[src]").isEmpty()) {
                System.out.println("https://www.dongacc.com/" + elements.get(1).select("img").attr("src"));
            }
            System.out.println();


            // 강의 커리큘럼
            System.out.println("강의 커리큘럼");
            System.out.println(elements.get(2));
            System.out.println();

//            // 전체 탭
//            for (Element element:
//                 elements) {
//                System.out.println("element = " + element);
//
//                // img 저장
//                if (!element.select("img[src]").isEmpty()) {
//                    System.out.println("https://www.dongacc.com/" + element.select("img").attr("src"));
//                }
//
//            }

        }catch (IOException ignored) {

        }



    }


    public static void main(String[] args) {
        getLectureCrawlingList();
    }

}
