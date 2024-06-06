package com.baeldung.crud;
import com.baeldung.crud.controllers.EventController;
import com.baeldung.crud.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class EventControllerTest {
    private static EventController eventController;
    private static BindingResult mockedBindingResult;
    private static EventRepository mackedEventRepository;
    private static Model mockedModel;

    @BeforeEach
    public  void setUpEventControllerInstance() {
        mackedEventRepository = mock(EventRepository.class);
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        eventController = new EventController(mackedEventRepository);
    }
    @Test
    public void whenCalledIndex_thenCorrect() {
       assertThat(eventController.event().getViewName().toString().equals("events"));
    }
}
