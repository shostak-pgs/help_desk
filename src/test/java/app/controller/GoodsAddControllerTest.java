package app.controller;

import app.config.HibernateUtils;
import app.config.SpringMailConfig;
import app.config.WebConfig;
import app.entity.Good;
import app.entity.Order;
import app.entity.User;
import app.service.impl.GoodsServiceImpl;
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
import java.util.*;
import static app.controller.GoodsAddController.BASKET;
import static app.controller.InitializerController.ALL_GOODS;
import static app.page_path.PagePath.*;
import static app.service.impl.OrderGoodsServiceImpl.PRICE;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static app.service.impl.OrderGoodsServiceImpl.PRINT_GOODS;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {HibernateUtils.class, WebConfig.class, SpringMailConfig.class})
@WebAppConfiguration(value = "src/main/java/app/config")
public class GoodsAddControllerTest {
    private static final String ADD_GOOD_URL = "/goodsAddServlet";
    private static final String COMPLETE_ORDER_URL = "/complete";
    private static final String CURRENT_USER = "currentUser";
    private static final String ITEM = "good";
    private static final String EMPTY_ELEMENT = "--Choose item--";

    private MockMvc mockMvc;
    private MockHttpSession sessionMock;

    @Mock
    private OrderGoodsServiceImpl serviceMock;

    @Mock
    private GoodsServiceImpl goodsServiceMock;

    @InjectMocks
    private GoodsAddController mockController;

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
    public void testAddGood() throws Exception {
        String item = "Advanced SQL (5.2 $)";
        Good good = new Good(3L, "Advanced SQL", 5.2);
        List<Good> allGoodsList = Arrays.asList(new Good(2L, "SQL", 4.2),
            new Good(3L, "Advanced SQL", 5.2));

        Map<String, Integer> expected = new HashMap<>();
        expected.put("Advanced SQL", 1);

        sessionMock.setAttribute(ALL_GOODS, allGoodsList);
        sessionMock.setAttribute(BASKET, expected);

        when(goodsServiceMock.getGood("Advanced SQL")).thenReturn(good);
        when(serviceMock.getOrderedGoods(anyList())).thenReturn(expected);

        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.post((ADD_GOOD_URL))
                .contentType("application/x-www-form-urlencoded")
                .accept(MediaType.TEXT_PLAIN)
                .param(ITEM, item)
                .session(sessionMock);

        mockMvc.perform(builder)
            .andExpect(status().isOk())
            .andExpect(view().name(ADD.getURL()))
            .andExpect(model().attribute(BASKET, expected));
    }

    @Test
    public void testDoGetRedirectionGoodAdd() throws Exception {
        mockMvc.perform(get(ADD_GOOD_URL))
            .andExpect(status().isOk())
            .andExpect(view().name(ADD.getURL()));
    }

    @Test
    public void testDoGetRedirectionComplete() throws Exception {
        mockMvc.perform(get(COMPLETE_ORDER_URL))
            .andExpect(status().isOk())
            .andExpect(view().name(ADD.getURL()));
    }

    @Test
    public void testCompleteOrder() throws Exception {
        String item = "Advanced SQL (5.2 $)";
        Good good = new Good(3L, "Advanced SQL", 5.2);

        List<Good> orderGoods = Collections.singletonList(new Good(3L, "Advanced SQL", 5.2));
        List<String> presentationParams = new LinkedList<>();
        presentationParams.add("1) Advanced SQL x 1");

        Map<String, Integer> basket = new HashMap<>();
        basket.put("Advanced SQL", 1);

        sessionMock.setAttribute(BASKET, basket);

        when(goodsServiceMock.getGood("Advanced SQL")).thenReturn(good);
        when(serviceMock.getOrderedGoods(orderGoods)).thenReturn(basket);
        when(serviceMock.createPresentationParams(basket)).thenReturn(presentationParams);
        when(serviceMock.countPrice(orderGoods)).thenReturn("5.2");

        MockHttpServletRequestBuilder builder =
            MockMvcRequestBuilders.post((COMPLETE_ORDER_URL))
                .contentType("application/x-www-form-urlencoded")
                .accept(MediaType.TEXT_PLAIN)
                .param(ITEM, item)
                .session(sessionMock);

        mockMvc.perform(builder)
            .andExpect(status().isOk())
            .andExpect(view().name(PRINT_CHECK.getURL()))
            .andExpect(model().attribute(PRICE, "5.2"))
            .andExpect(model().attribute(PRINT_GOODS, presentationParams));
    }


    @Test
    public void testCompleteOrderNegativeEmptyBasket() throws Exception {
        String item = EMPTY_ELEMENT;
        Good good = new Good(3L, "Advanced SQL", 5.2);

        List<Good> orderGoods = Collections.singletonList(new Good(3L, "Advanced SQL", 5.2));

        Map<String, Integer> basket = new HashMap<>();

        sessionMock.setAttribute(BASKET, basket);

        when(goodsServiceMock.getGood("Advanced SQL")).thenReturn(good);
        when(serviceMock.getOrderedGoods(orderGoods)).thenReturn(basket);

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post((COMPLETE_ORDER_URL))
                    .contentType("application/x-www-form-urlencoded")
                    .accept(MediaType.TEXT_PLAIN)
                    .param(ITEM, item)
                    .session(sessionMock);

        mockMvc.perform(builder)
             .andExpect(status().isOk())
             .andExpect(view().name(EMPTY_BASKET_ERROR.getURL()));

    }

}
