package moine.domain.middle;

import lombok.Getter;

@Getter
public enum Url {
    LOCAL("http://localhost:"),
    RELEASE(""),
    BASE(LOCAL.getUrl()),

    AUTH(BASE.getUrl() + "8080" + "/auth/connect_middle"),
    LECTURE(BASE.getUrl() + "8084" + "/lecture/connect_middle"),
    GROUP(BASE.getUrl() + "8083" + "/group/connect_middle"),
    CHATTING(BASE.getUrl() + "8085" + "/chatting/connect_middle"),
    RECOMMEND(BASE.getUrl() + "80856" + "/recommend/connect_middle"),
    MIDDLE(BASE.getUrl() + "8082" + "/middle");


    private final String url;

    private Url(String url) {
        this.url = url;
    }

}
