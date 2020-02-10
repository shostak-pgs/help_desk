package app.controller;

import app.config.HibernateUtils;
import app.config.SpringMailConfig;
import app.config.WebConfig;
import app.entity.Good;
import app.entity.Order;
import app.entity.User;
import app.service.impl.GoodsServiceImpl;
import app.service.impl.OrderGoodsServiceImpl;
import app.service.impl.OrderServiceImpl;
import app.service.impl.UserServiceImpl;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static app.controller.GoodsAddController.BASKET;
import static app.controller.InitializerController.USER_NAME;
import static app.page_path.PagePath.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateUtils.class, WebConfig.class, SpringMailConfig.class})
@WebAppConfiguration(value = "src/main/java/app/config")
public class InitializerControllerTest {
    private static final String ENTER_URL = "/createUserServlet";
    private static final String TERM = "term";
    private static final String START_PAGE_URL = "/";

    private MockMvc mockMvc;
    MockHttpSession sessionMock;

    @Mock
    private OrderGoodsServiceImpl orderGoodsServiceMock;

    @Mock
    private GoodsServiceImpl goodsServiceMock;

    @Mock
    private OrderServiceImpl orderServiceMock;

    @Mock
    private UserServiceImpl userServiceMock;

    @InjectMocks
    private InitializerController mockController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mockController).build();
        sessionMock = new MockHttpSession();
    }

    @Test
    public void testCreateUser() throws Exception {
        String userName = "Nikol";
        String term = "agreed";
        List<Good> goodsList = Arrays.asList(new Good(1L, "Good1", 1.0), new Good(2L, "Good2", 2.0), new Good(3L, "Good3", 3.0));
        List<Good> chosenItem = Arrays.asList(new Good(1L, "Good1", 1.0), new Good(2L, "Good2", 2.0));
        Map<String, Integer> basket = new HashMap<>();
        basket.put("Good1", 1);
        basket.put("Good2", 1);

        Order order = new Order(1L, 3L, 0.0);
        order.setGoods(chosenItem);
        User user = new User(3L, "Nikol", "Sandow", "nik6@gmail.com", order);

        when(goodsServiceMock.getGoods()).thenReturn(goodsList);
        when(userServiceMock.getUser(userName)).thenReturn(user);
        when(orderServiceMock.get("3")).thenReturn(order);
        when(orderGoodsServiceMock.getOrderedGoods(chosenItem)).thenReturn(basket);


        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.post(ENTER_URL)
                .contentType("application/x-www-form-urlencoded")
                .accept(MediaType.TEXT_PLAIN)
                .param(USER_NAME, userName)
                .param(TERM, term)
                .session(sessionMock);

        mockMvc.perform(builder)
             .andExpect(status().isOk())
             .andExpect(view().name(ADD.getURL()))
             .andExpect(model().attribute(USER_NAME, userName))
             .andExpect(model().attribute(BASKET, basket));
    }

    @Test
    public void testCreateUserNegativeNotTakeTerms() throws Exception {
        String userName = "Nikol";

        List<Good> goodsList = Arrays.asList(new Good(1L, "Good1", 1.0), new Good(2L, "Good2", 2.0), new Good(3L, "Good3", 3.0));

        when(goodsServiceMock.getGoods()).thenReturn(goodsList);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post(ENTER_URL)
                        .contentType("application/x-www-form-urlencoded")
                        .accept(MediaType.TEXT_PLAIN)
                        .param(USER_NAME, userName)
                        .session(sessionMock);

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(view().name(TERMS_ERROR.getURL()));

    }

    @Test
    public void testCreateUserNegativeBlankName() throws Exception {
        String userName = "";
        String term = "agreed";
        List<Good> goodsList = Arrays.asList(new Good(1L, "Good1", 1.0), new Good(2L, "Good2", 2.0), new Good(3L, "Good3", 3.0));

        when(goodsServiceMock.getGoods()).thenReturn(goodsList);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post(ENTER_URL)
                        .contentType("application/x-www-form-urlencoded")
                        .accept(MediaType.TEXT_PLAIN)
                        .param(USER_NAME, userName)
                        .param(TERM, term)
                        .session(sessionMock);

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(view().name(TERMS_ERROR.getURL()));
    }

    @Test
    public void testRedirectToFirstPage() throws Exception {
        mockMvc.perform(get(START_PAGE_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(HOME_PAGE.getURL()));

    }
}
