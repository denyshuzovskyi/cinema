package controller;

public class Page {
    private String pagePath;
    private WayToSend wayToSend;

    public Page(String pagePath, WayToSend wayToSend) {
        this.pagePath = pagePath;
        this.wayToSend = wayToSend;
    }

    public String getPagePath() {
        return pagePath;
    }

    public WayToSend getWayToSend() {
        return wayToSend;
    }

    public enum WayToSend {
        forward, redirect
    }
}
