package utilityobjects;

import io.github.cdimascio.dotenv.Dotenv;

public class SetUrl {

    public static String url;

    public static String setEnvUrl(String environment) {

        Dotenv dotenv = Dotenv.load();

        switch (environment) {
            case "PRODUCTION":
                url = dotenv.get("PROD_URL");
                break;

            case "STAGING":
                url = dotenv.get("STAGING_URL");
        }
        return url;
    }
}
