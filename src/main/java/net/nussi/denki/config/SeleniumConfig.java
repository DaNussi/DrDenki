package net.nussi.denki.config;

public class SeleniumConfig {
    private Grid grid;


    public static class Grid {
        private String url = "http://localhost:4444/wd/hub";

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
