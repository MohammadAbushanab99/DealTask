import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.deal.pojo.Deal;
import com.task.deal.pojo.DealResponse;
import com.task.deal.services.DealService;
import com.task.deal.AppClass;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AppClass.class)
@AutoConfigureMockMvc
public class DealControllerTest {

    @Autowired
    private DealService dealService;

    @Test
    public void givenValidRequest_whenCallingTheController_thenShouldReturnDealCreatedSuccessfully() throws Exception {

        Deal deal = new Deal("9999997678", "USD", "EUR", "2023-10-29T14:30:00", "1000.50");

        DealResponse response = dealService.createDeal(deal);

        String status = response.getStatus();
        Deal dealResponse = response.getDeal();

        assertEquals("Deal created successfully", status);
        assertNotNull(dealResponse);
    }


    @Test
    public void givenInValidUniqueId_whenCallingTheController_thenShouldReturnException() throws Exception {

        Deal deal = new Deal("12345", "USD", "EUR", "2023-10-29T14:30:00", "1000.50");
        DealResponse response = dealService.createDeal(deal);

        String status = response.getStatus();
        Deal dealResponse = response.getDeal();

        assertEquals("Invalid DealUniqueId format", status);
        assertNull(dealResponse);
    }

    @Test
    public void givenInValidFromCurrency_whenCallingTheController_thenShouldReturnException() throws Exception {

        Deal deal = new Deal("1111111111", "INVALID", "EUR", "2023-10-29T14:30:00", "1000.50");
        DealResponse response = dealService.createDeal(deal);

        String status = response.getStatus();
        Deal dealResponse = response.getDeal();

        assertEquals("Invalid currency code", status);
        assertNull(dealResponse);
    }

    @Test
    public void givenInValidToCurrency_whenCallingTheController_thenShouldReturnException() throws Exception {

        Deal deal = new Deal("1111111111", "USD", "INVALID", "2023-10-29T14:30:00", "1000.50");
        DealResponse response = dealService.createDeal(deal);

        String status = response.getStatus();
        Deal dealResponse = response.getDeal();

        assertEquals("Invalid currency code", status);
        assertNull(dealResponse);
    }


    @Test
    public void givenInValidTimestamp_whenCallingTheController_thenShouldReturnException() throws Exception {

        Deal deal = new Deal("1111111111", "USD", "EUR", "InvalidTimestamp", "1000.50");
        DealResponse response = dealService.createDeal(deal);

        String status = response.getStatus();
        Deal dealResponse = response.getDeal();

        assertEquals("Invalid timestamp format", status);
        assertNull(dealResponse);
    }

    @Test
    public void givenInValidAmount_whenCallingTheController_thenShouldReturnException() throws Exception {

        Deal deal = new Deal("1111111111", "USD", "EUR", "2023-10-29T14:30:00", "InvalidAmount");

        DealResponse response = dealService.createDeal(deal);

        String status = response.getStatus();
        Deal dealResponse = response.getDeal();

        assertEquals("Invalid deal amount", status);
        assertNull(dealResponse);
    }

}
