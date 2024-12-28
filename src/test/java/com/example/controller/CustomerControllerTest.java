package com.example.controller;
import com.example.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @Test
    void createCustomer_ShouldReturnOk() throws Exception {
        mockMvc.perform(post("/customers/create")
                        .param("name", "John Doe")
                        .param("email", "john.doe@example.com")
                        .param("phone", "1234567890"))
                .andExpect(status().isOk());

        Mockito.verify(customerService).createCustomer("John Doe", "john.doe@example.com", "1234567890");
    }
    @Test
    void assignTicketToCustomer_ShouldReturnOk() throws Exception {
        mockMvc.perform(post("/customers/assignTicket")
                        .param("ticketId", "1")
                        .param("customerId", "2"))
                .andExpect(status().isOk());

        Mockito.verify(customerService).assignTicketToCustomer(1L, 2L);
    }
}