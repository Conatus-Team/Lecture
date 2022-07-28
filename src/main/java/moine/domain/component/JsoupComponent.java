package moine.domain.component;

import moine.domain.dto.LectureCrawlingVO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsoupComponent {

    final String site_name = "라임 사이버 문화센터";

    public List<LectureCrawlingVO> getLectureCrawlingList() {

        // 타겟 url
        // 라임 사이버 문화센터 > 취미교양 탭
        final String originURL = "https://www.dongacc.com/online/category.htm?ca_code=11";

        Connection conn = Jsoup.connect(originURL);


        try {
            Document document = conn.get();
            return getLectureCrawlingList(document);

        } catch (IOException ignored) {
        }

        return null;
    }

    // 테이블 헤더
    // 분류 과정명 모바일 강사 수강기간 수강료 추천 맛보기 자세히
    public List<LectureCrawlingVO> getLectureCrawlingList(Document document) {
        // table.type_2의 type_2는 함수나 속성이 아니라 개발자도구로 보면 이름 자체임. class
        Elements lectureTable = document.select("table tbody tr");

        List<LectureCrawlingVO> list = new ArrayList<>();
        String category = "";
        String course_url="";

        // tr
        for (Element element : lectureTable) {
            LectureCrawlingVO lectureCrawlingVO = LectureCrawlingVO.builder().build();
            Class<?> clazz = lectureCrawlingVO.getClass();
            Field[] fields = clazz.getDeclaredFields();


            ArrayList data = new ArrayList<String>();

            // td
            Elements td = element.select("td");
            for(int i=0; i< td.size(); i++){
                // rowspan 속성이 존재하면 분류 가져오기
                if(!(td.get(i).attr("rowspan").isEmpty())){
                    category = td.get(i).text();
                    continue;
                }


                String text="";
                if(td.get(i).select("a").attr("href").isEmpty()){
                    // td의 글씨
                    text = td.get(i).text();
                }else if(td.get(i).select("a").attr("href") != "#"){
                    if(course_url.isEmpty()){
                        // 과정명에 있는 url 가져오기
                        text = td.get(i).text();
                        course_url = "https://www.dongacc.com/online/"+td.get(i).select("a").attr("href");

                    }

                }

                data.add(text);
            }
            data.add(course_url);
            data.add(category);
            course_url = "";

            // vo에 저장
            for(int i=0; i<fields.length; i++) {
                fields[i].setAccessible(true);
            }
            try {
                // data = [0:과정명, 1:모바일, 2:강사, 3:수강기간, 4:수강료, 5:추천, 6:'', 7:'', 8:url, 9:분류]
                // fields = [0:취미분야, 1:강의명, 2:강사명, 3:url, 4:원본사이트좋아요]
                fields[0].set(lectureCrawlingVO, data.get(9)); // 분류
                fields[1].set(lectureCrawlingVO, data.get(0)); // 과정명
                fields[2].set(lectureCrawlingVO, data.get(2)); // 강사
                fields[3].set(lectureCrawlingVO, data.get(8)); // url
                fields[4].set(lectureCrawlingVO, Long.parseLong((String) data.get(5))); // 추천
                fields[5].set(lectureCrawlingVO, site_name); // 사이트명

            }
            catch (Exception ignored){

            }

            list.add(lectureCrawlingVO);

        }
        return list;
    }


}
