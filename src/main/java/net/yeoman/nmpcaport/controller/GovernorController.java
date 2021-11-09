package net.yeoman.nmpcaport.controller;

import net.yeoman.nmpcaport.services.Impl.GovernorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/governor")
public class GovernorController {

    @Autowired
    private GovernorServiceImpl governorService;
}
