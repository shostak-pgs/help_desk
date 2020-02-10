package app.controller;

import app.config.HibernateUtils;
import app.config.SpringMailConfig;
import app.config.WebConfig;
import app.entity.Order;
import app.entity.User;
import app.service.impl.EmailService;
import app.service.impl.OrderGoodsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static app.page_path.PagePath.LETTER_WAS_SENT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateUtils.class, WebConfig.class, SpringMailConfig.class})
@WebAppConfiguration(value = "src/main/java/app/config")
public class SendMailControllerTest {
    private static final String SEND_MAIL_URL = "/sendMailWithInlineImage";
    private static final String CURRENT_USER = "currentUser";

    private MockMvc mockMvc;
    private MockHttpSession sessionMock;

    @Mock
    private OrderGoodsServiceImpl serviceMock;

    @Mock
    private EmailService emailServiceMock;

    @InjectMocks
    private SendMailController mockController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mockController).build();
        sessionMock = new MockHttpSession();
        Order order = new Order(1L, 3L, 0.0);
        User user = new User(3L, "Shostak", "Archi", "arshostak6@gmail.com", order);
        sessionMock.setAttribute(CURRENT_USER, user);
    }

    @Test
    public void testSendMailWithInline() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get(SEND_MAIL_URL)
                        .contentType("application/x-www-form-urlencoded")
                        .accept(MediaType.TEXT_PLAIN)
                        .session(sessionMock);

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(view().name(LETTER_WAS_SENT.getURL()));
    }
}
