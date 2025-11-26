package ma.formations.spring.resttemplatefront.controller;

import ma.formations.spring.resttemplatefront.domaine.EmpVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
@Controller
public class EmpController {
    private final RestTemplate restTemplate;
    @Value("${server.rest.url}")
    private String url;
    public EmpController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @RequestMapping("/")
    public String showWelcomeFile(Model m) {
        return "index";
    }
    @RequestMapping("/empform")
    public String showform(Model m) {
        m.addAttribute("empVo", new EmpVo());
        return "empform";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("empVo") EmpVo emp) {
// HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
// Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EmpVo> entity = new HttpEntity<EmpVo>(emp, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity,
                String.class);
        HttpStatusCode statusCode = response.getStatusCode();
        System.out.println("Response Satus Code: " + statusCode);
        return "redirect:/viewemp";// will redirect to viewemp request mapping
    }
    @RequestMapping("/viewemp")
    public String viewemp(Model m) {
// HttpHeaders
        EmpVo[] list = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
// Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
// HttpEntity<String>: To get result as String.
        HttpEntity<EmpVo[]> entity = new HttpEntity<EmpVo[]>(headers);
// Send request with GET method, and Headers.
        ResponseEntity<EmpVo[]> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                EmpVo[].class);
        HttpStatusCode statusCode = response.getStatusCode();
        System.out.println("Response Satus Code: " + statusCode);
        if (statusCode == HttpStatus.OK)
            list = response.getBody();
        m.addAttribute("list", Arrays.asList(list));
        return "viewemp";
    }
    @RequestMapping(value = "/editemp/{id}")
    public String edit(@PathVariable Long id, Model m) {
// HttpHeaders
        EmpVo emp = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EmpVo> entity = new HttpEntity<EmpVo>(headers);
        ResponseEntity<EmpVo> response = restTemplate.exchange(url + "/id/" + id, HttpMethod.GET,
                entity, EmpVo.class);
        HttpStatusCode statusCode = response.getStatusCode();
        System.out.println("Response Satus Code: " + statusCode);
        if (statusCode == HttpStatus.OK)
            emp = response.getBody();
        m.addAttribute("empVo", emp);
        return "empeditform";
    }
    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editsave(@ModelAttribute("empVo") EmpVo emp) {
// HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
// Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EmpVo> entity = new HttpEntity<EmpVo>(emp, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity,
                String.class);
        HttpStatusCode statusCode = response.getStatusCode();
        System.out.println("Response Satus Code: " + statusCode);
        return "redirect:/viewemp";
    }
    @RequestMapping(value = "/deleteemp/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
// HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
// Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EmpVo> entity = new HttpEntity<EmpVo>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url + "/" + id, HttpMethod.DELETE,
                entity, String.class);
        HttpStatusCode statusCode = response.getStatusCode();
        System.out.println("Response Satus Code: " + statusCode);
        return "redirect:/viewemp";
    }
}