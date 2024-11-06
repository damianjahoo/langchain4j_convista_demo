package com.convista.demo.ai.tools;

import com.convista.demo.persistence.BookingRepository;
import com.convista.demo.persistence.entity.Booking;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;

@Service
public class BookingTools {
    private final BookingRepository bookingRepository;

    public BookingTools(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Tool("Create new booking for the user and car and return id of the booking.")
    public Long createBooking(@P("Name of the user") String name, @P("Name of the car") String car) {
        return bookingRepository.save(new Booking(name, car)).getId();
    }

    @Tool("Cancel booking with specified id")
    public void cancelBooking(@P("Id of the booking to cancel") Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow();
        bookingRepository.delete(booking);
    }
}
