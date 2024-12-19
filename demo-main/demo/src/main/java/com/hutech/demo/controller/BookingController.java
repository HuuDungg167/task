package com.hutech.demo.controller;

import com.hutech.demo.createrequest.CreateBookingRequest;
import com.hutech.demo.filter.BookingFilter;
import com.hutech.demo.model.Booking;
import com.hutech.demo.repository.HelperRepository;
import com.hutech.demo.response.BookingResponse;
import com.hutech.demo.service.imp.HelperService;
import com.hutech.demo.service.inf.IBookingService;
import com.hutech.demo.service.inf.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private IBookingService bookingService;
    private IServiceService serviceService;
    private HelperService helperService;

    @PostMapping("/create")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody CreateBookingRequest request) {
        BookingResponse newBooking = bookingService.createBooking(request);
        return ResponseEntity.ok(newBooking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id) {
        BookingResponse booking = bookingService.getBookingById(id);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public List<BookingResponse> getAllBookings(BookingFilter filter) {
        return bookingService.getAllBookings(filter);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BookingResponse> updateBookingStatus(@PathVariable Long id, @RequestBody Booking.Status status) {
        BookingResponse updatedBooking = bookingService.updateBookingStatus(id, status);
        return updatedBooking != null ? ResponseEntity.ok(updatedBooking) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/bookings")
    public String showBookingPage(Model model) {
        model.addAttribute("services", serviceService.getAllServices());
        model.addAttribute("helpers", helperService.getAvailableHelpers());
        return "booking";
    }
}

