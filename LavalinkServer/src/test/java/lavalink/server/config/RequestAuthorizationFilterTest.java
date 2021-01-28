package lavalink.server.config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by napster on 08.03.19.
 */
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest()
@ActiveProfiles({"test"})
public class RequestAuthorizationFilterTest {

  @Autowired
  protected MockMvc mvc;

  @Autowired
  protected ServerConfig serverConfig;

  @Test
  public void unauthenticatedRequest_Fail() throws Exception {
    this.mvc.perform(get("/loadtracks"))
      .andExpect(status().isUnauthorized());
  }

  @Test
  public void wrongAuthenticatedRequest_Fail() throws Exception {
    this.mvc.perform(get("/loadtracks")
      .header("Authorization", serverConfig.getPassword() + "foo"))
      .andExpect(status().isForbidden());
  }
}
