package com.baeldung.crud.repositories;

import com.baeldung.crud.form.AddEventForm;

public interface EventRepository {
        public Object findAllEvents();
        boolean addEvent(AddEventForm addEventForm,String imgPath);
        boolean deleteEvent(int event_id);
}
