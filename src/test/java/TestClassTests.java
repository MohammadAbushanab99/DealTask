import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.test.Deal;
import com.task.test.DealResponse;
import com.task.test.DealValidationService;
import com.task.test.TestClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestClass.class)
@AutoConfigureMockMvc
public class TestClassTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DealValidationService dealValidationService;


    @Test
    public void createDealTest() throws Exception {
        String dealJson = "{ \"dealUniqueId\": \"1234567895\", \"fromCurrencyISOCode\": \"USD\", \"toCurrencyISOCode\": \"EUR\", \"dealTimestamp\": \"2023-10-29T14:30:00\", \"dealAmountInOrderingCurrency\": 1000.50 }";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                        .post("/deals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dealJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());


        MvcResult result = resultActions.andReturn();
        String content = result.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        DealResponse dealResponse = objectMapper.readValue(content, DealResponse.class);

        String status = dealResponse.getStatus();
        Deal deal = dealResponse.getDeal();

        assertEquals("Deal created successfully", status);
        assertNotNull(deal);
    }


    @Test
    public void testValidDealUniqueId() {
        String validDealUniqueId = "Ab12345678";
        assertTrue(dealValidationService.isValidDealUniqueId(validDealUniqueId));
    }

    @Test
    public void testInvalidDealUniqueId() {
        String invalidDealUniqueId = "InvalidDeal";
        assertFalse(dealValidationService.isValidDealUniqueId(invalidDealUniqueId));
    }

    @Test
    public void testValidCurrencyCode() {
        String validCurrencyCode = "USD";
        assertTrue(dealValidationService.isValidCurrencyCode(validCurrencyCode));
    }

    @Test
    public void testInvalidCurrencyCode() {
        String invalidCurrencyCode = "INVALID";
        assertFalse(dealValidationService.isValidCurrencyCode(invalidCurrencyCode));
    }

    @Test
    public void testValidTimestampFormat() {
        String validTimestamp = "2023-10-29T14:30:00";
        assertTrue(dealValidationService.isValidTimestampFormat(validTimestamp));
    }

    @Test
    public void testInvalidTimestampFormat() {
        String invalidTimestamp = "InvalidTimestamp";
        assertFalse(dealValidationService.isValidTimestampFormat(invalidTimestamp));
    }

    @Test
    public void testValidDealAmount() {
        String validDealAmount = "1000.50";
        assertTrue(dealValidationService.isValidDealAmount(validDealAmount));
    }

    @Test
    public void testInvalidDealAmount() {
        String invalidDealAmount = "InvalidAmount";
        assertFalse(dealValidationService.isValidDealAmount(invalidDealAmount));
    }
}
