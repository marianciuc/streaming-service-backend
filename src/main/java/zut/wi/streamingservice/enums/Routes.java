package zut.wi.streamingservice.enums;


import lombok.Getter;

@Getter
public enum Routes {
    AUTHENTICATION("/authentication/**");

    private final String route;

    Routes(String route) {
        this.route = route;
    }

}
