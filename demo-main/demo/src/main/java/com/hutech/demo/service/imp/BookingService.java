package com.hutech.demo.service.imp;

import com.hutech.demo.createrequest.CreateBookingRequest;
import com.hutech.demo.filter.BookingFilter;
import com.hutech.demo.mapper.BookingMapper;
import com.hutech.demo.model.Booking;
import com.hutech.demo.model.Customer;
import com.hutech.demo.model.Helper;
import com.hutech.demo.model.Service;
import com.hutech.demo.repository.BookingRepository;
import com.hutech.demo.repository.CustomerRepository;
import com.hutech.demo.repository.HelperRepository;
import com.hutech.demo.repository.ServiceRepository;
import com.hutech.demo.response.BookingResponse;
import com.hutech.demo.service.inf.IBookingService;
import org.apache.coyote.BadRequestException;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class BookingService implements IBookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final ServiceRepository serviceRepository;
    private final HelperRepository helperRepository;
    private final BookingMapper bookingMapper;
    private final PaymentService paymentService;

    public BookingService(BookingRepository bookingRepository,
                          CustomerRepository customerRepository,
                          ServiceRepository serviceRepository,
                          HelperRepository helperRepository,
                          BookingMapper bookingMapper,
                          PaymentService paymentService) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.serviceRepository = serviceRepository;
        this.helperRepository = helperRepository;
        this.bookingMapper = bookingMapper;
        this.paymentService = paymentService;
    }

    @Override
    public BookingResponse createBooking(CreateBookingRequest request) {
        try {
            Customer customer = customerRepository.findById(request.getCustomerId())
                    .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
            Service service = serviceRepository.findById(request.getServiceId())
                    .orElseThrow(() -> new IllegalArgumentException("Service not found"));
            Helper helper = helperRepository.findById(request.getHelperId())
                    .orElseThrow(() -> new IllegalArgumentException("Helper not found"));

            Booking booking = bookingMapper.toBooking(request);
            if (request.getEndTime().isBefore(request.getStartTime()))
                throw new BadRequestException("Invalid input time");
            long daysBetween = Duration.between(request.getStartTime(), request.getEndTime()).toDays()+1;

            // Calculate the total price
            booking.setTotalAmount(service.getCostPerDay().multiply(BigDecimal.valueOf(daysBetween)));
            booking.setService(service);
            booking.setCustomer(customer);
            booking.setHelper(helper);
            booking.setStatus(Booking.Status.PENDING);

            bookingRepository.save(booking);

//        paymentService.createPayment()
            return bookingMapper.toResponse(booking);
        }
        catch (Exception e) {
            // Handle or log the exceptions
            throw new RuntimeException("Error during booking creation", e);
        }
    }

    @Override
    public BookingResponse updateBookingStatus(Long id, Booking.Status status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("This booking with \"" + id + "\" doesn't exist"));

        booking.setStatus(status);
        bookingRepository.save(booking);

        return bookingMapper.toResponse(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        return bookingMapper.toResponse(booking);
    }

    @Override
    public List<BookingResponse> getAllBookings(BookingFilter filter) {
        List<Booking> bookings = bookingRepository.findAll();

        // Apply filters based on non-null filter fields
        List<Booking> filteredBookings = bookings.stream()
                .filter(booking -> filter.getCustomerId() == null || booking.getCustomer().getId().equals(filter.getCustomerId()))
                .filter(booking -> filter.getHelperId() == null || booking.getHelper().getId().equals(filter.getHelperId()))
                .filter(booking -> filter.getServiceId() == null || booking.getService().getId().equals(filter.getServiceId()))
                .filter(booking -> filter.getStartTime() == null ||
                        (booking.getStartTime() != null && booking.getStartTime().isAfter(filter.getStartTime())))
                .filter(booking -> filter.getEndTime() == null ||
                        (booking.getEndTime() != null && booking.getEndTime().isBefore(filter.getEndTime())))
                .filter(booking -> filter.getStatus() == null || booking.getStatus().toString().equalsIgnoreCase(filter.getStatus()))
                .collect(Collectors.toList());

        // Map filtered bookings to responses
        return bookingMapper.getList(filteredBookings);
    }
}
