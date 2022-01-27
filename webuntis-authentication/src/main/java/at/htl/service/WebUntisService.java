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

    public void authenticateUser(String userName, String password) {
        try {
            Session session = Session.login(
                    userName,
                    password,
                    this.serverName,
                    this.schoolName.replace(" ", "%20"));

            if (session != null) {
                System.out.println("Login successfull");

                session.logout();
            } else {
                System.out.println("Login failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
