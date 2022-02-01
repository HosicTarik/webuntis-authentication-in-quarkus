package at.htl.service;

import org.bytedream.untis4j.Session;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

@ApplicationScoped
public class WebUntisService {

    @ConfigProperty(name = "auth.school.name")
    String schoolName;

    @ConfigProperty(name = "auth.untis.server")
    String serverName;

    public String authenticateUser(String userName, String password) {
        try {
            Session session = Session.login(
                    userName,
                    password,
                    this.serverName,
                    this.schoolName.replace(" ", "%20"));

            if (session != null) {
                System.out.println("Login successfull");

                var result = session.getTeachers().searchByName(userName);

                session.logout();

                return result.stream().findFirst().get().toString();
            } else {
                System.out.println("Login failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Unknown error";
    }
}
