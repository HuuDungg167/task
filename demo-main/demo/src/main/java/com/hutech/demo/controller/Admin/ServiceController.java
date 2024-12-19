//package com.hutech.demo.controller.Admin;
//
//import com.hutech.demo.model.Service;
//import com.hutech.demo.service.ServiceService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/admin/services")
//@RequiredArgsConstructor
//public class ServiceController {
//
//    private final ServiceService serviceService;
//
//    // Hiển thị danh sách dịch vụ
//    @GetMapping
//    public String listServices(Model model) {
//        model.addAttribute("services", serviceService.getAllServices());
//        return "admin/services"; // Trang hiển thị danh sách dịch vụ
//    }
//
//    // Hiển thị form thêm dịch vụ
//    @GetMapping("/add")
//    public String showAddServiceForm(Model model) {
//        model.addAttribute("service", new Service());
//        return "admin/add-service"; // Trang thêm dịch vụ
//    }
//
//    // Xử lý thêm dịch vụ
//    @PostMapping("/add")
//    public String addService(@Valid @ModelAttribute("service") Service service, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "admin/add-service"; // Trả về lại form nếu có lỗi
//        }
//        serviceService.saveService(service);
//        return "redirect:/admin/services"; // Quay lại danh sách dịch vụ
//    }
//
//    // Hiển thị form chỉnh sửa dịch vụ
//    @GetMapping("/edit/{id}")
//    public String showEditServiceForm(@PathVariable("id") Long id, Model model) {
//        Service service = serviceService.getServiceById(id)
//                .orElseThrow(() -> new IllegalStateException("Service with ID " + id + " does not exist."));
//        model.addAttribute("service", service);
//        return "admin/edit-service"; // Trang chỉnh sửa dịch vụ
//    }
//
//    // Xử lý chỉnh sửa dịch vụ
//    @PostMapping("/edit/{id}")
//    public String editService(@PathVariable("id") Long id, @Valid @ModelAttribute("service") Service service, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "admin/edit-service"; // Trả về lại form nếu có lỗi
//        }
//        service.setId(id);
//        serviceService.updateService(service);
//        return "redirect:/admin/services"; // Quay lại danh sách dịch vụ
//    }
//
//    // Xử lý xóa dịch vụ
//    @GetMapping("/delete/{id}")
//    public String deleteService(@PathVariable("id") Long id) {
//        serviceService.deleteService(id);
//        return "redirect:/admin/services"; // Quay lại danh sách dịch vụ
//    }
//}
