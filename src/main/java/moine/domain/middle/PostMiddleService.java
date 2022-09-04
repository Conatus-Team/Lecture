package moine.domain.middle;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class PostMiddleService {

    public String sendTo(String url, Object body) {

        // header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // body, header
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);

        // send
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                url, //{요청할 서버 주소}
                HttpMethod.POST, //{요청할 방식}
                entity, // {요청할 때 보낼 데이터}
                String.class // {반환 타입}
        );

        return response.toString();
    }
}
