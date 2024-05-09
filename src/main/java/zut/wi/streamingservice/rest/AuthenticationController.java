package zut.wi.streamingservice.rest;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zut.wi.streamingservice.dto.StripeTokenDto;

@RestController
@RequestMapping("/authentication")
@AllArgsConstructor
public class AuthenticationController {
    @PostMapping("/login")
    @ResponseBody
    public StripeTokenDto createCardToken(@RequestBody StripeTokenDto model){
       return null;
    }
}
